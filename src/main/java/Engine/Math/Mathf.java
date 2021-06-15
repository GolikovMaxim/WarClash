package Engine.Math;

public final class Mathf {
    public static final float PI = 3.1415926535897932384626f, PI_2 = PI / 2, RAD2DEG = 180f / PI, DEG2RAD = 1 / RAD2DEG, FLOAT_ERROR = 1e-6f;

    public static float lerp(float a, float b, float t) {
        t = clamp01(t);
        return lerpUnclamped(a, b, t);
    }

    public static float lerpUnclamped(float a, float b, float t) {
        return a * (1 - t) + b * t;
    }

    public static float clamp(float a, float min, float max) {
        if(a < min) return min;
        if(a > max) return max;
        return a;
    }

    public static float clamp01(float a) {
        return clamp(a, 0, 1);
    }

    public static float sqrt(float a) {
        return (float) Math.sqrt(a);
    }

    public static float sin(float a) {
        return (float) Math.sin(a);
    }

    public static float cos(float a) {
        return (float) Math.cos(a);
    }

    public static float asin(float a) {
        return (float) Math.asin(a);
    }

    public static float acos(float a) {
        return (float) Math.acos(a);
    }
}
