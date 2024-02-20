package Prefabs;

<<<<<<< HEAD:TrashGame/src/physics/Prefabs/TileBlock.java
import static util.Constants.Game.TILES_SIZE;

=======

import components.Bounds;
>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66:TrashGame/src/Prefabs/TileBlock.java
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
        addComponent(new Bounds(null));
    }


    
}
