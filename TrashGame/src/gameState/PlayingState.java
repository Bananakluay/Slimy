package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.Player;
import gameData.Data;
import inputImage.Sprite;
import levels.LevelManager;
import main.Game;
import simplePhysic.Area;
import simplePhysic.RigidBody;

public class PlayingState extends GameState{

    private Player player;
    private LevelManager levelmg;
    private BufferedImage img = Sprite.loadSprite(Data.PlayerData.PLAYERSET);
    public PlayingState(){
        scene();
    }
    public void scene(){
        levelmg = new LevelManager();
        player = new Player(new Area(300, 300, (int)(Game.TILES_SIZE), (int)(Game.TILES_SIZE)));
        //RigidBody.setBlocksInterection(levelmg.getLevel().getBlocks());


    }

    @Override
    public void update() {
        player.update();
        //levelmg.update();
    
    }   

    @Override
    public void draw(Graphics g) {

        player.draw(g);
        //levelmg.draw(g);
        // g.drawImage(img, 300, 300, (int)(img.getWidth()*Game.SCALE),(int)(img.getHeight()*Game.SCALE),null);
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
