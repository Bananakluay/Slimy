package Prefabs;


import java.awt.Color;
import java.awt.Graphics;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
public class Box extends Entity{
    public Box(String name,float x, float y, float w, float h,Color color,float mass, float friction){
        super(name, new Transform(new Vec2(x, y), new Vec2(w, h)), 2);
        this.type = EntityType.BOX;
        this.addComponent(new Bounds(color));
        this.addComponent(new Physic2D());
        this.addComponent(new Rigidbody(mass, friction));

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawRect(
            (int)this.getTransform().position.x, 
            (int)this.getTransform().position.y,
            (int)this.getTransform().scale.x, 
            (int)this.getTransform().scale.y);
    }


}
