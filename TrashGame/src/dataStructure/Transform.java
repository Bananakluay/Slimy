package dataStructure;

import utils.Vec2;

public class Transform {
    public Vec2 position; // x pos, y pos
    public Vec2 scale; //width, height
    public Vec2 velocity;

    public Transform(Vec2 position, Vec2 scale){
        this.position = position;
        this.scale = scale;
        this.velocity = new Vec2(0, 0);
    }
}
