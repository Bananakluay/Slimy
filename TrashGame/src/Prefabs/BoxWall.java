package Prefabs;

import static util.Constants.Game.TILES_SIZE;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;
import util.Vec2;

public class BoxWall {
    Entity entity;
    float x,y,w,h;
    String name;
    public BoxWall(String name,float x, float y, float w, float h){
        this.x = TILES_SIZE*x;
        this.y = TILES_SIZE*y;
        this.w = TILES_SIZE*w;
        this.h = TILES_SIZE*h;
        this.name = name;
    }

    public Entity get(){
        Transform t = new Transform(new Vec2(x,y),new Vec2(w,h));
        entity = new Entity(name, t , 0);
        entity.addComponent(new Bounds());
        entity.addComponent(new Rigidbody(0.1f));
        entity.addComponent(new Physic2D());
        return entity;
    }
}
