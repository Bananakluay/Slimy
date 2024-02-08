package TileMap;

import java.awt.image.BufferedImage;

public class Block extends Tile{
    public Block(float x, float y, int hitbox_width, int hitbox_height, BufferedImage img) {
        super(x, y, hitbox_width, hitbox_height);
        this.img = img;
    }
    
}
