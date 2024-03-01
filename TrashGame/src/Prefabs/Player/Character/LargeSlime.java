package Prefabs.Player.Character;

import dataStructure.AssetPool;
import dataStructure.Transform;
import Prefabs.Player.Player;
import utils.Vec2;

import static utils.Constants.Game.*;
import static utils.Constants.Player.*;
import java.awt.image.BufferedImage;
import java.util.List;

import components.Animation;

public class LargeSlime extends Player {

    private Animation animation;
    private List<BufferedImage> frames;

    public LargeSlime(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE * 0.9f)));
        init();

    }

    private void init() {
        this.setMass(30);
        this.setFriction(0.07f);
        this.setMobility(WALK_SPEED * 0.44f, JUMP_FORCE * 0.75f);

        // animation
        frames = AssetPool.getBufferedImageList("TrashGame/res/assets/Character/BlueSlime.png", 16, 16);
        animation = this.getComponent(Animation.class);
        animation.setSize(TILES_SIZE, TILES_SIZE);
        animation.setScale(2f);
        animation.setOffset((int) (-4 * SCALE), (int) (-9 * SCALE + 1)); // 3 = 28
        animation.addAnimation("IDLE", 100, frames.subList(0, 2), false);
        animation.addAnimation("WALK", 80, frames.subList(8, 10), false);
        animation.addAnimation("JUMP", 100, frames.subList(18, 19), false);
        animation.addAnimation("FALL", 100, frames.subList(21, 22), false);
        animation.play("IDLE");

    }

    @Override
    public void update() {
        super.update();

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
