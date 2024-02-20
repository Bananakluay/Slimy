package components;

import java.util.ArrayList;
import java.util.List;

import dataStructure.AssetPool;

public class SubSprite extends Component{
    public List<Sprite> sprites;

<<<<<<< HEAD
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


=======
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

>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66
        
    }

    public Sprite getSprites(int index) {
        return sprites.get(index);
    }
}
