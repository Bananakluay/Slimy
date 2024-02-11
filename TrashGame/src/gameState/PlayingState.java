package gameState;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import level.LevelManager;
import main.Game;
import UI.PauseOverlay;

public class PlayingState extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private double spaceChargeTime = 0.0;
	private boolean paused = false;
	private PauseOverlay PauseOverlay;
	private boolean isSpaceHeld = false;
	private boolean success = false;
	private boolean isOnCooldown = false;
	private double Base_jumpMultiplier = 0.62f;
	private double cooldownTime = 0.1;
	private final double MAX_CHARGE_TIME = 0.275; // Seconds
	private Timer chargeTimer;

	public PlayingState(Game game) {
		super(game);
		chargeTimer = new Timer();
		scene();
	}

	public boolean isSpaceHeld() {
		return isSpaceHeld;
	}

	public void scene() {
		levelManager = new LevelManager(game);
		player = new Player(300, 300, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		PauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
		} else {
			PauseOverlay.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);

		if (paused)
			PauseOverlay.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			PauseOverlay.mouseDragged(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				startCharging();
				break;
			case KeyEvent.VK_ESCAPE:
				GameStatesManager.state = GameStatesManager.MENU;
				break;
			case KeyEvent.VK_P:
				paused = !paused;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				if (spaceChargeTime < 0.1) { // Adjust threshold as needed
					applyJumpPower(0.1);
				} else {
					applyJumpPower(spaceChargeTime);
				}
				player.setJump(true); // Set jump state
				resetCharge();
				break;
		}

	}

	private void applyJumpPower(double chargeTime) {
		System.out.println(chargeTime);
		double jumpMultiplier = Math.max(Base_jumpMultiplier, Math.min(chargeTime / MAX_CHARGE_TIME, 1.0)); // Normalize
																											// and clamp
		System.out.println(jumpMultiplier);
		player.setMultiplier(jumpMultiplier);
	}

	private void startCharging() {
		System.out.println("Charging started: " + spaceChargeTime); // Debugging

		chargeTimer.cancel(); // Ensure any previous timer is stopped
		chargeTimer = new Timer(); // Create a new timer

		chargeTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				spaceChargeTime += 0.01;
				System.out.println("Charging: " + spaceChargeTime); // Debugging

				if (spaceChargeTime >= MAX_CHARGE_TIME) {
					this.cancel();
				}
			}
		}, 0, 10);
	}

	private void resetCharge() {
		System.out.println("Charge before reset: " + spaceChargeTime); // Debugging
		spaceChargeTime = 0.0;
		System.out.println("Charge after reset: " + spaceChargeTime); // Debugging
		chargeTimer.cancel();

		isOnCooldown = true;
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				isOnCooldown = false;
			}
		}, (long) (cooldownTime * 1000));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			PauseOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			PauseOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			PauseOverlay.mouseMoved(e);

	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

	public void unpauseGame() {
		paused = false;
	}

}
