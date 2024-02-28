package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import dataStructure.AssetPool;

public class Sprite  extends Component{

    public BufferedImage img;

    public int offsetX = 0, offsetY = 0;


    public Sprite(String imgFile, int width, int height){
        this.img = AssetPool.getBufferedImage(imgFile, width, height);
        
    }
    public Sprite(BufferedImage img){
        this.img = img;
    }
    public void setOffset(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

   
    @Override
    public void draw(Graphics g) {
        int x = (int)entity.getTransform().position.x - offsetX;
        int y = (int)entity.getTransform().position.y - offsetY;
        int w = (int)entity.getTransform().scale.x;
        int h = (int)entity.getTransform().scale.y;
        g.drawImage(img, x,y,w,h, null);


    }


    

}
