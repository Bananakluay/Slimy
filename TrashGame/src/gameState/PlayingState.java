package gameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
<<<<<<< Updated upstream
import simplePhysic.Area;
=======
import level.LevelManager;
import main.Game;
>>>>>>> Stashed changes

public class PlayingState extends State implements Statemethods{

    private Player player;
    private LevelManager levelManager;

    public PlayingState(Game game) {
		super(game);
		scene();
	}


<<<<<<< Updated upstream
    private Player player;

    public PlayingState(){
        scene();
    }
    public void scene(){
        player = new Player(new Area(50, 50, 50, 50));
=======
    public void scene(){
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
>>>>>>> Stashed changes
    }

    @Override
    public void update() {
<<<<<<< Updated upstream
=======
        levelManager.update();
>>>>>>> Stashed changes
        player.update();
    }

    @Override
    public void draw(Graphics g) {
<<<<<<< Updated upstream
        player.draw(g);
=======
        levelManager.draw(g);
        player.render(g);

>>>>>>> Stashed changes
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
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
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                GameStatesManager.state = GameStatesManager.MENU;
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
                player.setJump(false);
                break;
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}

    public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}
 
    
}
