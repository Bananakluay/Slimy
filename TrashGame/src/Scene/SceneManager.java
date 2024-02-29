package Scene;

import Prefabs.Player.PlayerManager;
import main.GamePanel;

import java.util.logging.Level;

import Level.LevelManager;
import Prefabs.Spike;

public class SceneManager {
    private static boolean fadingOut = false;
    private static boolean fadingIn = false;
    private static int fadeSpeed = 2;
    private static SceneManager sceneManager = null;

    private static Scene currentScene = null;

    private SceneManager() {
        init();
    }

    public static SceneManager get() {
        if (sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    public void init() {
        changeScene(Scenes.LEVEL_SCENE);
    }

    public static void changeScene(Scenes scene) {
        if (currentScene != null) {
            fadingIn = true;
            // currentScene = null;
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
            case TEST_SCENE:
                System.out.println("TEST Scene");
                currentScene = new TestScene();
                break;
            default:
                break;
        }
        fadingOut = true;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void NextScene() {
        LevelManager.loadNextLevels();
        changeScene(Scenes.LEVEL_SCENE);
        //PlayerManager.get(currentScene);
    }

    public static void restart() {
        if (Spike.died_blue) {
            LevelManager.onDestroy();
            PlayerManager.onDestroy();
            currentScene = new LevelScene();
            PlayerManager.get(currentScene);
            Spike.died_blue = false;

        } else if (Spike.died_green && Spike.died_yellow) {
            LevelManager.onDestroy();
            PlayerManager.onDestroy();
            currentScene = new LevelScene();
            PlayerManager.get(currentScene);
            Spike.died_yellow = false;
            Spike.died_green = false;
        }
    }

    public static boolean isFadingOut() {
        return fadingOut;
    }

    public static boolean isFadingIn() {
        return fadingIn;
    }

    public static void updateFade() {
        if (fadingOut) {
            // Perform fade-out
            if (GamePanel.getFadeAlpha() - fadeSpeed >= 0) {
                GamePanel.setFadeAlpha(GamePanel.getFadeAlpha() - fadeSpeed);
            } else {
                fadingOut = false;
                // Change the scene here if needed
            }
        } else if (fadingIn) {
            // Perform fade-in
            if (GamePanel.getFadeAlpha() + fadeSpeed <= 255) {
                GamePanel.setFadeAlpha(GamePanel.getFadeAlpha() + fadeSpeed);
            } else {
                fadingIn = false;
                currentScene = null;
                changeScene(Scenes.LEVEL_SCENE);
                // Additional logic if needed after fade-in
            }
        }
    }
}
