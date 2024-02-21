package Prefabs.Exits;

import static utils.Constants.Game.TILES_SIZE;

import java.util.List;

import Behavior.Behavior;
import components.Detector;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;

public class Door extends Entity implements Behavior{

    public Door(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
        init();
    }

    public void init(){
        Detector detector = new Detector(
            transform.position.x+TILES_SIZE, 
            transform.position.y, 
            transform.scale.x, 
            transform.scale.y, 
            List.of(EntityType.PLAYER), 
            this);
        addComponent(detector);
        
    }
    @Override
    public void activate(Entity entity) {
        //what to do
        System.out.println("This is door Exits");
    }
    
}
