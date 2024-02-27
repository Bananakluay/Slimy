package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import components.Component;
import scene.LevelScene;

public class EntityManager {

    private List<Entity> entities;

    public EntityManager(){
        entities = new ArrayList<>();
    }

    public void updateEntities() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();
        }
    }
    public void ready(){
		for(Entity entity : entities){
			entity.ready();
		}
	}
    public void addEntity(Entity entity){
        entity.ready();
        entities.add(entity);
        LevelScene.getRenderer().submit(entity);

    }

    public void addAllEntities(List<Entity> entities){
        this.entities = entities;
    }
    


    public void removeEntity(Entity entity) {
        System.out.println("remove : " + entity.getName());
        entities.remove(entity);
        LevelScene.getRenderer().remove(entity,entity.getZindex()); 
    }


	public Entity getEntity(String name){
		for(Entity entity : this.entities){
			if(entity.getName().equals(name))
				return entity;
		}
		return null;
	}

    public Entity getEntity(int id){
		for(Entity entity : this.entities){
			if(entity.getId() == id)
				return entity;
		}
		return null;
	}

	public List<Entity> getEntitiesWithComponent(Class<? extends Component> componentClass){
		List<Entity> res = new ArrayList<>();
		for(Entity entity : entities)
			if(entity.hasComponent(componentClass))
				res.add(entity);
		return res;
	}

	public List<Entity> getAllEntities(){return entities;}


}
