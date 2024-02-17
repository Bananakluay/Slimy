package gameState;

public class GameStatesManager {

    private static GameStatesManager instance;

    private MenuState menu;
    private PlayingState playing;
    private GameState paused;

    private static GameState currentState;

    public GameStatesManager(){
        playing = new PlayingState();
        menu = new MenuState();
        paused = new PausedState();
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

    public GameState getMenuState() {
        return menu;
    }

    public GameState getPauseState() {
        return paused;
    }
}
