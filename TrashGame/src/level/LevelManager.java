package level;




public class LevelManager {

    public static LevelManager levelManager = null;
    private static Level currentLevel;

    private LevelManager(){
        init();
    }

    private void init(){
        currentLevel = new Level("TrashGame/res/mapdataV2.png", "TrashGame/res/TempTileSetV3.png");
    }

    public static LevelManager get(){
        if(levelManager == null)
            levelManager = new LevelManager();
        return levelManager;
    }

    public static Level getCurrentLevel(){
        return currentLevel;
    }











}
