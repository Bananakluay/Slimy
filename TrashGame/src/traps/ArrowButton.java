package traps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Player;

public class ArrowButton extends Trap{
    BufferedImage img;
    public ArrowButton(float x, float y, float width, float height, BufferedImage img) {
        super(x, y, width, height);
        this.img = img;
    }

    @Override
    public void update(){
        detection();
    }

    @Override 
    protected void activate(Player player){
        System.out.println("You hit ArrowButton");
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
