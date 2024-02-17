package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract class GameState{
    public abstract void update();

	public abstract void draw(Graphics g);

	// Key Listener
	public void keyPressed(KeyEvent e){};
	public void keyReleased(KeyEvent e){};

	// Mouse Listener
	public void mouseClicked(MouseEvent e){};
	public void mouseDragged(MouseEvent e){};

	public void mousePressed(MouseEvent e){};
	public void mouseReleased(MouseEvent e){};

	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};


}
