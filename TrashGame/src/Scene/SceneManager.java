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
        changeScene(Scenes.LEVEL_SCENE);
    }
    public void changeScene(Scenes scene){
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
    }

    public static Scene getCurrentScene(){return currentScene;}
}
