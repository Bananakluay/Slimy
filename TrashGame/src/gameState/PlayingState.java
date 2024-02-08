package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import simplePhysic.Area;

public class PlayingState extends GameState{

    private Player player;

    public PlayingState(){
        scene();
    }
    public void scene(){
        player = new Player(new Area(50, 50, 50, 50));
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}






    
    
}
