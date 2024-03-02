
package prefabs.player;

import dataStructure.Transform;

import entity.Entity;
import entity.EntityType;
import sound.Sound;
import utils.Vec2;
import utils.Constants.Layer;

import static entity.EntityType.PLAYER;
import static entity.EntityType.TILE;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Player.WALK_SPEED;

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

    public Player(String name, Transform transform) {
        super(name, transform, Layer.PLAYER);
        init();
    }

    private void init() {
        type = EntityType.PLAYER;
        direction = new Vec2(0, 0);
        addComponent(new Bounds(null, List.of(PLAYER)));
        addComponent(new Rigidbody(1.5f * SCALE, 0.5f * SCALE));
        addComponent(new Physic2D());
        addComponent(new Controller());
        addComponent(new Animation());
        this.getComponent(Rigidbody.class).extremumSpeed = 2f;

    }

    public boolean isActive() {
        return this.getComponent(Controller.class).isActive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setStatus(boolean status) {

        this.isAlive = status;
    }

    public void setAnimation(AnimationStatus status) {
        this.animationStatus = status;
    }

    public void die() {
        if (isActive()) {
            PlayerManager.switchPlayer();
        }
        if (isAlive) {
            this.isAlive = false;
            this.setActive(false);
            PlayerManager.resetIfDead();
            Sound.DEAD.play(false);
        }


    }

    public void setDirectionX(float x) {
        direction.x = x;
    }

    public void setDirectionY(float y) {
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
