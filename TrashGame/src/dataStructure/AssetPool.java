package dataStructure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import components.Sprite;
@SuppressWarnings("rawtypes")
public class AssetPool {
    @SuppressWarnings("unchecked")
    public static Map<String, Sprite> sprites = new HashMap();
    public static Map<String, BufferedImage> bufferImage = new HashMap<>();
    //static Map(String, Level) levelsData = new HashMap(); 




    public static Sprite getSprite(String imgFile, int tileSize){
        File file = new File(imgFile);
        if(AssetPool.sprites.containsKey(imgFile)){
            return AssetPool.sprites.get(file.getAbsolutePath().toString());
        }
        else{
            Sprite sprite = new Sprite(imgFile, tileSize);
            AssetPool.add(imgFile, sprite);
            return AssetPool.sprites.get(file.getAbsolutePath());
        }
        
    }

    public static BufferedImage getBufferedImage(String imgFile){
        File file = new File(imgFile);
        if(AssetPool.bufferImage.containsKey(imgFile)){
            return AssetPool.bufferImage.get(file.getAbsolutePath().toString());
        }
        else{
            BufferedImage bufferImg = null;
            try {
                bufferImg = ImageIO.read(file);

                if(bufferImg == null) 
                    System.out.println("res/"+ file +" not found");
                else
                    System.out.println("GET Img : "+"res/"+ file + " Success");
            } catch (IOException e) {
                System.out.println("GET Img : "+"res/"+ file + " fail");
                e.printStackTrace();
            }
            AssetPool.add(imgFile, bufferImg);
            return AssetPool.bufferImage.get(file.getAbsolutePath());
        }
    }
    
    
    public static <T> void add(String imgFile, T asset) {
        File file = new File(imgFile);
        if (asset instanceof BufferedImage && !AssetPool.bufferImage.containsKey(imgFile)) {
            AssetPool.bufferImage.put(file.getAbsolutePath(), (BufferedImage) asset);
        } 
        
        else if (asset instanceof Sprite && !AssetPool.sprites.containsKey(imgFile)) {
            AssetPool.sprites.put(file.getAbsolutePath(), (Sprite) asset);
        } 
        
        else {
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }


}
