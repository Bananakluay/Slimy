
package Prefabs.Player;

import dataStructure.Transform;

import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import components.Animation;
import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;

@SuppressWarnings("unused")
public class Player extends Entity {

    Controller controller;

    private boolean isAlive = true;

    protected AnimationStatus animationStatus = AnimationStatus.IDLE;

    public Player(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
        init();
    }

    private void init() {
        type = EntityType.PLAYER;
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(5f, 1.5f));
        addComponent(new Physic2D());
        addComponent(new Controller());
        addComponent(new Animation());

    }

    

    public boolean isActive(){
        return this.getComponent(Controller.class).isActive;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setStatus(boolean status){

        this.isAlive = status;
    }
    
    public void setAnimation(AnimationStatus status) {
        this.animationStatus = status;
    }

    public void die(){
        if(isAlive && isActive())
            PlayerManager.switchPlayer();
        this.isAlive = false;
        this.animationStatus = AnimationStatus.DEAD;
    }

    public Vec2 getDirection() {
        float directionX = Math.signum(this.getComponent(Rigidbody.class).velocity.x);
        float directionY = Math.signum(this.getComponent(Rigidbody.class).velocity.y);
        return new Vec2(directionX, directionY);
    }

    public void setMass(float mass) {
        this.getComponent(Rigidbody.class).mass = mass;
    }

    public void setFriction(float friction) {
        this.getComponent(Rigidbody.class).friction = friction;
    }

    public void setActive(boolean value) {
        this.getComponent(Controller.class).isActive = value;
    }

    public void setMobility(float walkSpeed, float jumpForce) {
        this.getComponent(Controller.class).setMobility(walkSpeed, jumpForce);
    }

}
