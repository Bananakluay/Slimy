package Prefabs.Player.Character;

import dataStructure.AssetPool;
import dataStructure.Transform;
import utils.Vec2;

import static utils.Constants.Game.*;
import static utils.Constants.Layer.PLAYER_LAYER;
import static utils.Constants.Player.*;
import java.awt.image.BufferedImage;
import java.util.List;

import Prefabs.Player.Player;
import components.Animation;


public class LargeSlime extends Player{

    private Animation animation;
    private List<BufferedImage> frames;

    public LargeSlime(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(1.3f*TILES_SIZE,1.3f*TILES_SIZE)), PLAYER_LAYER);
        init();

    }

    private void init(){
        this.setMass(30);
        this.setFriction(2);
        this.setMobility(WALK_SPEED*0.8f, JUMP_FORCE*0.8f);

        
        // //animation
        frames = AssetPool.getBufferedImageList("TrashGame/res/assets/Character/GreenSlime.png", 16, 16);
        animation = this.getComponent(Animation.class);
        animation.setSize(1.3f*TILES_SIZE, 1.3f*TILES_SIZE);
        animation.setScale(2);
        animation.setOffset((int)(-5*SCALE), (int)(-10*SCALE));
        animation.addAnimation("IDLE", 100, frames.subList(0, 2));
        animation.addAnimation("WALK", 80, frames.subList(8, 10));
        animation.addAnimation("JUMP", 100, frames.subList(18, 19));
        animation.addAnimation("FALL", 100, frames.subList(21, 22));
        animation.play("IDLE");
        
    }

    @Override
    public void update() {
        super.update();
        if(currenStatus == status)
            return;

        switch (status) {
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
        currenStatus = status;
    }
        
}

   
    


