package Scene;

public class SceneManager {
    private Scene currentScene;
    private TestScene testScene;
    public SceneManager(){
        init();
    }
    public void init(){
        testScene = new TestScene();
        currentScene = testScene;
    }
    public void changeScene(){}

    public Scene getCurrentScene(){return currentScene;}
}
