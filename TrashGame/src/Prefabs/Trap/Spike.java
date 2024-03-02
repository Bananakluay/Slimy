package prefabs.trap;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import interaction.Behavior;
import prefabs.player.Player;
import utils.Vec2;

public class Spike extends Entity implements Behavior {
    
    private BufferedImage img;

    public Spike(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE * 0.55f)), TRAP);
        init();
    }

    public void init() {

        Detector detector = new Detector(
                transform.position.x + SCALE,
                transform.position.y + (TILES_SIZE * 0.45f),
                transform.scale.x - 2 * SCALE,
                transform.scale.y,
                List.of(EntityType.PLAYER),
                this, false);
        addComponent(detector);
        img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/spike.png", TILES_SIZE, TILES_SIZE);

    }

    @Override
    public void activateOn(Entity entity) {
        if (entity instanceof Player player) {
            player.die();
        }

    }

    @Override
    public void activateOff() {
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(img,
                (int) (this.getTransform().position.x),
                (int) (this.getTransform().position.y),
                (int) (TILES_SIZE),
                (int) (TILES_SIZE),
                null);
    }

    @Override
    public void activateOneShot(Entity entity) {
    }

}
