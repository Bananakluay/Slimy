package dataStructure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import components.Sprite;
import utils.InputImage;
@SuppressWarnings("rawtypes")
public class AssetPool {
    @SuppressWarnings("unchecked")
    public static Map<String, Sprite> sprites = new HashMap();
    public static Map<String, BufferedImage> bufferImage = new HashMap<>();
    @SuppressWarnings("unchecked")
    public static Map<String, List<BufferedImage>> bufferImageList = new HashMap();
    //TODO static Map(String, Level) levelsData = new HashMap(); 




    public static BufferedImage getBufferedImage(String imgFile, int tileWidth, int tileHeight){
        File file = new File(imgFile);
        if(AssetPool.bufferImage.containsKey(imgFile)){
            return AssetPool.bufferImage.get(file.getAbsolutePath().toString());
        }
        else{
            BufferedImage bufferImg = InputImage.loadBufferedImage(imgFile);
            AssetPool.add(imgFile, bufferImg);
            return AssetPool.bufferImage.get(file.getAbsolutePath());
        }
    }

    public static List<BufferedImage> getBufferedImageList(String imgFile, int tileWidth, int tileHeight){
        File file = new File(imgFile);
        if(AssetPool.bufferImageList.containsKey(imgFile)){
            return AssetPool.bufferImageList.get(file.getAbsolutePath().toString());
        }
        else{

            BufferedImage bufferedImage = getBufferedImage(imgFile, tileWidth, tileHeight);
            int nRow = bufferedImage.getHeight()/tileHeight;
            int nCol = bufferedImage.getWidth()/tileWidth;

            List<BufferedImage> res = new ArrayList<>();

		    for (int row = 0; row < nRow; row++){
			    for (int col = 0; col < nCol; col++) 
                    res.add(bufferedImage.getSubimage(col * tileWidth, row * tileHeight, tileWidth, tileHeight));
			}

            return res;
        }
        
    }
    
    
    public static <T> void add(String imgFile, T asset) {
        File file = new File(imgFile);
        if (asset instanceof BufferedImage && !AssetPool.bufferImage.containsKey(imgFile)) {
            AssetPool.bufferImage.put(file.getAbsolutePath(), (BufferedImage) asset);
        } else if (asset instanceof Sprite && !AssetPool.sprites.containsKey(imgFile)) {
            AssetPool.sprites.put(file.getAbsolutePath(), (Sprite) asset);
        } else {
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }


}
