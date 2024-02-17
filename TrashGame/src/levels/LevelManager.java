package levels;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import gameData.Data;
import inputImage.Sprite;
import simplePhysics.Area;

public class LevelManager {
    public static final String loadNextLevels = null;
    private Level level;
    private static int lvlindex = 0;
    private static ArrayList<String> Map = new ArrayList<>();

    public LevelManager() {
        buildAllLevels();
        level = new Level(Sprite.loadSprite("/lvls/" + Map.get(lvlindex)));
    }

    public void update() {
        level.update();
    }

    public void draw(Graphics g) {
        level.draw(g);
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<Area> getTiles() {
        return level.getTiles();
    }

    private void buildAllLevels() {
        File[] allLevels = Data.GetAllLevels();
        for (File img : allLevels) {
            Map.add((String) img.getName());
        }

    }

    public static void loadNextLevels() {
        if (lvlindex >= Map.size()) {
            lvlindex = -1;
            System.out.println("Game complete");
        }
        lvlindex++;
    }

    public static void loadPreviousLevels() {
        lvlindex--;
    }

    public void reset() {
        level.reset();
    }
}
