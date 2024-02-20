package Prefabs;

import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;

public class MoviangBox extends Entity{

    public MoviangBox(String name, Transform transform, int zIndex) {
        super(name, transform, zIndex);
        this.addComponent(new Rigidbody(10, 20));
    }
    
}
