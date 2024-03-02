package dataStructure;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import components.Sprite;
import utils.FileLoader;
public class AssetPool {
    public static Map<String, Sprite> sprites = new HashMap<>();
    public static Map<String, BufferedImage> bufferImage = new HashMap<>();
    public static Map<String, List<BufferedImage>> bufferImageList = new HashMap<>();
    public static Map<String, Font> fonts = new HashMap<>();

    
    public static Font getFont(String resource, int size){
        File file = new File(resource);
        
        //Font not found in Asset Pool
        if(!AssetPool.fonts.containsKey(resource)){

            Font font = FileLoader.loadFont(resource, size);
            AssetPool.add(resource, font);
            return AssetPool.fonts.get(file.getAbsolutePath());

        }else{// found in Asset Pool

            //Size not match
            if(fonts.get(resource).getSize() != size){
                Font font = FileLoader.loadFont(resource, size);
                fonts.put(resource, font);
            }
            
            //find same size
            for(Map.Entry<String,Font> fontSet : fonts.entrySet()){
                if(fontSet.getKey() == resource && fontSet.getValue().getSize() == size){
                    return AssetPool.fonts.get(file.getAbsolutePath().toString());
                }
            }
        }
        return null;
    }

    public static BufferedImage getBufferedImage(String resource, int tileWidth, int tileHeight){

        File file = new File(resource);
        //If the asset is already in the AssetPool, return it
        if(AssetPool.bufferImage.containsKey(resource)){
            return AssetPool.bufferImage.get(file.getAbsolutePath().toString());
        }
        else{//If the asset is not in the AssetPool, load it and add it to the AssetPool
            BufferedImage bufferImg = FileLoader.loadBufferedImage(resource);
            AssetPool.add(resource, bufferImg);
            return AssetPool.bufferImage.get(file.getAbsolutePath());
        }
    }

    public static List<BufferedImage> getBufferedImageList(String resource, int tileWidth, int tileHeight){

        File file = new File(resource);

        //If the asset is already in the AssetPool, return it
        if(AssetPool.bufferImageList.containsKey(resource)){
            return AssetPool.bufferImageList.get(file.getAbsolutePath().toString());
        }
        else{//If the asset is not in the AssetPool, load it and add it to the AssetPool

            BufferedImage bufferedImage = getBufferedImage(resource, tileWidth, tileHeight);
            int nRow = bufferedImage.getHeight()/tileHeight;
            int nCol = bufferedImage.getWidth()/tileWidth;

            List<BufferedImage> bufferImgList = new ArrayList<>();

		    for (int row = 0; row < nRow; row++){
			    for (int col = 0; col < nCol; col++) 
                    bufferImgList.add(bufferedImage.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight));
			}
            AssetPool.add(resource, bufferImgList);
            return AssetPool.bufferImageList.get(file.getAbsolutePath());
        }
        
    }
    
    private static void add(String resource, List<BufferedImage> asset){
        File file = new File(resource);
        if(asset instanceof List<BufferedImage> b && !AssetPool.bufferImageList.containsKey(resource)){
            AssetPool.bufferImageList.put(file.getAbsolutePath(), b);
        }else{
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }
    
    private static <T> void add(String resource, T asset){
        File file = new File(resource);
        if(asset instanceof BufferedImage b && !AssetPool.bufferImage.containsKey(resource)){
            AssetPool.bufferImage.put(file.getAbsolutePath(), b);

        }else if(asset instanceof Sprite s && !AssetPool.sprites.containsKey(resource)){
            AssetPool.sprites.put(file.getAbsolutePath(), s);

        }else if(asset instanceof Font f && !AssetPool.fonts.containsKey(resource)){
            AssetPool.fonts.put(file.getAbsolutePath(), f);

        }else{
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }


}
