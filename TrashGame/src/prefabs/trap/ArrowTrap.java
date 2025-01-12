package prefabs.trap;

import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

import interaction.Behavior;
import prefabs.player.Player;
import scene.LevelScene;
import components.Detector;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class ArrowTrap extends Entity implements Behavior {

    int direction; // 0 : left, 1 : right, 2 : up, 3 : down

    boolean dubug = false;
    Random rand;

    public ArrowTrap(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)), TRAP);
        rand = new Random();
        direction = rand.nextInt(2);
        init();
    }

    public void init() {
        Detector detector = new Detector(
                transform.position.x,
                transform.position.y,
                transform.scale.x,
                transform.scale.y,
                Arrays.asList(EntityType.PLAYER),
                this,
                true);

        addComponent(detector);
    }

    @Override
    public void activateOneShot(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            ArrowRush arrowRush;

            // 0 : left, 1 : right, 2 : up, 3 : down
            if (direction == 0)
                arrowRush = new ArrowRush(
                        "ArrowRush",
                        0,
                        entity.getPosition().y + entity.getScale().y / 2,
                        direction);

            else 
                arrowRush = new ArrowRush(
                        "ArrowRush",
                        GAME_WIDTH,
                        entity.getPosition().y + entity.getScale().y / 2,
                        direction);


            LevelScene.getEntityManager().addEntity(arrowRush);

        }
    }

    @Override
    public void activateOff() {
    }

    @Override
    public void activateOn(Entity entity) {
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (dubug)
            g.drawRect((int) transform.position.x, (int) transform.position.y, (int) transform.scale.x,
                    (int) transform.scale.y);

    }

}
