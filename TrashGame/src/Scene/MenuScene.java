package scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dataStructure.AssetPool;
import gui.GuiButton;
import gui.GuiLayer;
import gui.GuiText;
import main.Game;
import utils.Vec2;

public class MenuScene extends Scene {
    private GuiLayer guiLayer;

    public MenuScene() {
        init();
    }


    public void init() {
        guiLayer = new GuiLayer(); // layer

        GuiButton playButton = new GuiButton( /* gen paly button */
                "PlayButton",
                new Vec2(GAME_WIDTH / 2 - (33f * SCALE), GAME_HEIGHT * 0.84f), // position
                new Vec2(33f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PlayButton.png", 33, 16),
                () -> SceneManager.changeScene(Scenes.LEVEL_SELECT_SCENE));

        GuiButton optionButton = new GuiButton( /* gen option button */
                "PlayButton",
                new Vec2(GAME_WIDTH - (50f * SCALE), GAME_HEIGHT * 0.84f), // position
                new Vec2(16f * SCALE * 2, 16f * SCALE * 2),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/OptionButton.png", 16, 16), // change png later
                () -> SceneManager.changeScene(Scenes.OPTION_SCENE));

        guiLayer.addGuiComponent(playButton); // add to guilayer
        guiLayer.addGuiComponent(optionButton);
    }

    @Override
    public void update() {
        guiLayer.update();
        if (Game.KI.onPress(KeyEvent.VK_ENTER)) {
            SceneManager.changeScene(Scenes.LEVEL_SCENE);
        }
    }

    @Override
    public void render(Graphics g) {
        gui(g);
    }

    @Override
    public void gui(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        GuiText.drawString(
                g,
                "Slimey",
                new Vec2(GAME_WIDTH / 2, GAME_HEIGHT * 0.1f),
                Color.BLACK,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", 32 * 10));
        if (guiLayer != null)
            guiLayer.render(g);
    }

    @Override
    public void onDestroy() {
        guiLayer = null;
    }

}
