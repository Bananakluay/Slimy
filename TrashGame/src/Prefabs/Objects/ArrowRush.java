package Prefabs.Objects;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import Interaction.Behavior;
import Prefabs.Player.Player;
import components.Detector;
import components.Rigidbody;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class ArrowRush extends Entity implements Interaction.Behavior {

    BufferedImage img;

    public ArrowRush(String name, float x, float y, float initialSpeedX, float initialSpeedY) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE*0.3f)), TRAP);  

        init();
    }

    public void init() {
        Detector detector = new Detector(
            this.getTransform().position.x, 
            this.getTransform().position.y, 
            this.getTransform().scale.x, 
            this.getTransform().scale.y,
            Arrays.asList(EntityType.PLAYER),
            this);
        addComponent(detector);

        img = AssetPool.getBufferedImage("TrashGame/res/something/plate.png", TILES_SIZE, TILES_SIZE);

        this.addComponent(new Rigidbody(0, 0));
        this.getComponent(Rigidbody.class).addForce(new Vec2(-5, 0));
    }

    @Override
    public void activate(Entity entity) {
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
        if (entity instanceof Player) {
                Player player = (Player) entity;
                player.die();
                System.out.println(entity.getName() + " hit arrow");
        }

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
