package TileMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameData.Data;
import main.Game;
import simplePhysic.Area;

import static gameData.Data.TileData.*;
@SuppressWarnings("unused")
public class Tile extends Area{
    protected BufferedImage img;
    protected int type;
    public Tile(float x, float y, int hitbox_width, int hitbox_height){
        super(x, y, hitbox_width, hitbox_height);
    }

    public void draw(Graphics g){
        if (img == null)
            g.drawRect((int)x, (int)y, Game.TILES_SIZE, Game.TILES_SIZE);
        else
            g.drawImage(img, (int)this.x, (int)this.y,Game.TILES_SIZE,Game.TILES_SIZE, null);
    }

    public void update(){
        
    }
}
