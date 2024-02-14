package levels;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Player;
import entities.PlayerManager;
import gameData.Data;
import inputImage.Sprite;
import simplePhysics.Area;

public class LevelManager {
    private Level level;
    public LevelManager(PlayerManager player){
        level = new Level(Sprite.loadSprite(Data.TileData.MAP_DATA), player);
    }

    public void update(){
        level.update();
    }

    public void draw(Graphics g){
        level.draw(g);
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<Area> getTiles(){
        return level.getTiles();
    }

}
