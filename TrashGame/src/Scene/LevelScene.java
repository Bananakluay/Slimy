package scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

    BufferedImage bg = AssetPool.getBufferedImage("TrashGame/res/assets/Background/background.png", 64, 36);

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
    }

    public static void test() {

        ArrowTrap arrowTrap = new ArrowTrap("ArrowTrap", TILES_SIZE * 16, TILES_SIZE * 10);
        entitiyManager.addEntity(arrowTrap);

        Box box = new Box("Box", TILES_SIZE * 15, TILES_SIZE * 9);
        entitiyManager.addEntity(box);
    }

    public static void setup() {
        test();
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
            drawBackGround(g);
            renderer.render(g);
            guiPlayingScene.render(g);
        } else {
            drawBackGround(g);
            renderer.render(g);
            g.setColor(new Color(0, 0, 0, 128));
            g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            guiPauseScene.render(g);

            g.setColor(Color.BLACK);
            g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center
            g.drawLine((GAME_WIDTH / 2) - 175, 0, (GAME_WIDTH / 2) - 175, GAME_HEIGHT);
            // draw center
            g.drawLine((GAME_WIDTH / 2) + 175, 0, (GAME_WIDTH / 2) + 175, GAME_HEIGHT);
            // draw center

            drawPauseGui(g);
        }
    }

    public void drawBackGround(Graphics g) {
        g.drawImage(
                bg,
                0,
                0,
                (int) (256 * 2 * SCALE),
                (int) (144 * 2 * SCALE),
                null);
    }

    public void drawPauseGui(Graphics g) {
        GuiText.drawString(
                g,
                "PAUSED",
                new Vec2((GAME_WIDTH / 2) + 10, (float) (GAME_HEIGHT / 2.2)),
                Color.GRAY,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", 32 * 10));
    }

    // Gui playing scene----------------------------------------------
    public static void initGuiPlayingScene() {
        GuiButton pauseButton = new GuiButton( /* gen option button */
                "PauseButton",
                new Vec2((GAME_WIDTH - (38f * SCALE)), GAME_HEIGHT * 0.03f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PauseButton.png", 16, 16), // change png later
                () -> isRunning = false);

        GuiButton resetButton = new GuiButton( /* gen option button */
                "resetButton",
                new Vec2((float) (GAME_WIDTH - (38f * SCALE) * 13.25), GAME_HEIGHT * 0.03f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ResetButton.png", 16, 16), // change png later
                () -> LevelManager.resetLevel());

        guiPlayingScene.addGuiComponent(pauseButton);
        guiPlayingScene.addGuiComponent(resetButton);
    }

    // Gui pause scene----------------------------------------------
    public static void initGuiPauseScene() {
        float ScaleX = 16f * SCALE * 1.5f;
        float ScaleY = 16f * SCALE * 1.5f;

        ToggleGuiButton soundButton = new ToggleGuiButton(
                "SoundButton",
                new Vec2((float) (TILES_SIZE * 16.75), (float) (TILES_SIZE * 3.25)),
                new Vec2((float) (ScaleX * 1.5), (float) (ScaleY * 1.5)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16),
                () -> System.out.println("SoundButton"));

        ToggleGuiButton musicButton = new ToggleGuiButton(
                "musicButton",
                new Vec2((float) (TILES_SIZE * 13.1), (float) (TILES_SIZE * 3.25)),
                new Vec2((float) (ScaleX * 1.5), (float) (ScaleY * 1.5)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/MusicButton.png", 16, 16),
                () -> System.out.println("musicButton"));

        GuiButton continueButton = new GuiButton( /* gen option button */
                "ContinueButton",
                new Vec2(GAME_WIDTH / 2 - (16f * SCALE) - 5, (float) (TILES_SIZE * 11.45)),
                new Vec2((float) (ScaleX * 1.5), (float) (ScaleY * 1.5)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BetterContinueButton.png", 16, 16), // change
                                                                                                            // png
                () -> isRunning = true);

        GuiButton HomeButton = new GuiButton( /* gen option button */
                "HomeButton",
                new Vec2(GAME_WIDTH / 2 - (75f * SCALE) - 5, (float) (TILES_SIZE * 11.45)),
                new Vec2((float) (ScaleX * 1.5), (float) (ScaleY * 1.5)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/HomeButton.png", 16, 16), // change png
                () -> SceneManager.changeScene(Scenes.MENU_SCENE));

        GuiButton restartButton = new GuiButton( /* gen option button */
                "restartButton",
                new Vec2(GAME_WIDTH / 2 - (-42f * SCALE) - 5, (float) (TILES_SIZE * 11.45)),
                new Vec2((float) (ScaleX * 1.5), (float) (ScaleY * 1.5)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ReplayButton.png", 16, 16), // change png
                () -> LevelManager.resetLevel());

        guiPauseScene.addGuiComponent(restartButton);
        guiPauseScene.addGuiComponent(musicButton);
        guiPauseScene.addGuiComponent(HomeButton);
        guiPauseScene.addGuiComponent(continueButton);
        guiPauseScene.addGuiComponent(soundButton);

    }

    public static void clear() {
        renderer.clear();
        entitiyManager.getAllEntities().clear();
        guiPlayingScene.clear();
        guiPauseScene.clear();
        playerManager.clear();
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
