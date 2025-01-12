package prefabs.objects;

import static utils.Constants.Game.SCALE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import utils.Constants.Layer;

public class Box extends Entity {
    BufferedImage img;
    Random rand = new Random();

    public Box(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(19 * SCALE, 19 * SCALE)), Layer.OBJECTS);
        this.type = EntityType.BOX;
        this.addComponent(new Bounds(null, null));
        this.addComponent(new Physic2D());
        this.addComponent(new Rigidbody(5f * SCALE, 0.2f));
        this.transform.scale.x += rand.nextInt(5);
        img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/Box19.png", 19, 19);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(
                img,
                (int) this.getPosition().x,
                (int) this.getPosition().y,
                (int) this.getScale().x,
                (int) this.getScale().y,
                null);
    }

}
