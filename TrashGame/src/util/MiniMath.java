package util;

public class MiniMath {
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}
