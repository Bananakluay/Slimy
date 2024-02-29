package level;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import scene.LevelScene;

public class LevelManager {

    private static LevelManager levelManager = null;

    private static ArrayList<String> Map = new ArrayList<>();

    private static int lvlindex = 0;
    private static int highestReachedLevel = 1;

    private LevelManager() {
        init();
    }

    public static LevelManager get() {
        if (levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }

    private void init() {
        buildAllLevels();
    }

    public static void loadLevels() {
        LevelScene.clear();
        LevelScene.setup();
        new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
    }

    public static void setLevel(int levelNumber) {
        lvlindex = levelNumber;
    }

    public static void loadNextLevels() {
        lvlindex++;
        if (lvlindex < Map.size()) {
            loadLevels();
            updateHighestReachedLevel();
        } else {
            System.out.println("Game complete");
        }
    }

    public static File[] GetAllLevels() {
        URL url = LevelManager.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {

            e.printStackTrace();
        }
        File[] files = file.listFiles();

        return files;
    }

    private static void buildAllLevels() {
        File[] allLevels = GetAllLevels();
        for (File img : allLevels) {
            Map.add((String) img.getName());
        }

    }

    public static int getHighestReachedLevel() {
        return highestReachedLevel;
    }

    public static void resetLevel() {
        loadLevels();
    }

    public static void onDestroy() {
        levelManager = null;
    }

    private static void updateHighestReachedLevel() {
        if (lvlindex > highestReachedLevel) {
            highestReachedLevel = lvlindex;
        }
    }
}