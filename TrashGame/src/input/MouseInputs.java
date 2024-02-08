package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameState.GameStatesManager;

// @SuppressWarnings("unused")
public class MouseInputs extends MouseAdapter{
    private GameStatesManager gsm;
    public MouseInputs(GameStatesManager gsm){
        this.gsm = gsm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gsm.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gsm.getCurrentState().mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gsm.getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gsm.getCurrentState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gsm.getCurrentState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gsm.getCurrentState().mouseExited(e);
    }



}
