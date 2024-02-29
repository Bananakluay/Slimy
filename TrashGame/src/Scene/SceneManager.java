package scene;

public class SceneManager {
    private static SceneManager sceneManager = null;

    private static Scene currentScene;

    private SceneManager() {
        init();
    }

    public static SceneManager get() {
        if (sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    public void init() {
        changeScene(Scenes.MENU_SCENE);
    }

    public static void changeScene(Scenes scene) {
        if (currentScene != null) {
            currentScene.onDestroy();
            currentScene = null;
        }
        switch (scene) {
            case MENU_SCENE:
                System.out.println("Menu Scene");
                currentScene = new MenuScene();
                break;
            case LEVEL_SCENE:
                System.out.println("LEVEL Scene");
                currentScene = new LevelScene();
                break;
            case OPTION_SCENE:
                System.out.println("OPTION Scene");
                currentScene = new OptionScene();
                break;
            case LEVEL_SELECT_SCENE:
                System.out.println("LEVEL SELECT SCENE");
                currentScene = new LevelSelectScene();
                break;
            default:
                break;
        }
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }
}
