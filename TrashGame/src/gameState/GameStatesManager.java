package gameState;
@SuppressWarnings("unused")
public class GameStatesManager {


    private TestState testState;
    private GameState currenState;

    public GameStatesManager(){
        testState = new TestState();
        setState(testState);

        
    }

    public void setState(GameState state){
        currenState = state;
    }
    public GameState getCurrentState(){
        return currenState;
    }

    public GameStatesManager getGameStatesManager(){
        return this;
    }

}
