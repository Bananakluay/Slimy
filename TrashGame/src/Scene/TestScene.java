package Scene;

import static util.Constants.Game.*;

import java.awt.Graphics;

import Prefabs.BoxWall;
import components.Bounds;
import components.Controller;
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
            new Transform(new Vec2(5*TILES_SIZE,TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE)),
            0);
        tE.addComponent(new Bounds());
        tE.addComponent(new Rigidbody(-2));
        tE.addComponent(new Physic2D());
        tE.addComponent(new Controller());
        addEntity(tE);

        Entity floor = new Entity(
            "Test",
            new Transform(new Vec2(TILES_SIZE*0,10*TILES_SIZE), new Vec2(TILES_SIZE*100, TILES_SIZE)),
            0);
        floor.addComponent(new Bounds());
        floor.addComponent(new Rigidbody(-2));
        addEntity(floor);

        BoxWall boxWall = new BoxWall(7,0,1,1);
        Entity bw = boxWall.get();
        addEntity(bw);

        renderer.submit(floor);
        renderer.submit(bw);
        renderer.submit(tE);
       
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
