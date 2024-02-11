package gameState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UI.MenuButton;
import main.Game;

public class State {
    
    protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public Game getGame() {
		return game;
	}
}
