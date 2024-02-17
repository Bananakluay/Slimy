package gameState;

public class GameStatesManager {

    private static GameStatesManager instance;

    private MenuState menu;
    private PlayingState playing;
    private PausedState paused;

    private static GameState currentState;

    public GameStatesManager(){
        playing = new PlayingState();
        setState(playing);
    }

    public static void setState(GameState state){
        currentState = state;
    }

    public GameState getCurrentState(){
        return currentState;
    }

    public static GameStatesManager getGameStatesManager(){
        if (instance == null) {
            instance = new GameStatesManager();
        }
        return instance;
    }

    public GameState getPlayingState() {
        return playing;
    }
}
