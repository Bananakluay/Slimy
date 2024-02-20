package Prefabs;

import java.util.List;

import Behavior.Behavior;
import components.Detector;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;

public class Spike extends Entity implements Behavior{

    public Spike(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
    }

    public void init(){
     
        Detector detector = new Detector(
            transform.position.x, 
            transform.position.y, 
            transform.scale.x, 
            transform.scale.x, 
            List.of(EntityType.PLAYER), 
            this);
        
        addComponent(detector);
        
    }
    @Override
    public void activate(Entity entity) {
        System.out.println("here");
    }
    
}
