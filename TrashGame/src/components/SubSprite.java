package components;

import java.util.ArrayList;
import java.util.List;

import dataStructure.AssetPool;

public class SubSprite extends Component{
    public List<Sprite> sprites;

    public int tileSize;

    public SubSprite(String imgfile, int tileSize){
        this.tileSize = tileSize;
        
        Sprite sprite = AssetPool.getSprite(imgfile,tileSize);
        sprites = new ArrayList<>();
        
        int nRow = sprite.getHeight()/tileSize;
        int nCol = sprite.getWidth()/tileSize;

		for (int row = 0; row < nRow; row++){
			for (int col = 0; col < nCol; col++){
                sprites.add(new Sprite(sprite.img.getSubimage(col*tileSize, row*tileSize, tileSize, tileSize)));
            }
        }

        
    }

    public Sprite getSprites(int index) {
        return sprites.get(index);
    }
}
