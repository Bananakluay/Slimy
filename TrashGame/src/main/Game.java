package main;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import gameState.GameStatesManager;
import gameState.MenuState;
import gameState.PlayingState;

public class Game implements Runnable{

	@SuppressWarnings("unused")
	private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

	private PlayingState playing;
	private MenuState menu;

    public final static int TILES_DEFAULT_SIZE = 16;
    public final static float SCALE = 4f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game(){
		initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

	private void initClasses() {
		menu = new MenuState(this);
		playing = new PlayingState(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

    public void update(){
		switch (GameStatesManager.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		}
    }

    public void render(Graphics g){
		switch (GameStatesManager.state) {
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
				break;
			default:
				break;
		}
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
				System.out.println("FPS: " + frames + " | UPS: " + ticks);
				frames = 0;
				ticks = 0;

			}
		}

	}

	public void windowFocusLost() {
		if (GameStatesManager.state == GameStatesManager.PLAYING)
			playing.getPlayer().resetDirBooleans();
	}

	public MenuState getMenu() {
		return menu;
	}

	public PlayingState getPlaying() {
		return playing;
	}

	public KeyListener PlayingState() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'PlayingState'");
	}


}
