package Scene;

import static util.Constants.TILES_SIZE;

import java.awt.Graphics;

import components.Bounds;
import components.Physic2D;
import components.Rigidbody;
import components.Spritesheet;
import components.TestComponent;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import util.Vec2;

public class TestScene extends Scene {


    public TestScene(){
        init();
        ready();
    }

    @Override
    public void init() {

        Entity tE = new Entity(
            "Test",
            new Transform(new Vec2(2*TILES_SIZE,2*TILES_SIZE), new Vec2(120, 200)),
            0);
        tE.addComponent(new Bounds());
        tE.addComponent(new Rigidbody(TILES_SIZE));
        // tE.addComponent(new Physic2D());
        addEntity(tE);

        Entity floor = new Entity(
            "floor", 
            new Transform(new Vec2(4*TILES_SIZE,10*TILES_SIZE), new Vec2(2, TILES_SIZE)),
            0);
        floor.addComponent(new Bounds()); 
        addEntity(floor);
    
        renderer.submit(tE);
        renderer.submit(floor);
    }

    

    @Override
    public void update() {
        for(Entity entity : entities){
            entity.update();

        }
    }

    @Override
    public void draw(Graphics g) {
        renderer.render(g);
        
    }

    
}
