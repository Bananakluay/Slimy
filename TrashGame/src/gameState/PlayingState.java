package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Box;
import entities.PlayerManager;
import levels.LevelManager;
import main.Game;
import simplePhysics.Area;

public class PlayingState extends GameState{

    private PlayerManager player;
    private LevelManager level;

    private Box box, box2,box3; // test

    public PlayingState(){
        scene();
    }
    public void scene(){

        level = new LevelManager();
        player = new PlayerManager();
        
        box = new Box(new Area(7*Game.TILES_SIZE, 5*Game.TILES_SIZE, 2*Game.TILES_SIZE, 2*Game.TILES_SIZE));
        box2 = new Box(new Area(7*Game.TILES_SIZE, 10*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        box3 = new Box(new Area(15*Game.TILES_SIZE, 10*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        
      
    }

    @Override
    public void update() {
        level.update();
        player.update();

        box.update();
        box2.update();
        box3.update();
    }

    @Override
    public void draw(Graphics g) {
        level.draw(g);
        player.draw(g);
        
        box.draw(g);
        box2.draw(g);
        box3.draw(g);
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
