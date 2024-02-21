package Scene;

import static utils.Constants.Game.*;

import java.awt.Graphics;
import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
import components.SubSprite;
import dataStructure.Transform;
import entity.Entity;
import utils.Vec2;

public class TestScene extends Scene {


    public TestScene(){
        init();
        ready();
    }



    @Override
    public void init() {
        

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
