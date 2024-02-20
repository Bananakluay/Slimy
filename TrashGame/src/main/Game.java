package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Manager.SceneManager;
import Scene.Scene;
import Scene.TestScene;
import input.KeyInputs;
import input.MouseInputs;
import util.*;
@SuppressWarnings("unused")
public class Game implements Runnable{

	private static Game game = null;

	private Window gameWindow;
    private GamePanel gamePanel;
	private SceneManager sceneManager;

	public static MouseInputs MI;
	public static KeyInputs KI;
	
    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
 
    private Game(){
		//setting scene
		sceneManager = SceneManager.get();
		//setting window
        gamePanel = GamePanel.get(this);
        gameWindow = Window.get(gamePanel);
		
		KI = new KeyInputs();
		MI = new MouseInputs();
		
		gamePanel.addKeyListener(KI);
        gamePanel.addMouseListener(MI);
        gamePanel.requestFocus();
		

        startGameLoop();
    }

	public static Game get(){
		if(game == null)
			return new Game();
		return game;
	}

    public void update(){
		SceneManager.getCurrentScene().update();
    }

    public void render(Graphics g){
		SceneManager.getCurrentScene().draw(g);
    }
    
    private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int ticks = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				// System.out.println("FPS: " + frames + " | UPS: " + ticks);
				frames = 0;
				ticks = 0;

			}
		}

	}

	public Scene getCurrentScene(){
		return sceneManager.getCurrentScene();
	}
}
