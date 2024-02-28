package prefabs.trap;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import interaction.Behavior;
import prefabs.player.Player;
import components.Detector;
import components.Rigidbody;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import utils.Constants.Layer;

public class ArrowRush extends Entity implements Behavior {

    BufferedImage img;
    float speed = 1.0f;
    int direction; // 0 : left, 1 : right, 2 : up, 3 : down

    public ArrowRush(String name, float x, float y, int direction) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE * 0.3f)), Layer.TRAP);

        init();
    }

    public void init() {
        Detector detector = new Detector(
                this.getTransform().position.x,
                this.getTransform().position.y,
                this.getTransform().scale.x,
                this.getTransform().scale.y,
                Arrays.asList(EntityType.PLAYER),
                this,
                false);
        addComponent(detector);

        img = AssetPool.getBufferedImage("TrashGame/res/Object/door.png", TILES_SIZE, TILES_SIZE);

        Rigidbody rb = new Rigidbody(0, 0);
        this.addComponent(rb);

        if (direction == 0)
            rb.velocity.x = -speed;
        else if (direction == 1)
            rb.velocity.x = speed;
        else if (direction == 2)
            rb.velocity.y = -speed;
        else if (direction == 3)
            rb.velocity.y = speed;
    }

    @Override
    public void activateOneShot(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            System.out.println(player.getName() + " hit arrow");
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
    }
}
