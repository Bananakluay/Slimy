package Scene;

import static util.Constants.TILES_SIZE;

import java.awt.Graphics;

import components.Spritesheet;
import components.TestComponent;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import util.Vec2;

public class TestScene extends Scene {


    public TestScene(){
        init();
    }

    @Override
    public void init() {
        Entity tE = new Entity(
            "Test",
            new Transform(new Vec2(2*TILES_SIZE,2*TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE))
            );
        Spritesheet spritesheet = new Spritesheet("TrashGame/res/TempTileSetV3.png", 16, 16);
        tE.addComponent(spritesheet.sprites.get(1));
        // tE.addComponent(AssetPool.getSprite("TrashGame/res/TempTileSetV3.png"));
        // tE.addComponent(new TestComponent());
        addEntity(tE);
    }

    @Override
    public void update() {
        for(Entity entity : entities){
            entity.update();

        }
    }

    @Override
    public void draw(Graphics g) {
        for(Entity entity : entities){
            entity.draw(g);
        }
    }

    
}
