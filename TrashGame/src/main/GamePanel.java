package main;

import javax.swing.JPanel;

import input.KeyboardInputs;
import input.MouseInputs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel{
    private Game game;
    public GamePanel(Game game){
        this.game = game;

        setPreferredSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new KeyboardInputs(game.getGameStatesManager()));
        addMouseListener(new MouseInputs(game.getGameStatesManager()));


    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); //
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
}
