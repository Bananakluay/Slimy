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
            "player",
            new Transform(new Vec2(5*TILES_SIZE,TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE)),
            0);
        tE.addComponent(new Bounds());
        tE.addComponent(new Rigidbody(1f));
        tE.addComponent(new Physic2D());
        tE.addComponent(new Controller());
        addEntity(tE);

        Entity floor = new Entity(
            "floor",
            new Transform(new Vec2(TILES_SIZE*0,10*TILES_SIZE), new Vec2(TILES_SIZE*100, TILES_SIZE)),
            3);
        floor.addComponent(new Bounds());
        floor.addComponent(new Rigidbody(0));
        addEntity(floor);

        BoxWall boxWall = new BoxWall("Box",7,5,2f,2);
        Entity bw = boxWall.get();
        addEntity(bw);

        BoxWall boxWall3 = new BoxWall("Box",7,8,2f,2f);
        Entity bw3 = boxWall3.get();
        addEntity(bw3);

        BoxWall boxWall4 = new BoxWall("Box",7,5,1,1);
        Entity bw4 = boxWall4.get();
        addEntity(bw4);

        BoxWall boxWall1 = new BoxWall("Box2",19,5,1f,5);
        Entity bw1 = boxWall1.get();
        bw1.removeCompnent(Physic2D.class);
        addEntity(bw1);

        renderer.submit(floor);
        renderer.submit(bw);
        renderer.submit(bw1);
        renderer.submit(tE);
        renderer.submit(bw3);
        renderer.submit(bw4);
       
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
