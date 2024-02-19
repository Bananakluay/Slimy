package dataStructure;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import components.Sprite;
@SuppressWarnings("rawtypes")
public class AssetPool {
    @SuppressWarnings("unchecked")
    public static Map<String, Sprite> sprites = new HashMap();
    //static Map(String, Level) levelsData = new HashMap(); 


    public static boolean hasSprite(String imgFile){
        return AssetPool.sprites.containsKey(imgFile);
    }

    public static Sprite getSprite(String imgFile){
        File file = new File(imgFile);
        if(AssetPool.hasSprite(imgFile)){
            return AssetPool.sprites.get(file.getAbsolutePath().toString());
        }
        else{
            Sprite sprite = new Sprite(imgFile);
            AssetPool.addSprite(imgFile, sprite);
            return AssetPool.sprites.get(file.getAbsolutePath());
        }
        
    }
    
    public static void addSprite(String imgFile, Sprite sprite){
        File file = new File(imgFile);
        if(!AssetPool.hasSprite(file.getAbsolutePath())){
            AssetPool.sprites.put(file.getAbsolutePath(), sprite);
        }
        else{
            System.out.println("Asset pool already has asset: " + file.getAbsolutePath());
            System.exit(-1);
        }
    }
}
