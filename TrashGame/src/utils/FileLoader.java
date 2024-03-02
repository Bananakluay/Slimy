package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataStructure.AssetPool;

@SuppressWarnings("unused")
public class FileLoader {

    public static Font loadFont(String resource, int size){
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File(resource)).deriveFont(Font.PLAIN, size);
        }catch(FontFormatException | IOException e){
            e.printStackTrace();
            
        }
        return font;
        
    }
    public static BufferedImage loadBufferedImage(String resource) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(resource));
        }catch (IOException e) {
            System.out.println("GET Img : "+resource + " fail");
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage[] loadBufferImageList(String resource, int tileWidth, int tileHeight) {
        BufferedImage img = AssetPool.getBufferedImage(resource, tileWidth, tileHeight);

        int nRow = img.getHeight() / tileHeight;
        int nCol = img.getWidth() / tileWidth;

        // System.out.println(nRow + " " + nCol);

        BufferedImage[] imgList = new BufferedImage[nRow * nCol];
        int index = 0;
        for (int row = 0; row < nRow; row++) {
            for (int col = 0; col < nCol; col++)
            imgList[index++] = img.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight);
        }
        return imgList;
    }

}