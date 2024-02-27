package Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Renderer.Renderer;

import entity.Entity;


public abstract class Scene{

	protected static Renderer renderer = new Renderer();
	public boolean isRunning = true;
	
	public abstract void init();

    public abstract void update();

	public abstract void render(Graphics g);

	public  void gui(Graphics g){};
	
	public abstract void onDestroy();

	public static Renderer getRenderer(){return renderer;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	
	// protected List<Entity> entities = new ArrayList<>();
	// public void addEntity(Entity entity) {
    //     entities.add(entity);
    // }
	// public List<Entity> getEntities() {
    //     return entities;
    // }

	

}
