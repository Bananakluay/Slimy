package levels;

import java.awt.Graphics;

import gameData.Data;
import inputImage.Sprite;

public class LevelManager {
    private Level level;
    public LevelManager(){
        level = new Level(Sprite.loadSprite(Data.TileData.TEST_MAP));
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

    public Level getCurrentLevel(){
        return level;
    }
    

}
