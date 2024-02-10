package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameState.GameStatesManager;
import main.GamePanel;



public class KeyboardInputs implements KeyListener{
    private GamePanel GamePanel;

	public KeyboardInputs(GamePanel GamePanel){
        this.GamePanel = GamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
<<<<<<< Updated upstream
        gsm.getCurrentState().keyPressed(e);
=======
        switch (GameStatesManager.state) {
            case MENU:
                ((main.GamePanel) GamePanel).getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                ((main.GamePanel) GamePanel).getGame().PlayingState().keyPressed(e);
                break;
            default:
                break;
            }
>>>>>>> Stashed changes
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameStatesManager.state) {
            case MENU:
                GamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                GamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}
