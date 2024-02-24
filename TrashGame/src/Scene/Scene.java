package Scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Renderer.Renderer;
import components.Component;
import entity.Entity;
import entity.EntityType;

public abstract class Scene{

	protected static Renderer renderer = new Renderer();
	private boolean isRunning = false;
	
	public abstract void init();

    public abstract void update();

	public abstract void draw(Graphics g);

	public abstract void onDestroy();

	public static Renderer getRenderer(){return renderer;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	


	

}
