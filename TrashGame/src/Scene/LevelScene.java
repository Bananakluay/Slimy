package scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import components.Bounds;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityManager;
import entity.EntityType;
import gui.GuiButton;
import gui.GuiLayer;
import gui.GuiText;
import gui.ToggleGuiButton;
import level.LevelManager;
import main.Game;
import prefabs.trap.ArrowRush;
import prefabs.trap.ArrowTrap;
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
    private static BufferedImage pauseBackground;

    private static EntityManager entitiyManager;
    private static GuiLayer guiPlayingScene;
    private static GuiLayer guiPauseScene;

    public LevelScene() {
        LevelManager.get();

        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();
        guiPlayingScene = new GuiLayer();
        guiPauseScene = new GuiLayer();

        init();

    }

    public void init() {
        LevelManager.loadLevels();
        setup();
        test();
    }

    public void test() {
        Platform p1 = new Platform(TILES_SIZE * 12, TILES_SIZE * 8, 0);
        Platform p2 = new Platform(TILES_SIZE * 13, TILES_SIZE * 8, 1);
        Platform p3 = new Platform(TILES_SIZE * 14, TILES_SIZE * 8, 2);
        entitiyManager.addEntity(p1);
        entitiyManager.addEntity(p2);
        entitiyManager.addEntity(p3);

        ArrowTrap arrowTrap = new ArrowTrap("ArrowTrap", TILES_SIZE * 16, TILES_SIZE * 10, 2);
        entitiyManager.addEntity(arrowTrap);
    }

    public static void setup() {
        initGuiPlayingScene();
        initGuiPauseScene();
        entitiyManager.ready();
        isRunning = true;
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
            g.setColor(new Color(0, 0, 0, 128));
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            guiPauseScene.render(g);
            // g.setColor(Color.BLACK);
            // g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center
            // g.drawLine((GAME_WIDTH / 2) - 175, 0, (GAME_WIDTH / 2) - 175, GAME_HEIGHT);
            // // draw center
            // g.drawLine((GAME_WIDTH / 2) + 175, 0, (GAME_WIDTH / 2) + 175, GAME_HEIGHT);
            // // draw center

            drawPauseGui(g);
        }
    }

    public void drawPauseGui(Graphics g) {
        GuiText.drawString(
                g,
                "PAUSED",
                new Vec2(GAME_WIDTH / 2, (float) (GAME_HEIGHT / 2.2)),
                Color.GRAY,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", 32 * 10));
    }

    // Gui playing scene----------------------------------------------
    public static void initGuiPlayingScene() {
        GuiButton pauseButton = new GuiButton( /* gen option button */
                "PauseButton",
                new Vec2(GAME_WIDTH - (40f * SCALE), GAME_HEIGHT * 0.03f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PauseButton.png", 16, 16), // change png later
                () -> isRunning = false);

        guiPlayingScene.addGuiComponent(pauseButton);
    }

    // Gui pause scene----------------------------------------------
    public static void initGuiPauseScene() {
        ToggleGuiButton soundButton = new ToggleGuiButton(
                "SoundButton",
                new Vec2((float) (TILES_SIZE * 17.15), (float) (TILES_SIZE * 4.8)),
                new Vec2(16f * SCALE * 1.5f, 16f * SCALE * 1.5f),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16),
                () -> System.out.println("SoundButton"));

        ToggleGuiButton musicButton = new ToggleGuiButton(
                "musicButton",
                new Vec2((float) (TILES_SIZE * 13.45), (float) (TILES_SIZE * 4.8)),
                new Vec2(16f * SCALE * 1.5f, 16f * SCALE * 1.5f),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16),
                () -> System.out.println("musicButton"));

        GuiButton continueButton = new GuiButton( /* gen option button */
                "ContinueButton",
                new Vec2(GAME_WIDTH / 2 - (16f * SCALE), GAME_HEIGHT * 0.8f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ContinueButton.png", 16, 16), // change png
                () -> isRunning = true);

        GuiButton backButton = new GuiButton( /* gen option button */
                "backButton",
                new Vec2(GAME_WIDTH / 2 - (75f * SCALE), GAME_HEIGHT * 0.8f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToLSMButton.png", 16, 16), // change png
                () -> SceneManager.changeScene(Scenes.MENU_SCENE));

        GuiButton restartButton = new GuiButton( /* gen option button */
                "restartButton",
                new Vec2(GAME_WIDTH / 2 - (-42f * SCALE), GAME_HEIGHT * 0.8f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ResetButton.png", 16, 16), // change png
                () -> LevelManager.resetLevel());

        guiPauseScene.addGuiComponent(restartButton);
        guiPauseScene.addGuiComponent(musicButton);
        guiPauseScene.addGuiComponent(backButton);
        guiPauseScene.addGuiComponent(continueButton);
        guiPauseScene.addGuiComponent(soundButton);

    }

    public static void clear() {
        renderer.clear();
        entitiyManager.getAllEntities().clear();
        guiPlayingScene.clear();
        guiPauseScene.clear();
    }

    @Override
    public void onDestroy() {
        playerManager = null;
        entitiyManager.getAllEntities().clear();
        guiPlayingScene.clear();
        guiPauseScene.clear();
    }

    public static EntityManager getEntityManager() {
        return entitiyManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

}
