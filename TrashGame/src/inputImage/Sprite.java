package inputImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class Sprite {
    private final static int TILE_SIZE = 32;
    
    public static BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File("TrashGame/res/" + file));

            if(sprite == null) 
                System.out.println("TrashGame/res/"+ file +" not found");
            else
                System.out.println("GET Img : "+"src/res/"+ file + " Success");
        } catch (IOException e) {
            System.out.println("GET Img : "+"res/"+ file + " fail");
            e.printStackTrace();
        }
        return sprite;
    }



    public static BufferedImage loadSprite(String file , int x, int y) {
        return loadSprite(file).getSubimage(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }



    
    public static BufferedImage[] loadSpriteAsList(String file) {
        BufferedImage sprite = loadSprite(file);
        
        int nRow = sprite.getHeight()/TILE_SIZE;
        int nCol = sprite.getWidth()/TILE_SIZE;

        System.out.println(nRow + " " + nCol);

		BufferedImage[] spriteList = new BufferedImage[nRow*nCol]; 
        int index = 0;
		for (int row = 0; row < nRow; row++){
			for (int col = 0; col < nCol; col++) 
                spriteList[index++] = sprite.getSubimage(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
        return spriteList;
	}


}