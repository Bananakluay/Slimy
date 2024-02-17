package traps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Player;
import inputImage.Sprite;
import static gameData.Data.TileData.*;

public class Spike extends Trap{
    BufferedImage img = Sprite.loadSprite(SPIKES_DATA);
    public Spike(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.img = img;
    }

    @Override
    public void update(){
        detection();
    }

    @Override 
    protected void activate(Player player){
        System.out.println("You hit spikes");
        //player.died();
       
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (img == null) {
            g2d.fill(this);
        }
        else {
            g2d.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
        }
        

    }
}
