package Prefabs.Player;

import dataStructure.Transform;
import entity.Entity;
import utils.Vec2;

import static utils.Constants.Game.*;

import components.Bounds;
import components.Controller;
import components.Rigidbody;
public class BigSlime extends Entity{

    private Controller controller;

    public BigSlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(1.5f*TILES_SIZE,1.5f*TILES_SIZE)), zIndex);
        init();
    }

    private void init(){
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(10f, 1f));

        controller = new Controller(false);
        addComponent(controller);
        

    }

    public Controller getController() {
        return controller;
    }

    
    
}
