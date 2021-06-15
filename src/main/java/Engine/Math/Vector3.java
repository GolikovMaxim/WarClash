package Engine.Math;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter @Setter
public class Vector3 {
    public static final Vector3
            zero    = new Vector3(),
            one     = new Vector3(1),
            left    = new Vector3(-1, 0 ,0),
            right   = new Vector3(1, 0, 0),
            down    = new Vector3(0, -1, 0),
            up      = new Vector3(0, 1, 0),
            back    = new Vector3(0, 0, -1),
            forward = new Vector3(0, 0, 1);

    private float x, y, z;

    //Static methods
    public Vector3() {
        x = y = z = 0;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float value) {
        x = y = z = value;
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 sub(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 mul(Vector3 a, Vector3 b) {
        return new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    public static Vector3 div(Vector3 a, Vector3 b) {
        return new Vector3(a.x / b.x, a.y / b.y, a.z / b.z);
    }

    public static Vector3 add(Vector3 a, float b) {
        return new Vector3(a.x + b, a.y + b, a.z + b);
    }

    public static Vector3 sub(Vector3 a, float b) {
        return new Vector3(a.x - b, a.y - b, a.z - b);
    }

    public static Vector3 mul(Vector3 a, float b) {
        return new Vector3(a.x * b, a.y * b, a.z * b);
    }

    public static Vector3 div(Vector3 a, float b) {
        return new Vector3(a.x / b, a.y / b, a.z / b);
    }

    public static Vector3 neg(Vector3 a) {
        return new Vector3(-a.x, -a.y, -a.z);
    }

    public static float dot(Vector3 a, Vector3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vector3 lerp(Vector3 a, Vector3 b, float t) {
        return new Vector3(
                Mathf.lerp(a.x, b.x, t),
                Mathf.lerp(a.y, b.y, t),
                Mathf.lerp(a.z, b.z, t)
        );
    }

    public static float distance(Vector3 a, Vector3 b) {
        return sub(a, b).magnitude();
    }

    public static float magnitudeSquared(Vector3 a) {
        return a.x * a.x + a.y * a.y + a.z * a.z;
    }

    public static float magnitude(Vector3 a) {
        return Mathf.sqrt(magnitudeSquared(a));
    }

    public static Vector3 normalize(Vector3 a) {
        float l = magnitude(a);
        return new Vector3(a.x / l, a.y / l, a.z / l);
    }

    public static float angle(Vector3 a, Vector3 b) {
        float cos = dot(a.normalized(), b.normalized());
        return Mathf.acos(cos);
    }

    public static Vector3 rotate(Vector3 a, Vector3 axis, float angle) {
        return new Matrix3x3(axis, angle).mul(a);
    }

    public static Vector3 rotate(Vector3 a, Vector3 euler) {
        return new Matrix3x3(euler).mul(a);
    }

    //Non-static methods
    public Vector3 add(Vector3 a) {
        x += a.x;
        y += a.y;
        z += a.z;
        return this;
    }

    public Vector3 sub(Vector3 a) {
        x -= a.x;
        y -= a.y;
        z -= a.z;
        return this;
    }

    public Vector3 mul(Vector3 a) {
        x *= a.x;
        y *= a.y;
        z *= a.z;
        return this;
    }

    public Vector3 div(Vector3 a) {
        x /= a.x;
        y /= a.y;
        z /= a.z;
        return this;
    }

    public Vector3 add(float a) {
        x += a;
        y += a;
        z += a;
        return this;
    }

    public Vector3 sub(float a) {
        x -= a;
        y -= a;
        z -= a;
        return this;
    }

    public Vector3 mul(float a) {
        x *= a;
        y *= a;
        z *= a;
        return this;
    }

    public Vector3 div(float a) {
        x /= a;
        y /= a;
        z /= a;
        return this;
    }

    public Vector3 neg() {
        x *= -1;
        y *= -1;
        z *= -1;
        return this;
    }

    public float magnitude() {
        return magnitude(this);
    }

    public Vector3 normalized() {
        return normalize(this);
    }

    @SneakyThrows @Override
    public Vector3 clone() {
        return new Vector3(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3 (" + x + ", " + y + ", " + z + ")";
    }
}
