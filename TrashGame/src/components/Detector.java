package components;

import java.awt.geom.Rectangle2D;
import java.util.List;

import Behavior.Behavior;
import Scene.SceneManager;
import entity.Entity;
import entity.EntityType;

public class Detector extends Component implements Behavior{

    public Rectangle2D.Float area;
    public boolean detected = false;
    private Behavior behavior;
    private final List<EntityType> types;

    public Detector(float x, float y, float witdth, float height, List<EntityType> types, Behavior behavior){
        this.area = new Rectangle2D.Float(x,y,witdth,height);
        this.types = types;
        this.behavior = behavior;
    }

    @Override
    public void update() {
        for(Entity entity : SceneManager.getCurrentScene().getEntitiesWithComponent(Bounds.class)){
            if (entity.equals(this.entity) || !types.contains(entity.type)) {
                continue; 
            }

            Bounds bounds = entity.getComponent(Bounds.class);
            boolean intersectedX = area.intersects(bounds.boundsX);
            boolean intersectedY = area.intersects(bounds.boundsY);

            if (intersectedX || intersectedY) { // More efficient intersection check
                behavior.activate(entity);
                detected = true;
            } else {
                detected = false;
            }
         }
    }

    @Override
    public void activate(Entity entity){
        System.out.println(this.entity.getName() + " founds "+entity.getName());
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    
    

}
