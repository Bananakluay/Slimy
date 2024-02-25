package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interaction.Behavior;
import Scene.LevelScene;
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

        List<Entity> entitiesToRemove = new ArrayList<>();

        for(Entity entity : LevelScene.getEntityManager().getEntitiesWithComponent(Bounds.class)){

            if (entity.equals(this.entity) || !types.contains(entity.getType())) {
                continue;
            }

            Bounds bounds = entity.getComponent(Bounds.class);

            boolean intersected = bound.intersects(bounds.boundsX) || bound.intersects(bounds.boundsY);

            if (interaction.containsKey(entity.getId()) && !intersected) {
                interaction.remove(entity.getId());
                // System.out.println(entity.getName() + " out");
                behavior.activateOff(); 
            } else if (intersected) {
                if (!interaction.containsKey(entity.getId())) {
                    interaction.put(entity.getId(), entity);
                    // System.out.println(entity.getName() + " in");
                    behavior.activateOn(entity);
                }
            }

            if (LevelScene.getEntityManager().getEntity(entity.getId()) == null) {
                entitiesToRemove.add(entity);
            }  
        }

        for (Entity entityToRemove : entitiesToRemove) {
            interaction.remove(entityToRemove.getId());
        }

    }

    @Override
    public void activateOn(Entity entity){
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
