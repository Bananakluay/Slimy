package Prefabs.Objects;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import Interaction.Behavior;
import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class ArrowRush extends Entity implements Interaction.Behavior{

    BufferedImage img;

    public ArrowRush(String name, float x, float y, float initialSpeedX, float initialSpeedY) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE / 2)), TRAP);

        // ตั้งความเร็วเริ่มต้น
        this.getTransform().velocity.x = initialSpeedX;
        this.getTransform().velocity.y = initialSpeedY;

        init();
    }


    public void init() {
        img = AssetPool.getBufferedImage("TrashGame/res/arrow.png", TILES_SIZE, TILES_SIZE);
    }
    


    @Override
    public void activate(Entity entity) {
        System.out.println(entity.getName() + " hit arrow");
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
    

    @Override
    public void update() {
        // เคลื่อนที่ลูกธนู
        Vec2 velocity = this.getTransform().velocity;
        this.getTransform().position.add(velocity);

        // this.getTransform().position.x += this.getTransform().velocity.x;
        // this.getTransform().position.y += this.getTransform().velocity.y;
    }
    
}
