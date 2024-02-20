package Prefabs;

import static util.Constants.Game.TILES_SIZE;

import java.util.Arrays;
//import java.util.List;

import Behavior.Behavior;
import components.Detector;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import util.Vec2;

public class Button extends Entity implements Behavior{

    public Button(String name, float x, float y, int zIndex) {
        super(name, 
        new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE/2)), 
        zIndex);

        init();
    }

    public void init(){
        Detector detector = new Detector(
            transform.position.x, 
            transform.position.y + (TILES_SIZE/2), 
            transform.scale.x, 
            transform.scale.y, 
            Arrays.asList(EntityType.PLAYER),
            //List.of(EntityType.PLAYER), 
            this);

        addComponent(detector);
        
    }
    
    @Override
    public void activate(Entity entity) {
        System.out.println(entity.getName());
    }

    
}
