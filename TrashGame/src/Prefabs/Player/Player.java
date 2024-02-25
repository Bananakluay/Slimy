
package Prefabs.Player;

import dataStructure.Transform;

import entity.Entity;
import entity.EntityType;
import utils.Vec2;

import static entity.EntityType.PLAYER;
import static entity.EntityType.TILE;
import static utils.Constants.Game.SCALE;

import java.util.List;

import components.Animation;
import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;

@SuppressWarnings("unused")
public class Player extends Entity {

    Controller controller;

    private boolean isAlive = true;
    private Vec2 direction;
    protected AnimationStatus animationStatus = AnimationStatus.IDLE;
    public Player(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
        init();
    }

    private void init() {
        type = EntityType.PLAYER;
        direction = new Vec2(0, 0);
        addComponent(new Bounds(null,List.of(PLAYER)));
        addComponent(new Rigidbody(1.5f*SCALE, 0.5f*SCALE));
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
    public void setDirectionX(float x){
        direction.x = x;
    }
    public void setDirectionY(float y){
        direction.y = y;
    }
    public Vec2 getDirection() {
        return direction;
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
