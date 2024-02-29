package scene;

import static utils.Constants.Game.*;

import java.awt.Color;
import java.awt.Graphics;

import dataStructure.AssetPool;
import gui.GuiButton;
import gui.GuiLayer;
import main.Game;
import utils.Vec2;

public class LevelSelectScene extends Scene {
    
    private GuiLayer guiLayer;

    public LevelSelectScene() {
        init();
    }

    private void init() {
        guiLayer = new GuiLayer(); // layer

        GuiButton playButton = new GuiButton( /* gen paly button */
                "PlayButton",
                new Vec2(GAME_WIDTH / 2 - (33f * SCALE), GAME_HEIGHT * 0.84f), // position
                new Vec2(33f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PlayButton.png", 33, 16),
                () -> SceneManager.changeScene(Scenes.LEVEL_SCENE));

        GuiButton First_B = new GuiButton( /* gen paly button */
                "FirstButton",
                new Vec2(256 - ((16f * SCALE * 3) / 2), 256 - ((16f * SCALE * 3) / 2)), // position((16f * SCALE * 3) / 2)
                new Vec2(16f * SCALE * 3, 16f * SCALE * 3),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ContinueButton.png", 16, 16),
                () -> SceneManager.changeScene(Scenes.LEVEL_SCENE));

        GuiButton Second_B = new GuiButton( /* gen paly button */
                "SecondButton",
                new Vec2(512 - ((16f * SCALE * 3) / 2), 256 - ((16f * SCALE * 3) / 2)), // position((16f * SCALE * 3) / 2)
                new Vec2(16f * SCALE * 3, 16f * SCALE * 3),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/ContinueButton.png", 16, 16),
                () -> SceneManager.changeScene(Scenes.LEVEL_SCENE));

        guiLayer.addGuiComponent(playButton); // add to guilayer
        guiLayer.addGuiComponent(First_B);
        guiLayer.addGuiComponent(Second_B);
    }

    @Override
    public void update() {
        System.out.println(Game.MI.getMouseX());
        System.out.println(Game.MI.getMouseY());
        guiLayer.update();
    }

    @Override
    public void render(Graphics g) {
        gui(g);
    }

    @Override
    public void gui(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        // Define the spacing between grid lines
    int gridSpacing = 128; // You can adjust this value to change the spacing
    
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

        if (guiLayer != null)
            guiLayer.render(g);
    }
    
    @Override
    public void onDestroy() {
        guiLayer = null;
    }


}
