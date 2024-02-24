package Prefabs.Player.Character;

import dataStructure.AssetPool;
import dataStructure.Transform;
import utils.Vec2;

import static Prefabs.Player.AnimationStatus.IDLE;
import static utils.Constants.Game.*;
import static utils.Constants.Layer.PLAYER_LAYER;
import static utils.Constants.Player.*;

import java.awt.image.BufferedImage;
import java.util.List;

import Prefabs.Player.Player;
import components.Animation;
import components.Bounds;
import components.Controller;

public class TinySlime extends Player{

 
    private Animation animation;
    private List<BufferedImage> frames;
    private String file;
    public TinySlime(String name, float x, float y, String file) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE,TILES_SIZE*0.7f)), PLAYER_LAYER);
        this.file = file;
        init();
    }
    
    private void init(){
        this.setMass(5f);
        this.setFriction(1.3f);
        this.setMobility(WALK_SPEED, JUMP_FORCE);

        frames = AssetPool.getBufferedImageList(file, 16, 16);
        animation = this.getComponent(Animation.class);
        animation.setSize(TILES_SIZE,TILES_SIZE);
        animation.setScale(2);
        animation.setOffset((int)(-4*SCALE), (int)(-10*SCALE)); //3.75
        animation.addAnimation("IDLE", 100, frames.subList(0, 2));
        animation.addAnimation("WALK", 20, frames.subList(8, 10));
        animation.addAnimation("JUMP", 100, frames.subList(18, 19));
        animation.addAnimation("FALL", 100, frames.subList(21, 22));
        animation.play("IDLE");
    }

    @Override
    public void update() {
        super.update();

        if(!this.getComponent(Controller.class).isActive()){
            animationStatus = IDLE;
        }

        switch (animationStatus) {
            case IDLE: 
                animation.play("IDLE");
                break;
            case MOVING:
                animation.play("WALK");
                break;
            case JUMPING:
                animation.play("JUMP");
                break;
            case FALLING:
                animation.play("FALL");
                break;
            default:
                break;
        }
    }
  
    
}
