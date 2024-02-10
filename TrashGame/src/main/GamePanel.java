package main;

import javax.swing.JPanel;

import input.KeyboardInputs;
import input.MouseInputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;
    
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
    }
    

    private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        game.render(g);
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.GRAY);
        for(int col=0;col<Game.GAME_WIDTH;col++)
            g.drawLine(col*Game.TILES_SIZE, 0, col*Game.TILES_SIZE, Game.GAME_HEIGHT);

        for(int rol=0;rol<Game.TILES_IN_HEIGHT;rol++)
            g.drawLine(0, rol*Game.TILES_SIZE, Game.GAME_WIDTH, rol*Game.TILES_SIZE);
            g.setColor(Color.RED);
        
    }

    public Game getGame() {
		return game;
	}


}
