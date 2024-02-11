package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Game;
import simplePhysics.Area;

public class PlayerManager {
    Player greenSlime;
    Player yellowSlime;
    Player currentPlayer;
    Player lastPlayer;

    public PlayerManager() {
        setup();
    }

    public void setup() {
        greenSlime = new Player(new Area(5 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        greenSlime.setColor(Color.green);

        yellowSlime = new Player(new Area(15 * Game.TILES_SIZE, 5 * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        yellowSlime.setColor(Color.yellow);

        currentPlayer = greenSlime;
        currentPlayer.setIsCurrentPlayer();

    }

    public void update() {
        greenSlime.update();
        yellowSlime.update();
    }

    public void draw(Graphics g) {
        greenSlime.draw(g);
        yellowSlime.draw(g);
    }

    public void switchPlayer() {
        if (currentPlayer == greenSlime) {
            currentPlayer = yellowSlime;
            currentPlayer.setIsCurrentPlayer();
            lastPlayer = greenSlime;
            simulateMovementKey();
            greenSlime.reset();

        } else if (currentPlayer == yellowSlime) {
            currentPlayer = greenSlime;
            currentPlayer.setIsCurrentPlayer();
            lastPlayer = yellowSlime;
            simulateMovementKey();
            yellowSlime.reset();
        }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R)
            switchPlayer();
        currentPlayer.keyPressed(e);
    };

    public void keyReleased(KeyEvent e) {
        currentPlayer.keyReleased(e);
    };

    private void simulateMovementKey() {
        if (lastPlayer.Left) {
            currentPlayer.Left = true;
        } else if (lastPlayer.Right) {
            currentPlayer.Right = true;
        }
    }
}
