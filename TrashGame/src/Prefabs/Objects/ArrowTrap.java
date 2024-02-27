package Prefabs.Objects;

import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import Interaction.Behavior;
import Prefabs.Player.Player;
import Scene.LevelScene;
import Scene.SceneManager;
import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class ArrowTrap extends Entity implements Behavior {

    BufferedImage img;

    public ArrowTrap(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE / 2)), TRAP);
        init();
    }

    public void init() {
        Detector detector = new Detector(
                transform.position.x,
                transform.position.y + TILES_SIZE / 2,
                transform.scale.x - SCALE,
                transform.scale.y,
                Arrays.asList(EntityType.PLAYER),
                this);
        addComponent(detector);
        img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/spike.png", TILES_SIZE, TILES_SIZE);
    }

    @Override
    public void activateOn(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            System.out.println(player.getName() + " hit arrowtrap");
            
            ArrowRush arrowRush = new ArrowRush("ArrowRush", GAME_WIDTH, this.getTransform().position.y+15, -1.0f, 0.0f);

            LevelScene.getEntityManager().addEntity(arrowRush);

        }
    }

    @Override
    public void activateOff() {
        // Leave it empty if not needed
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
    public void activate(Entity entity) {
        System.out.println("ArrowTrap activated!");
        
    }
}
