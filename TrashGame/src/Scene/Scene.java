package Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Renderer.Renderer;
import components.Component;
import entity.Entity;
import entity.EntityType;

public abstract class Scene{

	protected List<Entity> entities = new ArrayList<>();
	public Renderer renderer = new Renderer();
	private boolean isRunning = false;
	
	public abstract void init();

    public abstract void update();

	public abstract void draw(Graphics g);

	public void ready(){
		for(Entity entity : entities){
			entity.ready();
		}
		isRunning = true;
	}

	public abstract void onDestroy();

	public void addEntity(Entity entity){
		if(!isRunning){
			entities.add(entity);
		}
		else{
			entities.add(entity);
			entity.ready();
		}
	}


	public Entity getEntity(String name){
		for(Entity entity : this.entities){
			if(entity.getName().equals(name))
				return entity;
		}
		return null;
	}

	public void addAllEntities(List<Entity> entities){
		this.entities = entities;
	}

	public void removeEntity(String name, EntityType type) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			System.out.println(name);
			if (entity.getName().equals(name) && entity.getType() == type) {
				entities.remove(i);
				System.out.println("remove : "+ entity.getName());
				break; 
			}
		}
	}

	public List<Entity> getEntitiesWithComponent(Class<? extends Component> componentClass){
		List<Entity> res = new ArrayList<>();
		for(Entity entity : entities)
			if(entity.hasComponent(componentClass))
				res.add(entity);
		return res;
	}

	protected List<Entity> getAllEntities(){return entities;}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	


	

}
