package level;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import scene.LevelScene;

public class LevelManager {

    private static LevelManager levelManager = null;

    private static ArrayList<String> Map = new ArrayList<>();

    private static Level currentLevel;

    private static int lvlindex = 0;

    private static Timer timer;

    public static boolean isGameOver = false;
    private LevelManager() {
        init();
    }
    
    public static LevelManager get() {
        if (levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }
    private void init() {
        getCurrentLevel();
        buildAllLevels();
        currentLevel = new Level("TrashGame/res/lvls/6Tmap.png");
    }

    public Level getCurrentLevel() {
        return currentLevel;
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

    private void buildAllLevels() {
        File[] allLevels = GetAllLevels();
        for (File img : allLevels) {
            Map.add((String) img.getName());
        }

    }

    public static void loadNextLevels() {
        if (lvlindex < Map.size()) {
            // LevelScene.deleteCurrentLevel();
            LevelScene.createNextLevel();
            currentLevel = new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
            lvlindex++;
        } else {
            System.out.println("Game complete");
        }
    }

    public static void resetLevel(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Code to load and display the new scene
                LevelScene.createNextLevel();
                currentLevel = new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
                timer.cancel();
            }
        }, 2000);
        
    }

    public static void onDestroy() {
        levelManager = null;
    }
}