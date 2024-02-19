package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dataStructure.AssetPool;

public class Sprite  extends Component{
    public BufferedImage img;
    public String imgFile;
    public int width, height;
    public int tileSize;
    public int offsetX = 0, offsetY = 0;

    public List<Sprite> subSprite = new ArrayList<>();

    public Sprite(String imgFile, int tileSize){

        this.imgFile = imgFile;

        try{
            File file = new File(imgFile);
            if(AssetPool.sprites.containsKey(imgFile))
                System.out.println("Asset already exitst:" + imgFile);
            this.img = ImageIO.read(file);
            this.width = img.getWidth();
            this.height = img.getHeight();
        }catch(IOException e){
            System.out.println("CAN'T GET IMAGE : " + imgFile);
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public void setOffset(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Sprite(BufferedImage img){
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }


    @Override
    public void draw(Graphics g) {
        int x = (int)entity.getTransform().position.x - offsetX;
        int y = (int)entity.getTransform().position.y - offsetY;
        int w = (int)entity.getTransform().scale.x;
        int h = (int)entity.getTransform().scale.y;
        g.drawImage(img, x,y,w,h, null);
        // Transform t = entity.getTransform();
        // g2d.draw(new Rectangle((int)t.position.x,(int)t.position.y, (int)t.scale.x, (int)t.scale.y));

    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    

}
