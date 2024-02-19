package Scene;
public class SceneManager {
    private static SceneManager sceneManager = null;
    
    private static Scene currentScene;


    private SceneManager(){
        init();
    }

    public static SceneManager get(){
        if(sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    public void init(){
        changeScene(Scenes.TEST_SCENE);
    }
    public void changeScene(Scenes scene){
        switch (scene) {
            case MENU_SCENE:
                currentScene = new MenuScene();
                break;
            case LEVEL_SCENE:
                currentScene = new LevelScene();
            case TEST_SCENE:
                currentScene = new TestScene();
            default:
                break;
        }
    }

    public static Scene getCurrentScene(){return currentScene;}
}
