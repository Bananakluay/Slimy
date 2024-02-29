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
        loadLevelButtons(currentPage);
    }

    @Override
    public void update() {
        levelSelectGuiLayer.update();
        // nextPrevGuiLayer.update();
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

        GuiButton prevButton = new GuiButton(
                "PrevButton",
                new Vec2(posX, posY),
                new Vec2(scaleX, scaleY),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PrevButton.png", 16, 16),
                () -> loadPrevLevels()
        );

        GuiButton nextButton = new GuiButton(
                "NextButton",
                new Vec2(posX + 16 * SCALE * 3, posY),
                new Vec2(scaleX, scaleY),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/NextButton.png", 16, 16),
                () -> loadNextLevels()
        );
        
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
                float posY = initPosY * (gridSpacing * (j + 1)) - ((16f * SCALE * 3) / 2) + j * gridSpacing / 2;
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

    private void loadPrevLevels() {
        currentPage--;
        levelSelectGuiLayer.clear();
        loadLevelButtons(currentPage);
    }

    private void loadNextLevels() {
        currentPage++;
        levelSelectGuiLayer.clear();
        loadLevelButtons(currentPage);
    }

    @Override
    public void gui(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        // Define the spacing between grid lines
        // You can adjust this value to change the spacing

        // Set the color for the grid lines
        g.setColor(Color.LIGHT_GRAY);

        // Draw vertical grid lines
        for (int x = 0; x < GAME_WIDTH; x += gridSpacing) {
            g.drawLine(x, 0, x, GAME_HEIGHT);
        }

        // Draw horizontal grid lines
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
