package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.w3c.dom.events.MouseEvent;

import main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PausedState extends GameState{
    public void update(){

    }

    @Override
    public void draw(Graphics g){
        drawPausedScreen(g);
    }

    public void drawPausedScreen(Graphics g) {
        // Set font and color for the "PAUSED" text
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.RED);
        
        // Draw the "PAUSED" text in the center of the screen
        String pausedText = "PAUSED";
        int x = (Game.GAME_WIDTH - g.getFontMetrics().stringWidth(pausedText)) / 2;
        int y = Game.GAME_HEIGHT / 2;
        g.drawString(pausedText, x, y);
    }

    // Key Listener
	public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                GameStatesManager.setState(GameStatesManager.getGameStatesManager().getPlayingState());
                System.out.println("Swith to Playing State");
                break;
            case KeyEvent.VK_ESCAPE:
                GameStatesManager.setState(GameStatesManager.getGameStatesManager().getMenuState());
                System.out.println("Swith to Mune State"); 
                break;

        }
    }

	public void keyReleased(KeyEvent e){};

	// Mouse Listener
	public void mouseClicked(MouseEvent e){};
	public void mouseDragged(MouseEvent e){};

	public void mousePressed(MouseEvent e){};
	public void mouseReleased(MouseEvent e){};

	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};
}
