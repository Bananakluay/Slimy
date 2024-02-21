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
public class TinySlime extends Entity{

    Controller controller;  

    public TinySlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE,TILES_SIZE)), zIndex);
        init();
    }

    private void init(){
        type = EntityType.PLAYER;
        addComponent(new Bounds(null));
        addComponent(new Rigidbody(5f, 1.5f));
        addComponent(new Physic2D());
        
        controller = new Controller(false);
        addComponent(controller);


    }

    public Controller getController() {
        return controller;
    }
    
}
