package level;

import Scene.LevelScene;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class LevelManager {

    public static LevelManager levelManager = null;
    private static Level currentLevel;
    private static LevelScene levelScene;
    private LevelManager(LevelScene levelScene){
        LevelManager.levelScene = levelScene;
        init();
    }

    private void init(){
        //buildAllLevels();
        currentLevel = new Level("TrashGame/res/t4.png",levelScene);
    }

    public static LevelManager get(LevelScene levelScene){
        if(levelManager == null)
            levelManager = new LevelManager(levelScene);
        return levelManager;
    }
    private static ArrayList<String> Map = new ArrayList<>();
    
    private static int lvlindex = 0;

    private LevelManager() {
        init();
    }



    public static LevelManager get() {
        if (levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }

    public static Level getCurrentLevel() {
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
        if (lvlindex >= Map.size()) {
            lvlindex = -1;
            System.out.println("Game complete");
        }
        lvlindex++;
    }
}
