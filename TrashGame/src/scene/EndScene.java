package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dataStructure.AssetPool;
import gui.GuiButton;
import gui.GuiLayer;
import gui.GuiText;
import utils.Vec2;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

public class EndScene extends Scene{
    private GuiLayer guiLayer;
    private BufferedImage bg = AssetPool.getBufferedImage("TrashGame/res/assets/Background/background.png", 64, 36);

    public EndScene() {
        init();
    }

    public void init() {
        guiLayer = new GuiLayer();

        GuiButton backButton = new GuiButton( /* gen option button */
                "backButton",
                new Vec2((GAME_WIDTH - (16f * SCALE * 3)) / 2, GAME_HEIGHT * 0.6f), // position
                new Vec2(16f * SCALE * 3, 16f * SCALE * 3),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToMenuButton.png", 16, 16),
                () -> SceneManager.changeScene(Scenes.MENU_SCENE));
                
        guiLayer.addGuiComponent(backButton);
    }

    @Override
    public void update() {
        guiLayer.update();
    }

    @Override
    public void render(Graphics g) {
        gui(g);
    }

    @Override
    public void gui(Graphics g) {
        drawBackGround(g);

        GuiText.drawString(
                g,
                "YOU WIN!",
                new Vec2((float) (GAME_WIDTH / 2) , (GAME_HEIGHT / 2) - 64),
                Color.WHITE,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", (int) (32 * SCALE * 4)));

        if (guiLayer != null)
            guiLayer.render(g);
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
    public void onDestroy() {
        System.out.println("destroy");
        guiLayer = null;
    }

}
