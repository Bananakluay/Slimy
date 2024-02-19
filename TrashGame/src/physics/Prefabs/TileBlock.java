package physics.Prefabs;

import static util.Constants.Game.TILES_SIZE;

import components.Sprite;
import dataStructure.Transform;
import entity.Entity;
import util.Vec2;

public class TileBlock extends Entity{
    Sprite sprite;
    public TileBlock(float x, float y, float width, float height, Sprite sprite) {
        super("Tile", new Transform(new Vec2(x, y), new Vec2(width, height)),3);
        this.sprite = sprite;
        init();
    }

    private void init(){
        addComponent(sprite);
    }


    
}
