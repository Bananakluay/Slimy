package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interaction.Behavior;
import scene.LevelScene;
import entity.Entity;
import entity.EntityType;

public class Detector extends Component implements Behavior {

    public Rectangle2D.Float bound;
    public boolean activated = false;
    private boolean isOneShot = false;

    private final List<EntityType> types;
    public Map<Integer, Entity> interaction;
    private Behavior behavior;

    public Detector(float x, float y, float witdth, float height, List<EntityType> types, Behavior behavior,
            boolean isOneShot) {
        this.bound = new Rectangle2D.Float(x, y, witdth, height);
        this.types = types;
        this.behavior = behavior;
        this.isOneShot = isOneShot;
        interaction = new HashMap<Integer, Entity>();
    }

    @Override
    public void update() {

        List<Entity> entitiesToRemove = new ArrayList<>();

        for (Entity entity : LevelScene.getEntityManager().getEntitiesWithComponent(Bounds.class)) {

            if (entity.equals(this.entity) || !types.contains(entity.getType())) {
                continue;
            }

            Bounds bounds = entity.getComponent(Bounds.class);

            boolean intersected = bound.intersects(bounds.boundsX) || bound.intersects(bounds.boundsY);

            if (isOneShot && !activated) {
                oneShotMode(entity, intersected);
            } else if (!isOneShot) {
                continueMode(entity, intersected);
            }

            if (LevelScene.getEntityManager().getEntity(entity.getId()) == null) {
                entitiesToRemove.add(entity);
            }

            for (Map.Entry<Integer, Entity> e : interaction.entrySet()) {
                if (LevelScene.getEntityManager().getEntity(e.getKey()) == null) {
                    entitiesToRemove.add(e.getValue());
                }
            }

        }

        for (Entity entityToRemove : entitiesToRemove) {
            interaction.remove(entityToRemove.getId());
        }

    }

    private void oneShotMode(Entity entity, boolean intersected) {
        if (intersected) {
            activated = true;
            behavior.activateOneShot(entity);
        }
    }

    private void continueMode(Entity entity, boolean intersected) {
        if (interaction.containsKey(entity.getId()) && !intersected) {
            interaction.remove(entity.getId());
            if (interaction.isEmpty())
                behavior.activateOff();
            activated = false;
        } else if (intersected) {
            if (!interaction.containsKey(entity.getId())) {
                interaction.put(entity.getId(), entity);
                behavior.activateOn(entity);
                activated = true;
            }
        }

    }

    @Override
    public void activateOneShot(Entity entity) {
    }

    @Override
    public void activateOn(Entity entity) {
    }

    @Override
    public void activateOff() {
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void draw(Graphics g) {
        // Graphics2D g2d = (Graphics2D) g;
        // g2d.setColor(Color.red);
        // g2d.fill(bound);
    }

}
