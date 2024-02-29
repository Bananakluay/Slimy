package scene;

import static utils.Constants.Game.*;

import java.awt.Color;
import java.awt.Graphics;

import dataStructure.AssetPool;
import gui.GuiButton;
import gui.GuiLayer;
import ui.SelectLevelButton;
import utils.Vec2;

public class LevelSelectScene extends Scene {

    private GuiLayer levelSelectGuiLayer;
    private GuiLayer nextPrevGuiLayer;

    private int currentPage = 0;
    private int maxPage = 2;
    int gridSpacing = 128;

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
        if (this.nextPrevGuiLayer != null) {
            nextPrevGuiLayer.update();
        }

    }

    @Override
    public void render(Graphics g) {
        gui(g);
    }

    private void loadNextPrevButtons() {
        float posX = GAME_WIDTH / 2 - 16 * SCALE * 3;
        float posY = GAME_HEIGHT - 16 * SCALE * 3;
        float scaleX = 16f * SCALE * 3;
        float scaleY = 16f * SCALE * 3;
        float half_scaleX = (float) (scaleX / 2 * 0.75);
        float half_scaleY = (float) (scaleY / 2 * 0.75);

        GuiButton prevButton = new GuiButton(
                "PrevButton",
                new Vec2(256 - half_scaleX, (posY / 0.95f) - half_scaleY),
                new Vec2((float) (scaleX * 0.75), (float) (scaleY * 0.75)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToMenuButton.png", 16, 16),
                () -> loadPrevLevels());

        GuiButton NextButton = new GuiButton(
                "NextButton",
                new Vec2(1280 - half_scaleX, (posY / 0.95f) - half_scaleY),
                new Vec2((float) (scaleX * 0.75), (float) (scaleY * 0.75)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/NextButton.png", 16, 16),
                () -> loadNextLevels());

        GuiButton BackButton = new GuiButton(
                "BackButton",
                new Vec2(1280 - half_scaleX, (posY / 0.95f) - half_scaleY),
                new Vec2((float) (scaleX * 0.75), (float) (scaleY * 0.75)),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToLSMButton.png", 16, 16),
                () -> SceneManager.changeScene(Scenes.MENU_SCENE));

        nextPrevGuiLayer.addGuiComponent(prevButton);
        nextPrevGuiLayer.addGuiComponent(NextButton);
    }

    private void loadPrevLevels() { // ก่อน
        // if (currentPage > 0 ) {
        // currentPage--;
        // levelSelectGuiLayer.clear();
        // loadLevelButtons(currentPage);
        // }
        // if (currentPage == 0 ) {
        // nextPrevGuiLayer.removeGuiComponent(prevButton);
        // nextPrevGuiLayer.addGuiComponent(BackButton);
        // }
        // else {
        // levelSelectGuiLayer.clear();
        // loadLevelButtons(currentPage);
        // }
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
        float initPosX = 1;
        float initPosY = 2;
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                int levelNumber = i + j * col + page * (row * col) + 1;
                System.out.println(levelNumber);
                float posX = initPosX * (gridSpacing * (i + 2)) - ((16f * SCALE * 3) / 2) + i * gridSpacing;
                float posY = initPosY * (gridSpacing * (j + 1)) - ((16f * SCALE * 3) / 2) + j * gridSpacing / 9;
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

    @Override
    public void gui(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        g.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x < GAME_WIDTH; x += gridSpacing) {
            g.drawLine(x, 0, x, GAME_HEIGHT);
        }
        for (int y = 0; y < GAME_HEIGHT; y += gridSpacing) {
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
