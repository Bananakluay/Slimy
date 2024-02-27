package entity;

import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<Entity> iterator = entities.iterator();

        for (Entity entity : entitiesToAdd) {
            entities.add(entity);
            LevelScene.getRenderer().submit(entity);
        }
        entitiesToAdd.clear();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();
        }

        for (Entity entity : entitiesToRemove) {
            entities.remove(entity);
            LevelScene.getRenderer().remove(entity, entity.getZindex());
        }
        entitiesToRemove.clear();
    }

    public void ready() {
        for (Entity entity : entities) {
            entity.ready();
        }
    }

    public void addEntity(Entity entity) {
        entity.ready();
        entities.add(entity);
        LevelScene.getRenderer().submit(entity);

    }

    public void addAllEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        LevelScene.getRenderer().remove(entity, entity.getZindex());
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
