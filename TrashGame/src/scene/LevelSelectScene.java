package scene;

import static utils.Constants.Game.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dataStructure.AssetPool;
import gui.GuiButton;
import gui.GuiLayer;
import main.Game;
import ui.SelectLevelButton;
import utils.Vec2;

public class LevelSelectScene extends Scene {

    private GuiLayer levelSelectGuiLayer;
    private GuiLayer nextPrevGuiLayer;
    private int currentPage = 0;
    private int maxPage = 1;
    private int gridSpacing = (int) (GAME_WIDTH / 12);
    private BufferedImage bg = AssetPool.getBufferedImage("TrashGame/res/assets/Background/background.png", 64, 36);

    public LevelSelectScene() {
        init();
    }

    private void init() {
        levelSelectGuiLayer = new GuiLayer();
        nextPrevGuiLayer = new GuiLayer();
        loadNextPrevButtons();
        loadLevelButtons(currentPage);
    }

    @Override
    public void update() {
        levelSelectGuiLayer.update();
        // System.out.println(GAME_WIDTH);
        // System.out.println(Game.MI.getMouseX());
        // System.out.println(Game.MI.getMouseY());
        if (this.nextPrevGuiLayer != null) {
            nextPrevGuiLayer.update();
        }

    }

    @Override
    public void render(Graphics g) {
        drawBackGround(g);
        gui(g);
    }

    private void loadNextPrevButtons() {
        float posX = (float) (GAME_WIDTH);
        float posY = GAME_HEIGHT - 16 * SCALE * 3;
        float scaleX = 16f * SCALE * 3;
        float scaleY = 16f * SCALE * 3;
        float half_scaleX = (float) (scaleX / 2 * 0.75);
        float half_scaleY = (float) (scaleY / 2 * 0.75);

        GuiButton NextButton = new GuiButton(
                "NextButton",
                new Vec2((float) (posX / 1.2 - half_scaleX), (posY / 0.95f) - half_scaleY),
                new Vec2((float) (scaleX * 0.75), (float) (scaleY * 0.75)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/NextButton.png", 16, 16),
                () -> loadNextLevels());

        GuiButton prevButton = new GuiButton(
                "PrevButton",
                new Vec2(posX / 6 - half_scaleX, (posY / 0.95f) - half_scaleY),
                new Vec2((float) (scaleX * 0.75), (float) (scaleY * 0.75)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToMenuButton.png", 16, 16),
                () -> loadPrevLevels());

        nextPrevGuiLayer.addGuiComponent(NextButton);
        nextPrevGuiLayer.addGuiComponent(prevButton);
    }

    private void loadPrevLevels() { // ก่อน
        if (currentPage > 0) {
            currentPage--;
            levelSelectGuiLayer.clear();
            loadLevelButtons(currentPage);
        } else {
            levelSelectGuiLayer.clear();
            SceneManager.changeScene(Scenes.MENU_SCENE);
        }
    }

    private void loadNextLevels() { // ต่อไป
        if (currentPage < maxPage) {
            currentPage++;
            levelSelectGuiLayer.clear();
            loadLevelButtons(currentPage);
        } else {
            levelSelectGuiLayer.clear();
            loadLevelButtons(currentPage);
        }
    }

    private void loadLevelButtons(int page) {
        int row = 2;
        int col = 5;
        float initPosX = gridSpacing * 2 - (16f * SCALE * 3) / 2;
        float initPosY = gridSpacing * 2 - (16f * SCALE * 3) / 2;

        float buttonSpacingX = gridSpacing * 2;
        float buttonSpacingY = gridSpacing * 2;

        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                int levelNumber = i + j * col + page * (row * col) + 1;
                System.out.println(levelNumber);

                float posX = initPosX + i * buttonSpacingX;
                float posY = initPosY + j * buttonSpacingY;

                float scaleX = 16f * SCALE * 3;
                float scaleY = 16f * SCALE * 3;

                SelectLevelButton selectLevelButton = new SelectLevelButton(
                        "SelectLevelButton",
                        new Vec2(posX, posY),
                        new Vec2(scaleX, scaleY),
                        levelNumber);
                levelSelectGuiLayer.addGuiComponent(selectLevelButton);
            }
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

    @Override
    public void gui(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        // grid
        g.setColor(Color.LIGHT_GRAY);
        int numLines = 12;
        int spacing = (int) (GAME_WIDTH / (numLines));
        for (int i = 0; i < numLines; i++) {
            int x = i * spacing;
            g.drawLine(x, 0, x, GAME_HEIGHT);
        }
        for (int y = 0; y <= GAME_HEIGHT; y += gridSpacing) {
            g.drawLine(0, y, GAME_WIDTH, y);
        }

        if (levelSelectGuiLayer != null)
            levelSelectGuiLayer.render(g);
        if (nextPrevGuiLayer != null)
            nextPrevGuiLayer.render(g);
    }

    @Override
    public void onDestroy() {
        levelSelectGuiLayer = null;
        nextPrevGuiLayer = null;
    }

}
