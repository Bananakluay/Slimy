package entity;

import java.util.ArrayList;
import java.util.List;

import components.Component;
import scene.LevelScene;

public class EntityManager {

    private List<Entity> entities;
    List<Entity> entitiesToRemove;
    List<Entity> entitiesToAdd;

    public EntityManager() {
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
    }

    public void updateEntities() {
        // Process entities to add
        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear(); // Clear after processing

        // Process entities to remove
        List<Entity> toRemove = new ArrayList<>(entitiesToRemove); // Create a copy
        for (Entity entity : toRemove) {
            entities.remove(entity);
            LevelScene.getRenderer().remove(entity, entity.getZindex());
        }
        entitiesToRemove.clear(); // Clear after processing

        // Update remaining entities
        List<Entity> toUpdate = new ArrayList<>(entities); // Create a copy
        for (Entity entity : toUpdate) {
            entity.update();
        }
        // System.out.println("Entities: " + entities.size());
    }

    public void ready() {
        for (Entity entity : entitiesToAdd) {
            entity.ready();
        }
    }

    public void addEntity(Entity entity) {
        if (LevelScene.isRunning) {
            entity.ready();
        }
        entitiesToAdd.add(entity);
        LevelScene.getRenderer().submit(entity);

    }

    public void removeEntity(Entity entity) {
        entitiesToRemove.add(entity);
        LevelScene.getRenderer().remove(entity, entity.getZindex());
    }

    public void addAllEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public Entity getEntity(String name) {
        for (Entity entity : this.entities) {
            if (entity.getName().equals(name))
                return entity;
        }
        return null;
    }

    public Entity getEntity(int id) {
        for (Entity entity : this.entities) {
            if (entity.getId() == id)
                return entity;
        }
        return null;
    }

    public List<Entity> getEntitiesWithComponent(Class<? extends Component> componentClass) {
        List<Entity> res = new ArrayList<>();
        for (Entity entity : entities)
            if (entity.hasComponent(componentClass))
                res.add(entity);
        return res;
    }

    public void clear() {
        entities.clear();
    }

    public List<Entity> getAllEntities() {
        return entities;
    }

}
