package Scene;


import java.awt.Graphics;

import entity.Entity;

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
