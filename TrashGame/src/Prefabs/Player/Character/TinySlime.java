package prefabs.player.character;

import dataStructure.AssetPool;
import dataStructure.Transform;
import prefabs.player.Player;
import utils.Vec2;

import static prefabs.player.AnimationStatus.*;
import static utils.Constants.Game.*;
import static utils.Constants.Player.*;

import java.awt.image.BufferedImage;
import java.util.List;

import components.Animation;
import components.Controller;

public class TinySlime extends Player {

    private Animation animation;
    private List<BufferedImage> frames;
    private String file;

    public TinySlime(String name, float x, float y, String file) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE * 0.8f, TILES_SIZE / 2)));
        this.file = file;
        init();
    }

    private void init() {
        this.setMass(1.5f * SCALE);
        this.setFriction(0.35f);
        this.setMobility(WALK_SPEED, JUMP_FORCE * 0.9f);

        frames = AssetPool.getBufferedImageList(file, 16, 16);
        animation = this.getComponent(Animation.class);
        animation.setSize(TILES_SIZE, TILES_SIZE);
        animation.setScale(1.6f);
        animation.setOffset((int) (-4 * SCALE), (int) (-11 * SCALE + 1)); // 3.75
        animation.addAnimation("IDLE", 100, frames.subList(0, 2), false);
        animation.addAnimation("WALK", 20, frames.subList(8, 10), false);
        animation.addAnimation("JUMP", 100, frames.subList(18, 19), false);
        animation.addAnimation("FALL", 100, frames.subList(21, 22), false);
        animation.play("IDLE");
    }

    @Override
    public void update() {
        super.update();

        if (!this.getComponent(Controller.class).isActive()) {
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
