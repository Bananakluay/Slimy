package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameState.GameStatesManager;


public class KeyboardInputs extends KeyAdapter{
    private GameStatesManager gsm;

	public KeyboardInputs(GameStatesManager gsm){
        this.gsm = gsm;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.getCurrentState().keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        gsm.getCurrentState().keyReleased(e);
    }
}
