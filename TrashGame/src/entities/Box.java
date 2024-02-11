package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import simplePhysics.Area;
import simplePhysics.RigidBody;

public class Box extends RigidBody{

    public Box(Area hitbox) {
        super(hitbox);
    }
    @Override
    public void update(){
        velX *= 0.8;
        super.update();
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fill(hitbox);
        // g2d.setColor(Color.red);
        // g2d.draw(getBoundsX());
        // g2d.setColor(Color.blue);
        // g2d.draw(getBoundsY());

    }
    
}
