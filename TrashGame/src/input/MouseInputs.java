package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gameState.GameStatesManager;
import main.GamePanel;

// @SuppressWarnings("unused")
public class MouseInputs implements MouseListener, MouseMotionListener{
    private GamePanel GamePanel;

    public MouseInputs(main.GamePanel GamePanel){
        this.GamePanel = GamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameStatesManager.state) {
            case MENU:
                GamePanel.getGame().getMenu().mouseClicked(e);
                break;
            case PLAYING:
                GamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }



}
