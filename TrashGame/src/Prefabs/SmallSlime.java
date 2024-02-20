package Prefabs;

import dataStructure.Transform;
import entity.Entity;
import util.Vec2;
import static util.Constants.Game.*;

import components.Bounds;
import components.Controller;
import components.Rigidbody;
public class SmallSlime extends Entity{

    Controller controller;

    public SmallSlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE,TILES_SIZE)), zIndex);
        init();
    }

    private void init(){
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(10f, 1f));

        controller = new Controller(true);
        addComponent(controller);


    }

    public Controller getController() {
        return controller;
    }
    
}
