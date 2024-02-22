
package Prefabs.Player;

import dataStructure.Transform;

import entity.Entity;
import entity.EntityType;

import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
@SuppressWarnings("unused")
public class Player extends Entity{

    Controller controller;  
    private boolean isCurrentPlayer = false;
    public Player(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
        init();
    }

    private void init(){
        type = EntityType.PLAYER;
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(5f, 1.5f));
        addComponent(new Physic2D());
        addComponent(new Controller());

    }

    public void setMass(float mass){
        this.getComponent(Rigidbody.class).mass = mass;
    }

    public void setFriction(float friction){
        this.getComponent(Rigidbody.class).friction = friction;
    }

    public void setActive(boolean value){
        if(value == true)
            isCurrentPlayer = true;
        else    
            isCurrentPlayer = false;
        this.getComponent(Controller.class).isActive = value;
    }

    public void setMobility(float walkSpeed, float jumpForce){
        this.getComponent(Controller.class).setMobility(walkSpeed, jumpForce);
    }


    
}
