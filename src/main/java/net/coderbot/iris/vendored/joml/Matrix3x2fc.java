/*
 * The MIT License
 *
 * Copyright (c) 2017-2021 JOML
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.coderbot.iris.vendored.joml;


import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Interface to a read-only view of a 3x2 matrix of single-precision floats.
 *
 * @author Kai Burjack
 */
public interface Matrix3x2fc {

    /**
     * Return the value of the matrix element at column 0 and row 0.
     *
     * @return the value of the matrix element
     */
    float m00();

    /**
     * Return the value of the matrix element at column 0 and row 1.
     *
     * @return the value of the matrix element
     */
    float m01();

    /**
     * Return the value of the matrix element at column 1 and row 0.
     *
     * @return the value of the matrix element
     */
    float m10();

    /**
     * Return the value of the matrix element at column 1 and row 1.
     *
     * @return the value of the matrix element
     */
    float m11();

    /**
     * Return the value of the matrix element at column 2 and row 0.
     *
     * @return the value of the matrix element
     */
    float m20();

    /**
     * Return the value of the matrix element at column 2 and row 1.
     *
     * @return the value of the matrix element
     */
    float m21();

    /**
     * Multiply this matrix by the supplied <code>right</code> matrix by assuming a third row in
     * both matrices of <code>(0, 0, 1)</code> and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the <code>right</code> matrix,
     * then the new matrix will be <code>M * R</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * R * v</code>, the
     * transformation of the right matrix will be applied first!
     *
     * @param right the right operand of the matrix multiplication
     * @param dest  will hold the result
     * @return dest
     */
    Matrix3x2f mul(Matrix3x2fc right, Matrix3x2f dest);

    /**
     * Pre-multiply this matrix by the supplied <code>left</code> matrix and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>L</code> the <code>left</code> matrix,
     * then the new matrix will be <code>L * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>L * M * v</code>, the
     * transformation of <code>this</code> matrix will be applied first!
     *
     * @param left the left operand of the matrix multiplication
     * @param dest the destination matrix, which will hold the result
     * @return dest
     */
    Matrix3x2f mulLocal(Matrix3x2fc left, Matrix3x2f dest);

    /**
     * Return the determinant of this matrix.
     *
     * @return the determinant
     */
    float determinant();

    /**
     * Invert the <code>this</code> matrix by assuming a third row in this matrix of <code>(0, 0, 1)</code>
     * and store the result in <code>dest</code>.
     *
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f invert(Matrix3x2f dest);

    /**
     * Apply a translation to this matrix by translating by the given number of units in x and y and store the result
     * in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>T</code> the translation
     * matrix, then the new matrix will be <code>M * T</code>. So when
     * transforming a vector <code>v</code> with the new matrix by using
     * <code>M * T * v</code>, the translation will be applied first!
     *
     * @param x    the offset to translate in x
     * @param y    the offset to translate in y
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f translate(float x, float y, Matrix3x2f dest);

    /**
     * Apply a translation to this matrix by translating by the given number of units in x and y, and
     * store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>T</code> the translation
     * matrix, then the new matrix will be <code>M * T</code>. So when
     * transforming a vector <code>v</code> with the new matrix by using
     * <code>M * T * v</code>, the translation will be applied first!
     *
     * @param offset the offset to translate
     * @param dest   will hold the result
     * @return dest
     */
    Matrix3x2f translate(Vector2fc offset, Matrix3x2f dest);

    /**
     * Pre-multiply a translation to this matrix by translating by the given number of
     * units in x and y and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>T</code> the translation
     * matrix, then the new matrix will be <code>T * M</code>. So when
     * transforming a vector <code>v</code> with the new matrix by using
     * <code>T * M * v</code>, the translation will be applied last!
     *
     * @param offset the number of units in x and y by which to translate
     * @param dest   will hold the result
     * @return dest
     */
    Matrix3x2f translateLocal(Vector2fc offset, Matrix3x2f dest);

    /**
     * Pre-multiply a translation to this matrix by translating by the given number of
     * units in x and y and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>T</code> the translation
     * matrix, then the new matrix will be <code>T * M</code>. So when
     * transforming a vector <code>v</code> with the new matrix by using
     * <code>T * M * v</code>, the translation will be applied last!
     *
     * @param x    the offset to translate in x
     * @param y    the offset to translate in y
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f translateLocal(float x, float y, Matrix3x2f dest);

    /**
     * Get the current values of <code>this</code> matrix and store them into
     * <code>dest</code>.
     *
     * @param dest the destination matrix
     * @return dest
     */
    Matrix3x2f get(Matrix3x2f dest);


    /**
     * Store this matrix in column-major order into the supplied {@link FloatBuffer} at the current
     * buffer {@link FloatBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     * <p>
     * In order to specify the offset into the FloatBuffer at which
     * the matrix is stored, use {@link #get(int, FloatBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get(int, FloatBuffer)
     */
    FloatBuffer get(FloatBuffer buffer);

    /**
     * Store this matrix in column-major order into the supplied {@link FloatBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     *
     * @param index  the absolute position into the FloatBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    FloatBuffer get(int index, FloatBuffer buffer);

    /**
     * Store this matrix in column-major order into the supplied {@link ByteBuffer} at the current
     * buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which
     * the matrix is stored, use {@link #get(int, ByteBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get(int, ByteBuffer)
     */
    ByteBuffer get(ByteBuffer buffer);

    /**
     * Store this matrix in column-major order into the supplied {@link ByteBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    ByteBuffer get(int index, ByteBuffer buffer);

    /**
     * Store this matrix as an equivalent 3x3 matrix in column-major order into the supplied {@link FloatBuffer} at the current
     * buffer {@link FloatBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     * <p>
     * In order to specify the offset into the FloatBuffer at which
     * the matrix is stored, use {@link #get3x3(int, FloatBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get3x3(int, FloatBuffer)
     */
    FloatBuffer get3x3(FloatBuffer buffer);

    /**
     * Store this matrix as an equivalent 3x3 matrix in column-major order into the supplied {@link FloatBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     *
     * @param index  the absolute position into the FloatBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    FloatBuffer get3x3(int index, FloatBuffer buffer);

    /**
     * Store this matrix as an equivalent 3x3 matrix in column-major order into the supplied {@link ByteBuffer} at the current
     * buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which
     * the matrix is stored, use {@link #get3x3(int, ByteBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get3x3(int, ByteBuffer)
     */
    ByteBuffer get3x3(ByteBuffer buffer);

    /**
     * Store this matrix as an equivalent 3x3 matrix in column-major order into the supplied {@link ByteBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    ByteBuffer get3x3(int index, ByteBuffer buffer);

    /**
     * Store this matrix as an equivalent 4x4 matrix in column-major order into the supplied {@link FloatBuffer} at the current
     * buffer {@link FloatBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     * <p>
     * In order to specify the offset into the FloatBuffer at which
     * the matrix is stored, use {@link #get4x4(int, FloatBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get4x4(int, FloatBuffer)
     */
    FloatBuffer get4x4(FloatBuffer buffer);

    /**
     * Store this matrix as an equivalent 4x4 matrix in column-major order into the supplied {@link FloatBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given FloatBuffer.
     *
     * @param index  the absolute position into the FloatBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    FloatBuffer get4x4(int index, FloatBuffer buffer);

    /**
     * Store this matrix as an equivalent 4x4 matrix in column-major order into the supplied {@link ByteBuffer} at the current
     * buffer {@link ByteBuffer#position() position}.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     * <p>
     * In order to specify the offset into the ByteBuffer at which
     * the matrix is stored, use {@link #get4x4(int, ByteBuffer)}, taking
     * the absolute position as parameter.
     *
     * @param buffer will receive the values of this matrix in column-major order at its current position
     * @return the passed in buffer
     * @see #get4x4(int, ByteBuffer)
     */
    ByteBuffer get4x4(ByteBuffer buffer);

    /**
     * Store this matrix as an equivalent 4x4 matrix in column-major order into the supplied {@link ByteBuffer} starting at the specified
     * absolute buffer position/index.
     * <p>
     * This method will not increment the position of the given ByteBuffer.
     *
     * @param index  the absolute position into the ByteBuffer
     * @param buffer will receive the values of this matrix in column-major order
     * @return the passed in buffer
     */
    ByteBuffer get4x4(int index, ByteBuffer buffer);


    /**
     * Store this matrix into the supplied float array in column-major order at the given offset.
     *
     * @param arr    the array to write the matrix values into
     * @param offset the offset into the array
     * @return the passed in array
     */
    float[] get(float[] arr, int offset);

    /**
     * Store this matrix into the supplied float array in column-major order.
     * <p>
     * In order to specify an explicit offset into the array, use the method {@link #get(float[], int)}.
     *
     * @param arr the array to write the matrix values into
     * @return the passed in array
     * @see #get(float[], int)
     */
    float[] get(float[] arr);

    /**
     * Store this matrix as an equivalent 3x3 matrix into the supplied float array in column-major order at the given offset.
     *
     * @param arr    the array to write the matrix values into
     * @param offset the offset into the array
     * @return the passed in array
     */
    float[] get3x3(float[] arr, int offset);

    /**
     * Store this matrix as an equivalent 3x3 matrix into the supplied float array in column-major order.
     * <p>
     * In order to specify an explicit offset into the array, use the method {@link #get3x3(float[], int)}.
     *
     * @param arr the array to write the matrix values into
     * @return the passed in array
     * @see #get3x3(float[], int)
     */
    float[] get3x3(float[] arr);

    /**
     * Store this matrix as an equivalent 4x4 matrix into the supplied float array in column-major order at the given offset.
     *
     * @param arr    the array to write the matrix values into
     * @param offset the offset into the array
     * @return the passed in array
     */
    float[] get4x4(float[] arr, int offset);

    /**
     * Store this matrix as an equivalent 4x4 matrix into the supplied float array in column-major order.
     * <p>
     * In order to specify an explicit offset into the array, use the method {@link #get4x4(float[], int)}.
     *
     * @param arr the array to write the matrix values into
     * @return the passed in array
     * @see #get4x4(float[], int)
     */
    float[] get4x4(float[] arr);

    /**
     * Apply scaling to this matrix by scaling the unit axes by the given x and y and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>M * S</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * S * v</code>, the scaling will be applied first!
     *
     * @param x    the factor of the x component
     * @param y    the factor of the y component
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scale(float x, float y, Matrix3x2f dest);

    /**
     * Apply scaling to this matrix by scaling the base axes by the given <code>xy</code> factors
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>M * S</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * S * v</code>, the scaling will be applied first!
     *
     * @param xy   the factors of the x and y component, respectively
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scale(Vector2fc xy, Matrix3x2f dest);

    /**
     * Pre-multiply scaling to <code>this</code> matrix by scaling the base axes by the given sx and
     * sy factors while using the given <code>(ox, oy)</code> as the scaling origin,
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>S * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>S * M * v</code>
     * , the scaling will be applied last!
     * <p>
     * This method is equivalent to calling: <code>new Matrix3x2f().translate(ox, oy).scale(sx, sy).translate(-ox, -oy).mul(this, dest)</code>
     *
     * @param sx   the scaling factor of the x component
     * @param sy   the scaling factor of the y component
     * @param ox   the x coordinate of the scaling origin
     * @param oy   the y coordinate of the scaling origin
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scaleAroundLocal(float sx, float sy, float ox, float oy, Matrix3x2f dest);

    /**
     * Pre-multiply scaling to this matrix by scaling the base axes by the given <code>factor</code>
     * while using <code>(ox, oy)</code> as the scaling origin,
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>S * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>S * M * v</code>, the
     * scaling will be applied last!
     * <p>
     * This method is equivalent to calling: <code>new Matrix3x2f().translate(ox, oy).scale(factor).translate(-ox, -oy).mul(this, dest)</code>
     *
     * @param factor the scaling factor for all three axes
     * @param ox     the x coordinate of the scaling origin
     * @param oy     the y coordinate of the scaling origin
     * @param dest   will hold the result
     * @return this
     */
    Matrix3x2f scaleAroundLocal(float factor, float ox, float oy, Matrix3x2f dest);

    /**
     * Apply scaling to this matrix by uniformly scaling the two base axes by the given <code>xy</code> factor
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>M * S</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * S * v</code>, the scaling will be applied first!
     *
     * @param xy   the factor for the two components
     * @param dest will hold the result
     * @return dest
     * @see #scale(float, float, Matrix3x2f)
     */
    Matrix3x2f scale(float xy, Matrix3x2f dest);

    /**
     * Pre-multiply scaling to <code>this</code> matrix by scaling the two base axes by the given <code>xy</code> factor,
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>S * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>S * M * v</code>
     * , the scaling will be applied last!
     *
     * @param xy   the factor to scale all two base axes by
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scaleLocal(float xy, Matrix3x2f dest);

    /**
     * Pre-multiply scaling to <code>this</code> matrix by scaling the base axes by the given x and y
     * factors and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>S * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>S * M * v</code>
     * , the scaling will be applied last!
     *
     * @param x    the factor of the x component
     * @param y    the factor of the y component
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scaleLocal(float x, float y, Matrix3x2f dest);

    /**
     * Apply scaling to <code>this</code> matrix by scaling the base axes by the given sx and
     * sy factors while using <code>(ox, oy)</code> as the scaling origin, and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>M * S</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * S * v</code>
     * , the scaling will be applied first!
     * <p>
     * This method is equivalent to calling: <code>translate(ox, oy, dest).scale(sx, sy).translate(-ox, -oy)</code>
     *
     * @param sx   the scaling factor of the x component
     * @param sy   the scaling factor of the y component
     * @param ox   the x coordinate of the scaling origin
     * @param oy   the y coordinate of the scaling origin
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f scaleAround(float sx, float sy, float ox, float oy, Matrix3x2f dest);

    /**
     * Apply scaling to this matrix by scaling the base axes by the given <code>factor</code>
     * while using <code>(ox, oy)</code> as the scaling origin,
     * and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>S</code> the scaling matrix,
     * then the new matrix will be <code>M * S</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * S * v</code>, the
     * scaling will be applied first!
     * <p>
     * This method is equivalent to calling: <code>translate(ox, oy, dest).scale(factor).translate(-ox, -oy)</code>
     *
     * @param factor the scaling factor for all three axes
     * @param ox     the x coordinate of the scaling origin
     * @param oy     the y coordinate of the scaling origin
     * @param dest   will hold the result
     * @return this
     */
    Matrix3x2f scaleAround(float factor, float ox, float oy, Matrix3x2f dest);

    /**
     * Transform/multiply the given vector by this matrix by assuming a third row in this matrix of <code>(0, 0, 1)</code>
     * and store the result in that vector.
     *
     * @param v the vector to transform and to hold the final result
     * @return v
     * @see Vector3f#mul(Matrix3x2fc)
     */
    Vector3f transform(Vector3f v);

    /**
     * Transform/multiply the given vector by this matrix and store the result in <code>dest</code>.
     *
     * @param v    the vector to transform
     * @param dest will contain the result
     * @return dest
     * @see Vector3f#mul(Matrix3x2fc, Vector3f)
     */
    Vector3f transform(Vector3f v, Vector3f dest);

    /**
     * Transform/multiply the given vector <code>(x, y, z)</code> by this matrix and store the result in <code>dest</code>.
     *
     * @param x    the x component of the vector to transform
     * @param y    the y component of the vector to transform
     * @param z    the z component of the vector to transform
     * @param dest will contain the result
     * @return dest
     */
    Vector3f transform(float x, float y, float z, Vector3f dest);

    /**
     * Transform/multiply the given 2D-vector, as if it was a 3D-vector with z=1, by
     * this matrix and store the result in that vector.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being 1.0, so it
     * will represent a position/location in 2D-space rather than a direction.
     * <p>
     * In order to store the result in another vector, use {@link #transformPosition(Vector2fc, Vector2f)}.
     *
     * @param v the vector to transform and to hold the final result
     * @return v
     * @see #transformPosition(Vector2fc, Vector2f)
     * @see #transform(Vector3f)
     */
    Vector2f transformPosition(Vector2f v);

    /**
     * Transform/multiply the given 2D-vector, as if it was a 3D-vector with z=1, by
     * this matrix and store the result in <code>dest</code>.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being 1.0, so it
     * will represent a position/location in 2D-space rather than a direction.
     * <p>
     * In order to store the result in the same vector, use {@link #transformPosition(Vector2f)}.
     *
     * @param v    the vector to transform
     * @param dest will hold the result
     * @return dest
     * @see #transformPosition(Vector2f)
     * @see #transform(Vector3f, Vector3f)
     */
    Vector2f transformPosition(Vector2fc v, Vector2f dest);

    /**
     * Transform/multiply the given 2D-vector <code>(x, y)</code>, as if it was a 3D-vector with z=1, by
     * this matrix and store the result in <code>dest</code>.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being 1.0, so it
     * will represent a position/location in 2D-space rather than a direction.
     * <p>
     * In order to store the result in the same vector, use {@link #transformPosition(Vector2f)}.
     *
     * @param x    the x component of the vector to transform
     * @param y    the y component of the vector to transform
     * @param dest will hold the result
     * @return dest
     * @see #transformPosition(Vector2f)
     * @see #transform(Vector3f, Vector3f)
     */
    Vector2f transformPosition(float x, float y, Vector2f dest);

    /**
     * Transform/multiply the given 2D-vector, as if it was a 3D-vector with z=0, by
     * this matrix and store the result in that vector.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being <code>0.0</code>, so it
     * will represent a direction in 2D-space rather than a position. This method will therefore
     * not take the translation part of the matrix into account.
     * <p>
     * In order to store the result in another vector, use {@link #transformDirection(Vector2fc, Vector2f)}.
     *
     * @param v the vector to transform and to hold the final result
     * @return v
     * @see #transformDirection(Vector2fc, Vector2f)
     */
    Vector2f transformDirection(Vector2f v);

    /**
     * Transform/multiply the given 2D-vector, as if it was a 3D-vector with z=0, by
     * this matrix and store the result in <code>dest</code>.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being <code>0.0</code>, so it
     * will represent a direction in 2D-space rather than a position. This method will therefore
     * not take the translation part of the matrix into account.
     * <p>
     * In order to store the result in the same vector, use {@link #transformDirection(Vector2f)}.
     *
     * @param v    the vector to transform
     * @param dest will hold the result
     * @return dest
     * @see #transformDirection(Vector2f)
     */
    Vector2f transformDirection(Vector2fc v, Vector2f dest);

    /**
     * Transform/multiply the given 2D-vector <code>(x, y)</code>, as if it was a 3D-vector with z=0, by
     * this matrix and store the result in <code>dest</code>.
     * <p>
     * The given 2D-vector is treated as a 3D-vector with its z-component being <code>0.0</code>, so it
     * will represent a direction in 2D-space rather than a position. This method will therefore
     * not take the translation part of the matrix into account.
     * <p>
     * In order to store the result in the same vector, use {@link #transformDirection(Vector2f)}.
     *
     * @param x    the x component of the vector to transform
     * @param y    the y component of the vector to transform
     * @param dest will hold the result
     * @return dest
     * @see #transformDirection(Vector2f)
     */
    Vector2f transformDirection(float x, float y, Vector2f dest);

    /**
     * Apply a rotation transformation to this matrix by rotating the given amount of radians and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the rotation matrix,
     * then the new matrix will be <code>M * R</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * R * v</code>, the rotation will be applied first!
     *
     * @param ang  the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f rotate(float ang, Matrix3x2f dest);

    /**
     * Pre-multiply a rotation to this matrix by rotating the given amount of radians and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the rotation matrix,
     * then the new matrix will be <code>R * M</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>R * M * v</code>, the
     * rotation will be applied last!
     * <p>
     * Reference: <a href="http://en.wikipedia.org/wiki/Rotation_matrix#Rotation_matrix_from_axis_and_angle">http://en.wikipedia.org</a>
     *
     * @param ang  the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Matrix3x2f rotateLocal(float ang, Matrix3x2f dest);

    /**
     * Apply a rotation transformation to this matrix by rotating the given amount of radians about
     * the specified rotation center <code>(x, y)</code> and store the result in <code>dest</code>.
     * <p>
     * This method is equivalent to calling: <code>translate(x, y, dest).rotate(ang).translate(-x, -y)</code>
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the rotation matrix,
     * then the new matrix will be <code>M * R</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * R * v</code>, the rotation will be applied first!
     *
     * @param ang  the angle in radians
     * @param x    the x component of the rotation center
     * @param y    the y component of the rotation center
     * @param dest will hold the result
     * @return dest
     * @see #translate(float, float, Matrix3x2f)
     * @see #rotate(float, Matrix3x2f)
     */
    Matrix3x2f rotateAbout(float ang, float x, float y, Matrix3x2f dest);

    /**
     * Apply a rotation transformation to this matrix that rotates the given normalized <code>fromDir</code> direction vector
     * to point along the normalized <code>toDir</code>, and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>R</code> the rotation matrix,
     * then the new matrix will be <code>M * R</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * R * v</code>, the rotation will be applied first!
     *
     * @param fromDir the normalized direction which should be rotate to point along <code>toDir</code>
     * @param toDir   the normalized destination direction
     * @param dest    will hold the result
     * @return dest
     */
    Matrix3x2f rotateTo(Vector2fc fromDir, Vector2fc toDir, Matrix3x2f dest);

    /**
     * Apply a "view" transformation to this matrix that maps the given <code>(left, bottom)</code> and
     * <code>(right, top)</code> corners to <code>(-1, -1)</code> and <code>(1, 1)</code> respectively and store the result in <code>dest</code>.
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>O</code> the orthographic projection matrix,
     * then the new matrix will be <code>M * O</code>. So when transforming a
     * vector <code>v</code> with the new matrix by using <code>M * O * v</code>, the
     * orthographic projection transformation will be applied first!
     *
     * @param left   the distance from the center to the left view edge
     * @param right  the distance from the center to the right view edge
     * @param bottom the distance from the center to the bottom view edge
     * @param top    the distance from the center to the top view edge
     * @param dest   will hold the result
     * @return dest
     */
    Matrix3x2f view(float left, float right, float bottom, float top, Matrix3x2f dest);

    /**
     * Obtain the position that gets transformed to the origin by <code>this</code> matrix.
     * This can be used to get the position of the "camera" from a given <i>view</i> transformation matrix.
     * <p>
     * This method is equivalent to the following code:
     * <pre>
     * Matrix3x2f inv = new Matrix3x2f(this).invertAffine();
     * inv.transform(origin.set(0, 0));
     * </pre>
     *
     * @param origin will hold the position transformed to the origin
     * @return origin
     */
    Vector2f origin(Vector2f origin);

    /**
     * Obtain the extents of the view transformation of <code>this</code> matrix and store it in <code>area</code>.
     * This can be used to determine which region of the screen (i.e. the NDC space) is covered by the view.
     *
     * @param area will hold the view area as <code>[minX, minY, maxX, maxY]</code>
     * @return area
     */
    float[] viewArea(float[] area);

    /**
     * Obtain the direction of <code>+X</code> before the transformation represented by <code>this</code> matrix is applied.
     * <p>
     * This method uses the rotation component of the left 2x2 submatrix to obtain the direction
     * that is transformed to <code>+X</code> by <code>this</code> matrix.
     * <p>
     * This method is equivalent to the following code:
     * <pre>
     * Matrix3x2f inv = new Matrix3x2f(this).invert();
     * inv.transformDirection(dir.set(1, 0)).normalize();
     * </pre>
     * If <code>this</code> is already an orthogonal matrix, then consider using {@link #normalizedPositiveX(Vector2f)} instead.
     * <p>
     * Reference: <a href="http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/threeD/">http://www.euclideanspace.com</a>
     *
     * @param dir will hold the direction of <code>+X</code>
     * @return dir
     */
    Vector2f positiveX(Vector2f dir);

    /**
     * Obtain the direction of <code>+X</code> before the transformation represented by <code>this</code> <i>orthogonal</i> matrix is applied.
     * This method only produces correct results if <code>this</code> is an <i>orthogonal</i> matrix.
     * <p>
     * This method uses the rotation component of the left 2x2 submatrix to obtain the direction
     * that is transformed to <code>+X</code> by <code>this</code> matrix.
     * <p>
     * This method is equivalent to the following code:
     * <pre>
     * Matrix3x2f inv = new Matrix3x2f(this).transpose();
     * inv.transformDirection(dir.set(1, 0));
     * </pre>
     * <p>
     * Reference: <a href="http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/threeD/">http://www.euclideanspace.com</a>
     *
     * @param dir will hold the direction of <code>+X</code>
     * @return dir
     */
    Vector2f normalizedPositiveX(Vector2f dir);

    /**
     * Obtain the direction of <code>+Y</code> before the transformation represented by <code>this</code> matrix is applied.
     * <p>
     * This method uses the rotation component of the left 2x2 submatrix to obtain the direction
     * that is transformed to <code>+Y</code> by <code>this</code> matrix.
     * <p>
     * This method is equivalent to the following code:
     * <pre>
     * Matrix3x2f inv = new Matrix3x2f(this).invert();
     * inv.transformDirection(dir.set(0, 1)).normalize();
     * </pre>
     * If <code>this</code> is already an orthogonal matrix, then consider using {@link #normalizedPositiveY(Vector2f)} instead.
     * <p>
     * Reference: <a href="http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/threeD/">http://www.euclideanspace.com</a>
     *
     * @param dir will hold the direction of <code>+Y</code>
     * @return dir
     */
    Vector2f positiveY(Vector2f dir);

    /**
     * Obtain the direction of <code>+Y</code> before the transformation represented by <code>this</code> <i>orthogonal</i> matrix is applied.
     * This method only produces correct results if <code>this</code> is an <i>orthogonal</i> matrix.
     * <p>
     * This method uses the rotation component of the left 2x2 submatrix to obtain the direction
     * that is transformed to <code>+Y</code> by <code>this</code> matrix.
     * <p>
     * This method is equivalent to the following code:
     * <pre>
     * Matrix3x2f inv = new Matrix3x2f(this).transpose();
     * inv.transformDirection(dir.set(0, 1));
     * </pre>
     * <p>
     * Reference: <a href="http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/threeD/">http://www.euclideanspace.com</a>
     *
     * @param dir will hold the direction of <code>+Y</code>
     * @return dir
     */
    Vector2f normalizedPositiveY(Vector2f dir);

    /**
     * Unproject the given window coordinates <code>(winX, winY)</code> by <code>this</code> matrix using the specified viewport.
     * <p>
     * This method first converts the given window coordinates to normalized device coordinates in the range <code>[-1..1]</code>
     * and then transforms those NDC coordinates by the inverse of <code>this</code> matrix.
     * <p>
     * As a necessary computation step for unprojecting, this method computes the inverse of <code>this</code> matrix.
     * In order to avoid computing the matrix inverse with every invocation, the inverse of <code>this</code> matrix can be built
     * once outside using {@link #invert(Matrix3x2f)} and then the method {@link #unprojectInv(float, float, int[], Vector2f) unprojectInv()} can be invoked on it.
     *
     * @param winX     the x-coordinate in window coordinates (pixels)
     * @param winY     the y-coordinate in window coordinates (pixels)
     * @param viewport the viewport described by <code>[x, y, width, height]</code>
     * @param dest     will hold the unprojected position
     * @return dest
     * @see #unprojectInv(float, float, int[], Vector2f)
     * @see #invert(Matrix3x2f)
     */
    Vector2f unproject(float winX, float winY, int[] viewport, Vector2f dest);

    /**
     * Unproject the given window coordinates <code>(winX, winY)</code> by <code>this</code> matrix using the specified viewport.
     * <p>
     * This method differs from {@link #unproject(float, float, int[], Vector2f) unproject()}
     * in that it assumes that <code>this</code> is already the inverse matrix of the original projection matrix.
     * It exists to avoid recomputing the matrix inverse with every invocation.
     *
     * @param winX     the x-coordinate in window coordinates (pixels)
     * @param winY     the y-coordinate in window coordinates (pixels)
     * @param viewport the viewport described by <code>[x, y, width, height]</code>
     * @param dest     will hold the unprojected position
     * @return dest
     * @see #unproject(float, float, int[], Vector2f)
     */
    Vector2f unprojectInv(float winX, float winY, int[] viewport, Vector2f dest);

    /**
     * Test whether the given point <code>(x, y)</code> is within the frustum defined by <code>this</code> matrix.
     * <p>
     * This method assumes <code>this</code> matrix to be a transformation from any arbitrary coordinate system/space <code>M</code>
     * into standard OpenGL clip space and tests whether the given point with the coordinates <code>(x, y, z)</code> given
     * in space <code>M</code> is within the clip space.
     * <p>
     * Reference: <a href="http://gamedevs.org/uploads/fast-extraction-viewing-frustum-planes-from-world-view-projection-matrix.pdf">
     * Fast Extraction of Viewing Frustum Planes from the World-View-Projection Matrix</a>
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @return <code>true</code> if the given point is inside the frustum; <code>false</code> otherwise
     */
    boolean testPoint(float x, float y);

    /**
     * Test whether the given circle is partly or completely within or outside of the frustum defined by <code>this</code> matrix.
     * <p>
     * This method assumes <code>this</code> matrix to be a transformation from any arbitrary coordinate system/space <code>M</code>
     * into standard OpenGL clip space and tests whether the given sphere with the coordinates <code>(x, y, z)</code> given
     * in space <code>M</code> is within the clip space.
     * <p>
     * Reference: <a href="http://gamedevs.org/uploads/fast-extraction-viewing-frustum-planes-from-world-view-projection-matrix.pdf">
     * Fast Extraction of Viewing Frustum Planes from the World-View-Projection Matrix</a>
     *
     * @param x the x-coordinate of the circle's center
     * @param y the y-coordinate of the circle's center
     * @param r the circle's radius
     * @return <code>true</code> if the given circle is partly or completely inside the frustum; <code>false</code> otherwise
     */
    boolean testCircle(float x, float y, float r);

    /**
     * Test whether the given axis-aligned rectangle is partly or completely within or outside of the frustum defined by <code>this</code> matrix.
     * The rectangle is specified via its min and max corner coordinates.
     * <p>
     * This method assumes <code>this</code> matrix to be a transformation from any arbitrary coordinate system/space <code>M</code>
     * into standard OpenGL clip space and tests whether the given axis-aligned rectangle with its minimum corner coordinates <code>(minX, minY, minZ)</code>
     * and maximum corner coordinates <code>(maxX, maxY, maxZ)</code> given in space <code>M</code> is within the clip space.
     * <p>
     * Reference: <a href="http://old.cescg.org/CESCG-2002/DSykoraJJelinek/">Efficient View Frustum Culling</a>
     * <br>
     * Reference: <a href="http://gamedevs.org/uploads/fast-extraction-viewing-frustum-planes-from-world-view-projection-matrix.pdf">
     * Fast Extraction of Viewing Frustum Planes from the World-View-Projection Matrix</a>
     *
     * @param minX the x-coordinate of the minimum corner
     * @param minY the y-coordinate of the minimum corner
     * @param maxX the x-coordinate of the maximum corner
     * @param maxY the y-coordinate of the maximum corner
     * @return <code>true</code> if the axis-aligned box is completely or partly inside of the frustum; <code>false</code> otherwise
     */
    boolean testAar(float minX, float minY, float maxX, float maxY);

    /**
     * Compare the matrix elements of <code>this</code> matrix with the given matrix using the given <code>delta</code>
     * and return whether all of them are equal within a maximum difference of <code>delta</code>.
     * <p>
     * Please note that this method is not used by any data structure such as {@link ArrayList} {@link HashSet} or {@link HashMap}
     * and their operations, such as {@link ArrayList#contains(Object)} or {@link HashSet#remove(Object)}, since those
     * data structures only use the {@link Object#equals(Object)} and {@link Object#hashCode()} methods.
     *
     * @param m     the other matrix
     * @param delta the allowed maximum difference
     * @return <code>true</code> whether all of the matrix elements are equal; <code>false</code> otherwise
     */
    boolean equals(Matrix3x2fc m, float delta);

    /**
     * Determine whether all matrix elements are finite floating-point values, that
     * is, they are not {@link Float#isNaN() NaN} and not
     * {@link Float#isInfinite() infinity}.
     *
     * @return {@code true} if all components are finite floating-point values;
     * {@code false} otherwise
     */
    boolean isFinite();

}
