package scene;

import java.awt.Graphics;

import renderer.Renderer;


public abstract class Scene{

	protected static Renderer renderer = new Renderer();

	public static boolean isRunning = true;
	
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

	


	

}
