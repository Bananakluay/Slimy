package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gameState.GameStatesManager;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
    
    private GamePanel GamePanel;
    
    public MouseInputs(GamePanel GamePanel){
        this.GamePanel = GamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStatesManager.state) {
            case PLAYING:
                GamePanel.getGame().getPlaying().mouseDragged(e);
                break;
            default:
                break;
    
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStatesManager.state) {
            case MENU:
                GamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                GamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameStatesManager.state) {
            case PLAYING:
                GamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStatesManager.state) {
            case MENU:
                GamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                GamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStatesManager.state) {
            case MENU:
                GamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                GamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
    
            }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }



}
