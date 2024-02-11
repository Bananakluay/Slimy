package traps;

import java.awt.image.BufferedImage;

public class Thorn extends Trap{
    BufferedImage img;
    public Thorn(float x, float y, float width, float height, BufferedImage img) {
        super(x, y, width, height);
        this.img = img;
    }

    public void update(){
        detection();
    }

    @Override
    protected void activate(){
        System.out.println("You hit thorn");
    }
    
}
