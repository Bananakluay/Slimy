package prefabs.player.character;

import dataStructure.AssetPool;
import dataStructure.Transform;
import prefabs.player.Player;
import utils.Vec2;

import static utils.Constants.Game.*;
import static utils.Constants.Player.*;

import java.awt.image.BufferedImage;
import java.util.List;

import components.Animation;

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
        this.setMass(10f * SCALE);
        this.setFriction(0.35f);
        this.setMobility(WALK_SPEED * 0.6f, JUMP_FORCE * 0.9f);

        frames = AssetPool.getBufferedImageList(file, 16, 16);
        animation = this.getComponent(Animation.class);
        animation.setSize(TILES_SIZE, TILES_SIZE);
        animation.setScale(1.6f);
        animation.setOffset((int) (-4 * SCALE), (int) (-11 * SCALE + 1)); 
        animation.addAnimation("IDLE", 100, frames.subList(0, 2), false);
        animation.addAnimation("WALK", 20, frames.subList(3, 5), false);
        animation.addAnimation("JUMP", 100, frames.subList(6, 7), false);
        animation.addAnimation("FALL", 100, frames.subList(7, 8), false);
        animation.addAnimation("DEAD", 100, frames.subList(9, 11), false);
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
            case DEAD:
                animation.play("DEAD");
                break;
            default:
                break;
        }
    }

}
