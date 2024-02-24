package Prefabs.Exits;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import Interaction.Behavior;
import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import level.LevelManager;
import Scene.LevelScene;

public class Door extends Entity implements Behavior {
    public LevelScene levelScene;
    List<BufferedImage> img;
    public Door(String name, Transform transform) {
        super(name, transform, 1);
        init();
    }

    public void init() {
        Detector detector = new Detector(
                transform.position.x,
                transform.position.y + TILES_SIZE*0.3f,
                transform.scale.x,
                transform.scale.y*1.7f,
                List.of(EntityType.PLAYER),
                this);
        addComponent(detector);
        img = AssetPool.getBufferedImageList("TrashGame/res/assets/Object/door3.png", 21, 32);
    }

    @Override
    public void activateOn(Entity entity) {
        System.out.println("This is door Exits");
        LevelManager.loadNextLevels();
    }
    
    @Override
    public void activateOff() {}
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(
            img.get(1), 
            (int)(this.getTransform().position.x), 
            (int)(this.getTransform().position.y),
            (int)(21*SCALE), 
            (int)(32*SCALE), 
            null);
    }

}
