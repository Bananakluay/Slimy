package scene;

import main.Game;

public class SceneManager {
    public static boolean fadingOut = false;
    public static boolean fadingIn = false;
    private static int fadeSpeed = 3;
    private static Scenes Change = null;
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
            // currentScene.onDestroy();
            // currentScene = null;
            fadingOut = true;
            Change = scene;
            return;
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
        fadingIn = true;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static boolean isFadingOut() {
        return fadingOut;
    }

    public static boolean isFadingIn() {
        return fadingIn;
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
                fadingOut = false;
                currentScene.onDestroy();
                currentScene = null;
                changeScene(Change);
                // Additional logic if needed after fade-in
            }
        }
    }
}
