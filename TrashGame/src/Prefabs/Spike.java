package Prefabs;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import Behavior.Behavior;
import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class Spike extends Entity implements Behavior{
    BufferedImage img;
    public Spike(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE/2)), TRAP);
        init();
    }

    public void init(){

        Detector detector = new Detector(
            transform.position.x, 
            transform.position.y+TILES_SIZE/2, 
            transform.scale.x - SCALE, 
            transform.scale.y, 
            List.of(EntityType.PLAYER), 
            this);
        addComponent(detector);
        img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/spike.png", TILES_SIZE, TILES_SIZE);
        
    }
    
    @Override
    public void activate(Entity entity) {
        System.out.println(entity.getName());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(img, 
        (int)(this.getTransform().position.x), 
        (int)(this.getTransform().position.y),
        (int)(TILES_SIZE), 
        (int)(TILES_SIZE),
        null);
    }
    
}
