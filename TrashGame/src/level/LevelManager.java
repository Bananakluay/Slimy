package level;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import main.Game;
import scene.LevelScene;
import scene.SceneManager;
import scene.Scenes;

public class LevelManager {
    private static boolean reset = false, resett = true;
    private static boolean next = false, first = true;
    private static boolean fadingOut = false;
    private static boolean fadingIn = false;
    private static int fadeSpeed = 3;
    private static boolean running_level = false;

    private static LevelManager levelManager = null;

    private static ArrayList<String> Map = new ArrayList<>();

    private static int lvlindex = 0;
    private static int highestReachedLevel = 1;

    private static final String SAVE_FILE_PATH = "TrashGame/src/save/save.dat";

    private LevelManager() {
        init();
        System.out.println("size"+ Map.size());
    }

    public static LevelManager get() {
        if (levelManager == null)
            levelManager = new LevelManager();
        first = true;
        return levelManager;
    }

    private void init() {
        buildAllLevels();
        highestReachedLevel = loadLastPlayedLevel();

        System.out.println("Highest reached level: " + highestReachedLevel);
    }

    public static void loadLevels() {
        if (reset && resett) {
            System.out.println("restart");
            if (running_level) {
                // SceneManager.fadingOut = true;
                fadingOut = true;
                running_level = false;
                return;
            }
            if (!fadingOut) {
                System.out.println(lvlindex);
                
                System.out.println("TrashGame/res/lvls/" + Map.get(lvlindex));
                new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
                running_level = true;
                fadingIn = true;
                resett = false;
                reset = false;
            }
        } else if (next) {
            if (running_level) {
                fadingOut = true;
                running_level = false;
                return;
            }
            if (!fadingOut) {
                System.out.println(lvlindex);

                System.out.println("TrashGame/res/lvls/" + Map.get(lvlindex));
                new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
                running_level = true;
                fadingIn = true;
            }
        } else if (first) {
            System.out.println("firsty");
            System.out.println(lvlindex);
            System.out.println("TrashGame/res/lvls/" + Map.get(lvlindex));
            new Level("TrashGame/res/lvls/" + Map.get(lvlindex));
            first = false;
            running_level = true;
            fadingIn = true;
        }
    }

    public static void setLevel(int levelNumber) {
        lvlindex = levelNumber;
    }

    public static void loadNextLevels() {
        lvlindex++;
        if (lvlindex < Map.size()) {
            next = true;
            loadLevels();
            updateHighestReachedLevel();
            if (lvlindex + 1 > highestReachedLevel)
                saveLastPlayedLevel(lvlindex + 1);
        } else {
            updateHighestReachedLevel();
            SceneManager.changeScene(Scenes.END_SCENE);
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
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals("lvl" + (i + 1) + ".png")) {
                    filesSorted[i] = files[j];
                }
            }
        return filesSorted;
    }

    private static void buildAllLevels() {
        File[] allLevels = GetAllLevels();
        for (File img : allLevels) {
            System.out.println("img.getname" + img.getName());
            Map.add((String) img.getName());
        }

    }

    public static int getHighestReachedLevel() {
        return highestReachedLevel;
    }

    public static void resetLevel() {
        reset = true;
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
                resett = true;
                next = false;
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
                LevelScene.clear();
                LevelScene.setup();
                fadingOut = false;
                loadLevels();
                // Additional logic if needed after fade-in
            }
        }
    }

    public static void saveLastPlayedLevel(int level) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(SAVE_FILE_PATH));
            System.out.println("Saving level: " + level);
            dos.writeInt(level);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadLastPlayedLevel() {
        int level = 1;
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(SAVE_FILE_PATH));
            if (dis.available() >= Integer.BYTES) {
                level = dis.readInt();
            } else {
                System.out.println("Save file is empty or corrupted. Defaulting to level 1.");
            }
            dis.close();
        } catch (EOFException e) {
            System.out.println("End of file reached unexpectedly. Defaulting to level 1.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return level;
    }

    public static boolean isFadingOut() {
        return fadingOut;
    }

    public static boolean isFadingIn() {
        return fadingIn;
    }
}