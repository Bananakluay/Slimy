package dataStructure;

import file.*;

import java.awt.Font;
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
    private static final Map<String, Font> fonts = new HashMap<>();
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
    
    
    public static <T> void add(String imgFile, T asset){
        File file = new File(imgFile);
        if(asset instanceof BufferedImage b && !AssetPool.bufferImage.containsKey(imgFile)){
            AssetPool.bufferImage.put(file.getAbsolutePath(), b);
        }
        else if(asset instanceof Sprite s && !AssetPool.sprites.containsKey(imgFile)){
            AssetPool.sprites.put(file.getAbsolutePath(), s);
        }
        else{
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }

    public static Font getFont(String resource, int size)
    {
        Font font;

        if (!fonts.containsKey(resource))
        {
            font = FileLoader.loadFont(resource, size);
            fonts.put(resource, font);
        }
        else
        {
            if (fonts.get(resource).getSize() != size)
            {
                font = FileLoader.loadFont(resource, size);
                fonts.put(resource, font);
            }

            for (Map.Entry<String, Font> fontSet : fonts.entrySet())
            {
                if (fontSet.getKey().equals(resource) && fontSet.getValue().getSize() == size)
                    return fontSet.getValue();
            }
        }

        return null;
    }


}
