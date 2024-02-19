package main;

import javax.swing.JPanel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static util.Constants.Game.*;
public class GamePanel extends JPanel{

    private static GamePanel gamePanel = null;
    
    private Game game;
    private GamePanel(Game game){
        this.game = game;

        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        setBackground(Color.BLACK);

    }

    public static GamePanel get(Game game){
        if(gamePanel == null)
            gamePanel = new GamePanel(game);
        return gamePanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); //
        drawGrid(g);
        game.render(g);
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.GRAY);
        for(int col=0;col<GAME_WIDTH;col++)
            g.drawLine(col*TILES_SIZE, 0, col*TILES_SIZE, GAME_HEIGHT);

        for(int rol=0;rol<TILES_IN_HEIGHT;rol++)
            g.drawLine(0, rol*TILES_SIZE, GAME_WIDTH, rol*TILES_SIZE);
            g.setColor(Color.RED);
        
    }
}
