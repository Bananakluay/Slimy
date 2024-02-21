package Prefabs;



import static utils.Constants.Game.TILES_SIZE;

import components.Bounds;
import components.Sprite;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class TileBlock extends Entity{
    Sprite sprite;
    public TileBlock(float x, float y, float width, float height, Sprite sprite) {
        super("Tile", new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)),3);
        this.sprite = sprite;
        this.type = EntityType.TILE;
        init();
    }

    private void init(){
        addComponent(sprite);
        addComponent(new Bounds(null));
    }
    


    
}
