package traps;

import java.awt.image.BufferedImage;

import entities.Player;

public class Spike extends Trap{
    BufferedImage img;
    public Spike(float x, float y, float width, float height, BufferedImage img) {
        super(x, y, width, height);
        this.img = img;
    }

    public void update(){
        detection();
    }

    @Override
    protected void activate(Player player){

        System.out.println("You hit thorn");
    }
    
}
