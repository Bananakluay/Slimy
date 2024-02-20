package Scene;

import static util.Constants.Game.*;

import java.awt.Graphics;
import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
<<<<<<< HEAD
import components.Sprite;
import components.SubSprite;
import dataStructure.AssetPool;
=======
import components.SubSprite;
>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66
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
        Entity player = new Entity(
            "player",
            new Transform(new Vec2(5*TILES_SIZE,TILES_SIZE), new Vec2(TILES_SIZE, TILES_SIZE)),
            0);
        player.addComponent(new Bounds(null));
        player.addComponent(new Rigidbody(3f,1f));
        player.addComponent(new Physic2D());
        player.addComponent(new Controller());
        player.addComponent(new SubSprite("TrashGame/res/Wall.png", 8).getSprites(1));

        addEntity(player);

        Entity floor = new Entity("floor", new Transform(new Vec2(5*TILES_SIZE,10*TILES_SIZE), new Vec2(TILES_SIZE*200, TILES_SIZE)), 0);
        floor.addComponent(new Bounds(null));
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
