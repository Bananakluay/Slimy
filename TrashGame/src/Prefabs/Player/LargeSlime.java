package Prefabs.Player;

import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

import static utils.Constants.Game.*;

import components.Bounds;
import components.Controller;
import components.Physic2D;
import components.Rigidbody;
public class LargeSlime extends Entity{

    private Controller controller;

    public LargeSlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(1.5f*TILES_SIZE,1.5f*TILES_SIZE)), zIndex);
        init();
    }

    private void init(){
        type = EntityType.PLAYER;
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(10f, 1f));
        addComponent(new Physic2D());

        controller = new Controller(true);
        addComponent(controller);
        

    }

    public Controller getController() {
        return controller;
    }

    
    
}
