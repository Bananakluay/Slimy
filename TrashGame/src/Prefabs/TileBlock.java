package Prefabs;



import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import components.Bounds;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class TileBlock extends Entity{
    BufferedImage img;
    BufferedImage img2;
    float x2, y2;
    public TileBlock(BufferedImage img, float x, float y, BufferedImage img2, float x2, float y2) {
        super("Tile", new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)),3);
        this.img = img;
        this.img2 = img2;
        this.x2 = x2;
        this.y2 = y2;
        this.type = EntityType.TILE;
        init();
    }

    private void init(){
        addComponent(new Bounds(null));
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, (int)this.getTransform().position.x, (int)this.getTransform().position.y, (int)this.getTransform().scale.x, (int)this.getTransform().scale.y, null);
        g.drawImage(img, (int)this.getTransform().position.x, (int)this.getTransform().position.y, (int)this.getTransform().scale.x, (int)this.getTransform().scale.y, null);
    }


    
}
