package prefabs.trap;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import interaction.Behavior;
import prefabs.player.Player;
import scene.LevelScene;
import components.Detector;
import components.Rigidbody;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class ArrowRush extends Entity implements Behavior {

    BufferedImage img;
    float speed = SCALE * 3;
    int direction; // 0 : left, 1 : right, 2 : up, 3 : down
    Rigidbody rb;

    public ArrowRush(String name, float x, float y, int direction) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE * 0.3f)), 5);
        this.direction = direction;
        init();
    }

    public void init() {
        Detector detector;
        if (direction == 0 || direction == 1) {
            detector = new Detector(
                    this.getTransform().position.x,
                    this.getTransform().position.y,
                    this.getTransform().scale.x,
                    this.getTransform().scale.y,
                    Arrays.asList(EntityType.PLAYER),
                    this,
                    true);
        } else {
            detector = new Detector(
                    this.getTransform().position.x,
                    this.getTransform().position.y,
                    this.getTransform().scale.y,
                    this.getTransform().scale.x,
                    Arrays.asList(EntityType.PLAYER),
                    this,
                    true);
        }

        addComponent(detector);
        if (direction == 0)
            img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/dartright.png", TILES_SIZE, TILES_SIZE);
        else
            img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/dartleft.png", TILES_SIZE, TILES_SIZE);

        rb = new Rigidbody(0, 0);
        rb.setExtrmumXSpeed(speed);
        this.addComponent(rb);

        if (direction == 0)
            rb.velocity.x = speed;
        else if (direction == 1)
            rb.velocity.x = -speed;
        if (direction == 2)
            rb.velocity.y = speed;
        else if (direction == 3)
            rb.velocity.y = -speed;
    }

    @Override
    public void activateOneShot(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            player.die();
            player.getComponent(Rigidbody.class).addForce(new Vec2(rb.velocity.x, 0));
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(img,
                (int) (this.getTransform().position.x),
                (int) (this.getTransform().position.y),
                (int) (this.getTransform().scale.x),
                (int) (this.getTransform().scale.y),
                null);


    }

    @Override
    public void activateOn(Entity entity) {
    }

    @Override
    public void activateOff() {
    }

    @Override
    public void update() {
        super.update();
        this.getComponent(Detector.class).updatePos(this.getTransform().position.x, this.getTransform().position.y);

        // remove arrow if it's out of screen
        if (this.getTransform().position.x < -3 * TILES_SIZE ||
                this.getTransform().position.x > GAME_WIDTH + 3 * TILES_SIZE ||
                this.getTransform().position.y < -3 * TILES_SIZE ||
                this.getTransform().position.y > GAME_HEIGHT + 3 * TILES_SIZE) {
            LevelScene.getEntityManager().removeEntity(this);
        }
    }
}
