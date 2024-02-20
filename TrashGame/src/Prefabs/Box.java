package Prefabs;


import java.awt.Color;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import util.Vec2;

public class Box extends Entity{
    public Box(String name,float x, float y, float w, float h,Color color,float friction, boolean isMovable){
        super(name, new Transform(new Vec2(x, y), new Vec2(w, h)), 2);
        this.tag = EntityType.BOX;
        this.addComponent(new Bounds(color));
        this.addComponent(new Physic2D());
        if(isMovable)
            this.addComponent(new Rigidbody(friction));

    }


}
