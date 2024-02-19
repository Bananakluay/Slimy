package Scene;

import static util.Constants.Game.*;

import java.awt.Graphics;

import Prefabs.BoxWall;
import Prefabs.TileBlock;
import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
import components.Sprite;
import components.Spritesheet;
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

        // Entity floor = new Entity(
        //     "floor",
        //     new Transform(new Vec2(TILES_SIZE*0,10*TILES_SIZE), new Vec2(TILES_SIZE*100, TILES_SIZE)),
        //     3);
        // floor.addComponent(new Bounds());
        // floor.addComponent(new Rigidbody(0));
        // addEntity(floor);
        // Spritesheet spritesheet = new Spritesheet("TrashGame/res/TileSet.png", 16, 16);
        // System.out.println(spritesheet.sprites.size());
        // TileBlock tileBlock = new TileBlock(10, 10, TILES_SIZE, TILES_SIZE, new Spritesheet("TrashGame/res/TileSet.png", 16, 16).sprites.get(0));
        // addEntity(tileBlock);
        // // renderer.submit(tileBlock);
       
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
