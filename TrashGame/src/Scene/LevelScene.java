package scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dataStructure.AssetPool;
import entity.Entity;
import entity.EntityManager;
import gui.GuiButton;
import gui.GuiLayer;
import gui.ToggleGuiButton;
import level.LevelManager;
import main.Game;
import prefabs.Objects.Box;
import prefabs.player.PlayerManager;
import prefabs.trap.BombButton;
import prefabs.trap.Spike;
import prefabs.Objects.Button;
import prefabs.Objects.Gate;
import utils.Vec2;

public class LevelScene extends Scene {

    private static PlayerManager playerManager;

    private static EntityManager entitiyManager;
    private GuiLayer guiPlayingScene;
    private GuiLayer guiPauseScene;

    public LevelScene() {
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();

        guiPlayingScene = new GuiLayer();
        guiPauseScene = new GuiLayer();

        init();
        initGuiPlayingScene();
        initGuiPauseScene();
    }

    @Override
    public void init() {
        // Entity--------------------------------
        loadLevels();
        Button b = new Button("Button", TILES_SIZE * 11, TILES_SIZE * 12);
        entitiyManager.addEntity(b);
        Button b2 = new Button("Button", TILES_SIZE * 14, TILES_SIZE * 12);
        entitiyManager.addEntity(b2);

        Gate g = new Gate("Gate", TILES_SIZE * 16, TILES_SIZE * 11);
        entitiyManager.addEntity(g);
        g.addListener(b);
        g.addListener(b2);

        Box box = new Box("box", TILES_SIZE * 4, TILES_SIZE * 2, TILES_SIZE, TILES_SIZE, null, 3f * SCALE,
                0.67f * SCALE);
        entitiyManager.addEntity(box);

        BombButton fb = new BombButton(TILES_SIZE * 8, TILES_SIZE * 12);
        entitiyManager.addEntity(fb);

        Spike sp = new Spike("spike", TILES_SIZE * 21, TILES_SIZE * 12);
        entitiyManager.addEntity(sp);
        entitiyManager.ready();

    }

    // Gui playing scene----------------------------------------------
    public void initGuiPlayingScene() {

        GuiButton pauseButton = new GuiButton( /* gen option button */
                "PauseButton",
                new Vec2(GAME_WIDTH - (40f * SCALE), GAME_HEIGHT * 0.03f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PauseButton.png", 16, 16), // change png later
                () -> isRunning = false);

        guiPlayingScene.addGuiComponent(pauseButton);
    }

    // Gui pause scene----------------------------------------------
    public void initGuiPauseScene() {
        ToggleGuiButton soundButton = new ToggleGuiButton(
                "SoundButton",
                new Vec2(TILES_SIZE * 10, TILES_SIZE * 3),
                new Vec2(16f * SCALE * 1.5f, 16f * SCALE * 1.5f),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16),
                () -> System.out.println("SoundButton"));

        GuiButton continueButton = new GuiButton( /* gen option button */
                "ContinueButton",
                new Vec2(GAME_WIDTH - (40f * SCALE), GAME_HEIGHT * 0.03f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ContinueButton.png", 16, 16), // change png
                                                                                                      // later
                () -> isRunning = true);

        GuiButton backButton = new GuiButton( /* gen option button */
                "backButton",
                new Vec2(GAME_WIDTH - (50f * SCALE), GAME_HEIGHT * 0.84f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToLSMButton.png", 16, 16), // change png
                                                                                                       // later
                () -> SceneManager.changeScene(Scenes.MENU_SCENE));

        guiPauseScene.addGuiComponent(backButton);
        guiPauseScene.addGuiComponent(continueButton);
        guiPauseScene.addGuiComponent(soundButton);

    }

    @Override
    public void update() {
        // pause and player
        if (Game.KI.onPress(KeyEvent.VK_ESCAPE)) {
            if (isRunning == true) {
                isRunning = false;
            } else {
                isRunning = true;
            }
        }

        if (isRunning) {
            playerManager.update();
            entitiyManager.updateEntities();
            guiPlayingScene.update();
        } else {
            guiPauseScene.update();
        }

    }

    @Override
    public void render(Graphics g) {
        if (isRunning) {
            renderer.render(g);
            guiPlayingScene.render(g);
        } else {
            renderer.render(g);
            guiPauseScene.render(g);
        }
    }

    private void loadLevels() {
        LevelManager.get();
    }

    @Override
    public synchronized void onDestroy() {
        playerManager = null;
        entitiyManager.getAllEntities().clear();
        guiPlayingScene.clear();
        guiPauseScene.clear();
    }

    public static void deleteCurrentLevel() {
        for (Entity entity : entitiyManager.getAllEntities()) {
            entity.onDestroy();
        }
        playerManager = null;
    }

    public static void createNextLevel() {
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();
        renderer.clear();
    }

    public static EntityManager getEntityManager() {
        return entitiyManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

}
