package dataStructure;

import utils.Vec2;

public class Transform {
    public Vec2 position; // x pos, y pos
    public Vec2 scale; //width, height

    public Transform(Vec2 position, Vec2 scale){
        this.position = position;
        this.scale = scale;
    }
}
