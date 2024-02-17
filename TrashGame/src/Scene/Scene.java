package Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import components.Component;
import entity.Entity;

public abstract class Scene{

	protected List<Entity> entities = new ArrayList<>();
	protected Renderer renderer = new Renderer();
	private boolean isRunning = false;
	
	public abstract void init();

    public abstract void update();

	public abstract void draw(Graphics g);

	public void ready(){
		for(Entity entity : entities){
			entity.init();
		}
		isRunning = true;
	}

	protected void addEntity(Entity entity){
		if(!isRunning){
			entities.add(entity);
		}
		else{
			entities.add(entity);
			entity.init();
		}
	}

	protected Entity getEntity(String name){
		for(Entity entity : entities){
			if(entity.getName().equals(name))
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

	protected List<Entity> getAllEntities(){return entities;}



	

}
