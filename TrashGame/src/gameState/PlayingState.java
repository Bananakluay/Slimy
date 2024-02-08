package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import entities.Player;
import levels.LevelManager;
import main.Game;
import simplePhysics.Area;
import simplePhysics.RigidBody;

public class PlayingState extends GameState{

    private Player player;
    private LevelManager level;
    private RigidBody box;
    public PlayingState(){
        scene();
    }
    public void scene(){
        level = new LevelManager();
        player = new Player(new Area(5*Game.TILES_SIZE, 5*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        RigidBody.setAreasInterection(level.getTiles());
        box = new RigidBody(new Area(7*Game.TILES_SIZE, 5*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE));
        RigidBody.setInterection(box);
        
    }

    @Override
    public void update() {
        player.update();
        level.update();
        box.update();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        level.draw(g);
        player.draw(g);

        g2d.setColor(Color.red);
        g2d.draw(box.getHitbox());
     

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
