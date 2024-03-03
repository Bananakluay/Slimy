package main;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import input.Mouse.MouseManager;
import level.LevelManager;
import prefabs.player.PlayerManager;
import scene.Scene;
import scene.SceneManager;
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

	private static int fadeAlpha = 0;

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

		// System.gc();
		// Runtime runtime = Runtime.getRuntime();
		// long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 *
		// 1024); // in MB
		// System.out.println("Used Memory: " + usedMemory + " MB");
		MI.update();
		KI.update();

		LevelManager.updateFadeIn();
		LevelManager.updateFadeOut();
		SceneManager.updateFadeIn();
		SceneManager.updateFadeOut();

		SceneManager.getCurrentScene().update();
	}

	public void render(Graphics g) {
		SceneManager.getCurrentScene().render(g);

		if (SceneManager.isFadingOut() || SceneManager.isFadingIn() || LevelManager.isFadingIn()
				|| LevelManager.isFadingOut()) {
			g.setColor(new Color(0, 0, 0, fadeAlpha));
			g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		}
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

	public static Scene getCurrentScene() {
		return SceneManager.getCurrentScene();
	}

	public static int getFadeAlpha() {
		return fadeAlpha;
	}

	public static void setFadeAlpha(int alpha) {
		fadeAlpha = alpha;
	}
}
