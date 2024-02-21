package level;
import java.util.List;

import Scene.LevelScene;


public class LevelManager {

    public static LevelManager levelManager = null;
    private static Level currentLevel;
    private static LevelScene levelScene;
    private LevelManager(LevelScene levelScene){
        LevelManager.levelScene = levelScene;
        init();
    }

    private void init(){
        currentLevel = new Level("TrashGame/res/Tmap.png", "TrashGame/res/Wall.png", levelScene);
    }

    public static LevelManager get(LevelScene levelScene){
        if(levelManager == null)
            levelManager = new LevelManager(levelScene);
        return levelManager;
    }

    public static Level getCurrentLevel(){
        return currentLevel;
    }











}
