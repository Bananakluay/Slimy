package tileMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameData.Data;
import main.Game;
import simplePhysics.Area;

import static gameData.Data.TileData.*;

@SuppressWarnings("unused")
public class TileBlock extends Area {

    protected BufferedImage img;
    protected int offsetHbX = 0, offSetHbY = 0;
    protected int offsetImgX = 0, offsetImgY = 0;

    public TileBlock(float x, float y, int hitbox_width, int hitbox_height, BufferedImage img) {
        super(x, y, hitbox_width, hitbox_height);
        this.img = img;
    }

    public void draw(Graphics g) {
        if (img == null)
            g.drawRect((int) x, (int) y, Game.TILES_SIZE, Game.TILES_SIZE);
        else
            g.drawImage(img, (int) this.x - offsetImgX, (int) this.y - offSetHbY, Game.TILES_SIZE, Game.TILES_SIZE,
                    null);
    }

    public void setOffSetImage(int offSetX, int offSetY) {
        offsetImgX = offSetX;
        offsetImgY = offSetY;
    }

    public void setOffSetHitbox(int offSetX, int offSetY) {
        offsetHbX = offSetX;
        offSetHbY = offSetY;
    }

    public void reset() {
        x = 0;
        y = 0;
        offsetImgX = 0;
        offsetImgY = 0;
    }
}
