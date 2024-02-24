package Prefabs;

import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;

import components.Bounds;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;

public class Gate extends Entity{
    Button button;
    public Gate(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, 2*TILES_SIZE)), 1);
        init();
    }
    
    public void init(){
        type = EntityType.GATE;
        addComponent(new Bounds(Color.DARK_GRAY));
    }
    public void open(){
        this.getComponent(Bounds.class).setBound(
            this.getTransform().position.x, 
            this.getTransform().position.y,
            this.getTransform().scale.x, 
            this.getTransform().scale.y*0.3f);
    }
    public void close(){
        this.getComponent(Bounds.class).setBound(
            this.getTransform().position.x, 
            this.getTransform().position.y,
            this.getTransform().scale.x, 
            2*TILES_SIZE);
    }
    
}
