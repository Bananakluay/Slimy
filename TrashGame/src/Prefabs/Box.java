package Prefabs;


import java.awt.Color;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
public class Box extends Entity{
    public Box(String name,float x, float y, float w, float h,Color color,float mass, float friction, boolean isMovable){
        super(name, new Transform(new Vec2(x, y), new Vec2(w, h)), 10);
        this.type = EntityType.BOX;
        this.addComponent(new Bounds(color));
        this.addComponent(new Physic2D());
        if(isMovable)
            this.addComponent(new Rigidbody(mass, friction));

    }


}
