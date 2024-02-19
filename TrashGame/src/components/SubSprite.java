package components;

import java.util.ArrayList;
import java.util.List;

import dataStructure.AssetPool;

public class SubSprite extends Component{
    public List<Sprite> sprites;

    public int tileWidth;
    public int tileHeight;

    public SubSprite(String imgfile, int tileWidth, int tileHeight){
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        
        Sprite sprite = AssetPool.getSprite(imgfile);
        sprites = new ArrayList<>();
        
        int nRow = sprite.getHeight()/tileHeight;
        int nCol = sprite.getWidth()/tileWidth;

        // System.out.println(nRow + " " + nCol);

		for (int row = 0; row < nRow; row++){
			for (int col = 0; col < nCol; col++){
                sprites.add(new Sprite(sprite.img.getSubimage(col, row, tileWidth, tileHeight)));
            }
        }


        
    }

    public Sprite getSprites(int index) {
        return sprites.get(index);
    }
}
