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
    Vec2 minorPos;
    boolean option;
    public TileBlock(BufferedImage img, Vec2 mainPos, BufferedImage img2, Vec2 minorPos, boolean option) {
        super("Tile", new Transform(mainPos, new Vec2(TILES_SIZE, TILES_SIZE)),3);
        this.img = img;
        this.img2 = img2;
        this.minorPos = minorPos;
        this.type = EntityType.TILE;
        this.option = option;
        init();
    }

    private void init(){
        addComponent(new Bounds(null));
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, (int)this.getTransform().position.x, (int)this.getTransform().position.y, (int)this.getTransform().scale.x, (int)this.getTransform().scale.y, null);
        if(option)
            g.drawImage(img2, (int)minorPos.x, (int)minorPos.y, (int)this.getTransform().scale.x, (int)this.getTransform().scale.y, null);
    }


    
}
