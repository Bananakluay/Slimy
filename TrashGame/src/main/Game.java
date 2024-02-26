package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Prefabs.Player.PlayerManager;
import Scene.Scene;
import Scene.SceneManager;
import input.Mouse.MouseManager;
import input.Keyboard.KeyManager;
import utils.*;

@SuppressWarnings("unused")
public class Game implements Runnable {

	private static Game game = null;

	private Window gameWindow;
	private GamePanel gamePanel;
	private SceneManager sceneManager;

	public static MouseManager MI;
	public static KeyManager KI;
	private Thread gameThread;

	private final int FPS_SET = 120;
	private final int UPS_SET = 200;

	private Game() {
		// setting scene
		sceneManager = SceneManager.get();
		// setting window
		gamePanel = GamePanel.get(this);
		gameWindow = Window.get(gamePanel);

		MI = new MouseManager();
		KI = new KeyManager();
		gamePanel.addKeyListener(KI);
		gamePanel.addMouseListener(MI);
		gamePanel.addMouseMotionListener(MI);
		gamePanel.requestFocus();

		startGameLoop();
	}

	public static Game get() {
		if (game == null)
			return new Game();
		return game;
	}

	public void update() {
		// Runtime runtime = Runtime.getRuntime();
		// long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 *
		// 1024); // in MB
		// System.out.println("Used Memory: " + usedMemory + " MB");
		
		MI.update();
		KI.update();
		SceneManager.getCurrentScene().update();
	}

	public void render(Graphics g) {
		SceneManager.getCurrentScene().render(g);
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

	public Scene getCurrentScene() {
		return sceneManager.getCurrentScene();
	}
}
