package gameState;

@SuppressWarnings("unused")
public class GameStatesManager {

    private MenuState menu;
    private PlayingState playing;
    private PausedState paused;
    private GameState currenState;

    public GameStatesManager() {
        playing = new PlayingState();
        setState(playing);

    }

    public void setState(GameState state) {
        currenState = state;
    }

    public GameState getCurrentState() {
        return currenState;
    }

    public GameStatesManager getGameStatesManager() {
        return this;
    }

}
