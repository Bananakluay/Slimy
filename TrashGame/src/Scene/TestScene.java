package Scene;

import static util.Constants.Game.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
import components.Sprite;
import components.SubSprite;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import physics.Prefabs.BoxWall;
import physics.Prefabs.TileBlock;
import util.Vec2;

public class TestScene extends Scene {


    public TestScene(){
        init();
        ready();
    }



    @Override
    public void init() {
        Entity player = new Entity(
            "player",
            new Transform(new Vec2(5*TILES_SIZE,TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE)),
            0);
        player.addComponent(new Bounds());
        player.addComponent(new Rigidbody(1f));
        player.addComponent(new Physic2D());
        player.addComponent(new Controller());

        addEntity(player);

        Entity floor = new Entity("floor", new Transform(new Vec2(5*TILES_SIZE,10*TILES_SIZE), new Vec2(TILES_SIZE*200, TILES_SIZE)), 0);
        floor.addComponent(new Bounds());
        addEntity(floor);

        renderer.submit(floor);
        renderer.submit(player);

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



    @Override
    public void onDestroy() {
        for (Entity entity : entities) {
            entity.onDestroy();
        }
    }

    
}
