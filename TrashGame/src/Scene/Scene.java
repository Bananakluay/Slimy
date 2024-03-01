package Scene;

import java.awt.Graphics;

import Renderer.Renderer;


public abstract class Scene{

	protected static Renderer renderer = new Renderer();

	public static boolean isRunning = false;
	
    public abstract void update();

	public abstract void render(Graphics g);

	public  void gui(Graphics g){};
	
	public abstract void onDestroy();

	public static Renderer getRenderer(){return renderer;
	}


	


	

}
