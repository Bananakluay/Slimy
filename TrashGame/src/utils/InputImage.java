package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataStructure.AssetPool;

@SuppressWarnings("unused")
public class InputImage {

    public static BufferedImage loadBufferedImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));

            if(img == null) 
                System.out.println(file +" not found");
            else
                System.out.println("GET Img : "+file + " Success");
        } catch (IOException e) {
            System.out.println("GET Img : "+file + " fail");
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage[] loadBufferImageList(String file, int tileWidth, int tileHeight) {
        BufferedImage img = AssetPool.getBufferedImage(file, tileWidth, tileHeight);

        int nRow = img.getHeight() / tileHeight;
        int nCol = img.getWidth() / tileWidth;

        System.out.println(nRow + " " + nCol);

        BufferedImage[] imgList = new BufferedImage[nRow * nCol];
        int index = 0;
        for (int row = 0; row < nRow; row++) {
            for (int col = 0; col < nCol; col++)
            imgList[index++] = img.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
        }
        return imgList;
    }

}