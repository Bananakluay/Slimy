package util;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class InputImage {
    private final static int TILE_SIZE = 16;
    
    public static BufferedImage loadBufferedImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/" + file));

            if(img == null) 
                System.out.println("res/"+ file +" not found");
            else
                System.out.println("GET Img : "+"res/"+ file + " Success");
        } catch (IOException e) {
            System.out.println("GET Img : "+"res/"+ file + " fail");
            e.printStackTrace();
        }
        return img;
    }



    public static BufferedImage loadSprite(String file , int x, int y) {
        return loadBufferedImage(file).getSubimage(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static BufferedImage[] loadBufferImageList(String file) {
        BufferedImage sprite = loadBufferedImage(file);
        
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