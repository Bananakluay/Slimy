package Prefabs;


import components.Bounds;
import components.Sprite;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import util.Vec2;

public class TileBlock extends Entity{
    Sprite sprite;
    public TileBlock(float x, float y, float width, float height, Sprite sprite) {
        super("Tile", new Transform(new Vec2(x, y), new Vec2(width, height)),3);
        this.sprite = sprite;
        this.tag = EntityType.TILE;
        init();
    }

    private void init(){
        addComponent(sprite);
        addComponent(new Bounds());
    }


    
}
