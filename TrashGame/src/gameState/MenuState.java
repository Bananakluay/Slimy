package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;

public class MenuState extends GameState{
    @Override
    public void draw(Graphics g) {
        //System.out.println("Drawing menu..."); // check drwing menu
        g.setColor(Color.white);
        g.drawString("MENU", Game.GAME_WIDTH / 2, 200);
    }

    @Override
    public void update() {
        
    }

    // Key Listener
	public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            GameStatesManager.setState(GameStatesManager.getGameStatesManager().getPlayingState());

    };
	public void keyReleased(KeyEvent e){};

	// Mouse Listener
	public void mouseClicked(MouseEvent e){};
	public void mouseDragged(MouseEvent e){};

	public void mousePressed(MouseEvent e){};
	public void mouseReleased(MouseEvent e){};

	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};
    
}
