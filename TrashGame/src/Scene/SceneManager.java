package Scene;

public class SceneManager {
    private static SceneManager sceneManager = null;
    
    private static Scene currentScene;
    private Scene testScene;


    private SceneManager(){
        init();
    }

    public static SceneManager get(){
        if(sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    public void init(){
        testScene = new TestScene();
        currentScene = testScene;
    }
    public void changeScene(){}

    public static Scene getCurrentScene(){return currentScene;}
}
