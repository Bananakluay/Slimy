package prefabs.exits;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import interaction.Behavior;
import level.LevelManager;
import scene.LevelScene;
import utils.Vec2;

public class Door extends Entity implements Behavior {
    public LevelScene levelScene;
    List<BufferedImage> img;

    public Door(String name, float x, float y) {
        super(
                name,
                new Transform(new Vec2(x - 4 * SCALE, y - TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE * 2)),
                -2);
        init();
    }

    public void init() {
        Detector detector = new Detector(
                transform.position.x,
                transform.position.y + TILES_SIZE * 0.3f,
                transform.scale.x,
                transform.scale.y,
                List.of(EntityType.PLAYER),
                this, false);
        addComponent(detector);
        img = AssetPool.getBufferedImageList("TrashGame/res/assets/Object/door.png", 21, 32);
    }

    @Override
    public void activateOn(Entity entity) {
        System.out.println("This is door Exits");
        LevelManager.loadNextLevels();
    }

    @Override
    public void activateOff() {
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(
                img.get(1),
                (int) (this.getTransform().position.x),
                (int) (this.getTransform().position.y),
                (int) (21 * SCALE),
                (int) (32 * SCALE),
                null);

    }

    @Override
    public void activateOneShot(Entity entity) {
    }

}
