package net.coderbot.iris.compat.sodium.mixin.shadow_map;

import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderBackend;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderContainer;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderManager;
import me.jellysquid.mods.sodium.client.render.chunk.cull.ChunkFaceFlags;
import me.jellysquid.mods.sodium.client.render.chunk.lists.ChunkRenderList;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPass;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPassManager;
import net.coderbot.iris.compat.sodium.impl.shadow_map.SwappableChunkRenderManager;
import net.coderbot.iris.shadows.ShadowRenderingState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Modifies {@link ChunkRenderManager} to support maintaining a separate visibility list for the shadow camera, as well
 * as disabling chunk rebuilds when computing visibility for the shadow camera.
 */
@Mixin(ChunkRenderManager.class)
public class MixinChunkRenderManager implements SwappableChunkRenderManager {
    @Unique
    private static final ObjectArrayFIFOQueue<?> EMPTY_QUEUE = new ObjectArrayFIFOQueue<>();
    @Shadow(remap = false)
    @Final
    private ObjectArrayFIFOQueue<ChunkRenderContainer<?>> importantRebuildQueue;
    @Shadow(remap = false)
    @Final
    private ObjectArrayFIFOQueue<ChunkRenderContainer<?>> rebuildQueue;
    @Shadow(remap = false)
    @Final
    @Mutable
    private ChunkRenderList<?>[] chunkRenderLists;
    @Shadow(remap = false)
    @Final
    @Mutable
    private ObjectList<ChunkRenderContainer<?>> tickableChunks;
    @Shadow(remap = false)
    @Final
    @Mutable
    private ObjectList<TileEntity> visibleBlockEntities;
    @Shadow(remap = false)
    private boolean dirty;
    @Shadow(remap = false)
    private int visibleChunkCount;
    @Unique
    private ChunkRenderList<?>[] chunkRenderListsSwap;
    @Unique
    private ObjectList<ChunkRenderContainer<?>> tickableChunksSwap;
    @Unique
    private ObjectList<TileEntity> visibleBlockEntitiesSwap;
    @Unique
    private int visibleChunkCountSwap;
    @Unique
    private boolean dirtySwap;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void iris$onInit(SodiumWorldRenderer renderer, ChunkRenderBackend backend, BlockRenderPassManager renderPassManager, WorldClient world, int renderDistance, CallbackInfo ci) {
        this.chunkRenderListsSwap = new ChunkRenderList[BlockRenderPass.COUNT];
        this.tickableChunksSwap = new ObjectArrayList<>();
        this.visibleBlockEntitiesSwap = new ObjectArrayList<>();

        for (int i = 0; i < this.chunkRenderListsSwap.length; i++) {
            this.chunkRenderListsSwap[i] = new ChunkRenderList<>();
        }

        this.dirtySwap = true;
    }

    @Override
    public void iris$swapVisibilityState() {
        ChunkRenderList<?>[] chunkRenderListsTmp = chunkRenderLists;
        chunkRenderLists = chunkRenderListsSwap;
        chunkRenderListsSwap = chunkRenderListsTmp;

        ObjectList<ChunkRenderContainer<?>> tickableChunksTmp = tickableChunks;
        tickableChunks = tickableChunksSwap;
        tickableChunksSwap = tickableChunksTmp;

        ObjectList<TileEntity> visibleBlockEntitiesTmp = visibleBlockEntities;
        visibleBlockEntities = visibleBlockEntitiesSwap;
        visibleBlockEntitiesSwap = visibleBlockEntitiesTmp;

        int visibleChunkCountTmp = visibleChunkCount;
        visibleChunkCount = visibleChunkCountSwap;
        visibleChunkCountSwap = visibleChunkCountTmp;

        boolean dirtyTmp = dirty;
        dirty = dirtySwap;
        dirtySwap = dirtyTmp;
    }

    @Redirect(method = "addChunk", remap = false,
            at = @At(value = "INVOKE",
                    target = "me/jellysquid/mods/sodium/client/render/chunk/ChunkRenderContainer.canRebuild ()Z",
                    remap = false))
    private boolean iris$noRebuildEnqueueingInShadowPass(ChunkRenderContainer<?> render) {
        if (ShadowRenderingState.areShadowsCurrentlyBeingRendered()) {
            return false;
        }

        return render.canRebuild();
    }

    @Inject(method = "computeVisibleFaces", at = @At("HEAD"), cancellable = true, remap = false)
    private void iris$disableBlockFaceCullingInShadowPass(ChunkRenderContainer<?> render,
                                                          CallbackInfoReturnable<Integer> cir) {
        // TODO: Enable chunk face culling during the shadow pass
        if (ShadowRenderingState.areShadowsCurrentlyBeingRendered()) {
            cir.setReturnValue(ChunkFaceFlags.ALL);
        }
    }

    @Redirect(method = "reset()V", remap = false,
            at = @At(value = "FIELD",
                    target = "me/jellysquid/mods/sodium/client/render/chunk/ChunkRenderManager.rebuildQueue :" +
                            "Lit/unimi/dsi/fastutil/objects/ObjectArrayFIFOQueue;",
                    remap = false))
    private ObjectArrayFIFOQueue<?> iris$noQueueClearingInShadowPass$rebuildQueue(ChunkRenderManager<?> manager) {
        if (ShadowRenderingState.areShadowsCurrentlyBeingRendered()) {
            return EMPTY_QUEUE;
        } else {
            return rebuildQueue;
        }
    }

    @Redirect(method = "reset()V", remap = false,
            at = @At(value = "FIELD",
                    target = "me/jellysquid/mods/sodium/client/render/chunk/ChunkRenderManager.importantRebuildQueue :" +
                            "Lit/unimi/dsi/fastutil/objects/ObjectArrayFIFOQueue;",
                    remap = false))
    private ObjectArrayFIFOQueue<?> iris$noQueueClearingInShadowPass$importantRebuildQueue(ChunkRenderManager<?> manager) {
        if (ShadowRenderingState.areShadowsCurrentlyBeingRendered()) {
            return EMPTY_QUEUE;
        } else {
            return importantRebuildQueue;
        }
    }

    @Inject(method = "updateChunks()V", at = @At("HEAD"), cancellable = true, remap = false)
    private void iris$preventChunkRebuildsInShadowPass(CallbackInfo ci) {
        if (ShadowRenderingState.areShadowsCurrentlyBeingRendered()) {
            ci.cancel();
        }
    }
}
