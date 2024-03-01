package level;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import main.Game;
import scene.LevelScene;
import scene.SceneManager;

public class LevelManager {

    private static boolean fadingOut = false;
    private static boolean fadingIn = false;
    private static int fadeSpeed = 3;
    private static boolean running_level = false;

    private static LevelManager levelManager = null;

    private static ArrayList<String> Map = new ArrayList<>();

    private static int lvlindex = 0;
    private static int highestReachedLevel = 30;

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
        if (running_level) {
            // SceneManager.fadingOut = true;
            fadingOut = true;
            running_level = false;
            return;
        }
        LevelScene.setup();
        new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
        running_level = true;
        // SceneManager.fadingIn = true;
        fadingIn = true;
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

    public static void updateFadeIn() {
        if (fadingIn) {
            // Perform fade-out
            if (Game.getFadeAlpha() - fadeSpeed >= 0) {
                Game.setFadeAlpha(Game.getFadeAlpha() - fadeSpeed);
            } else {
                fadingIn = false;

                // Change the scene here if needed
            }
        }
    }

    public static void updateFadeOut() {
        if (fadingOut) {
            // Perform fade-in
            if (Game.getFadeAlpha() + fadeSpeed <= 255) {
                Game.setFadeAlpha(Game.getFadeAlpha() + fadeSpeed);
            } else {
                System.out.println("Hi");
                LevelScene.clear();
                fadingOut = false;
                loadLevels();
                // Additional logic if needed after fade-in
            }
        }
    }

    public static boolean isFadingOut() {
        return fadingOut;
    }

    public static boolean isFadingIn() {
        return fadingIn;
    }
}