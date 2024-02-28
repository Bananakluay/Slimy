package scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import components.Bounds;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityManager;
import entity.EntityType;
import gui.GuiButton;
import gui.GuiLayer;
import gui.ToggleGuiButton;
import level.LevelManager;
import main.Game;
import prefabs.objects.Box;
import prefabs.objects.Button;
import prefabs.objects.Gate;
import prefabs.objects.Platform;
import prefabs.player.PlayerManager;
import prefabs.trap.BombButton;
import prefabs.trap.Spike;
import utils.Vec2;

@SuppressWarnings("unused")
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
        isRunning = true;
        // Entity--------------------------------
        LevelManager.loadLevels();

        // Platform p = new Platform(TILES_SIZE * 10, TILES_SIZE * 10);
        // entitiyManager.addEntity(p);
        // Platform p2 = new Platform(TILES_SIZE * 11, TILES_SIZE * 10);
        // entitiyManager.addEntity(p2);

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
                () -> isRunning = true);

        GuiButton backButton = new GuiButton( /* gen option button */
                "backButton",
                new Vec2(GAME_WIDTH - (50f * SCALE), GAME_HEIGHT * 0.84f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToLSMButton.png", 16, 16), // change png
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

    @Override
    public void onDestroy() {
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
        renderer.clear();
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();
    }

    public static EntityManager getEntityManager() {
        return entitiyManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

}
