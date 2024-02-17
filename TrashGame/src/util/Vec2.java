package util;

public class Vec2 {
    public float x;
    public float y;

    public Vec2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void add(Vec2 vec2){
        this.x += vec2.x;
        this.y += vec2.y;
    }

    
}
