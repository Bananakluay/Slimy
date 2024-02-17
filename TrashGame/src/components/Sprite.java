package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dataStructure.AssetPool;
import dataStructure.Transform;

import static util.Constants.Game.*;
public class Sprite  extends Component{
    public BufferedImage img;
    public String imgFile;
    public int width, height;
    public Sprite(String imgFile){

        this.imgFile = imgFile;

        try{
            File file = new File(imgFile);
            if(AssetPool.hasSprite(imgFile))
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

    public Sprite(BufferedImage img){
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(entity.getTransform().position.x, entity.getTransform().position.y);
        transform.scale(SCALE, SCALE);

        g2d.drawImage(img, transform, null);
        Transform t = entity.getTransform();
        g2d.draw(new Rectangle((int)t.position.x,(int)t.position.y, (int)t.scale.x, (int)t.scale.y));

    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    

}
