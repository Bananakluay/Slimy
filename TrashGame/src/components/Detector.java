package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interaction.Behavior;
import Scene.SceneManager;
import entity.Entity;
import entity.EntityType;

public class Detector extends Component implements Behavior{

    public Rectangle2D.Float bound;
    public boolean detected = false;

    private final List<EntityType> types;
    public  Map<Integer, Entity> interaction;
    private Behavior behavior;
    

    public Detector(float x, float y, float witdth, float height, List<EntityType> types, Behavior behavior){
        this.bound = new Rectangle2D.Float(x,y,witdth,height);
        this.types = types;
        this.behavior = behavior;
        interaction = new HashMap();
    }

    @Override
    public void update() {
        for(Entity entity : SceneManager.getCurrentScene().getEntitiesWithComponent(Bounds.class)){

            if (entity.equals(this.entity) || !types.contains(entity.getType())) {
                continue; 
            }

            Bounds bounds = entity.getComponent(Bounds.class);
            
            boolean intersectedX = bound.intersects(bounds.boundsX);
            boolean intersectedY = bound.intersects(bounds.boundsY);

            if (intersectedX || intersectedY) {
                if(!interaction.containsKey(entity.getId())){
                    interaction.put(entity.getId(), entity);
                }

            } 
            else {
                if(interaction.containsKey(entity.getId())){
                    interaction.remove(entity.getId());
                }
            }

            if(!interaction.isEmpty()){
                behavior.activateOn(entity);
            }
            else{
                behavior.activateOff();
            }
        }
    }

    @Override
    public void activateOn(Entity entity){
        System.out.println(this.entity.getName() + " founds "+entity.getName());
    }
    @Override
    public void activateOff(){
        System.out.println("activeOff");
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setColor(Color.red);
        // g2d.fill(bound);
    }
    
    

}
