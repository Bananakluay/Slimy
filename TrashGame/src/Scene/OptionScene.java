package scene;

import java.awt.Color;
import java.awt.Graphics;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import gui.GuiButton;
import gui.GuiLayer;
import dataStructure.AssetPool;
import utils.Vec2;

public class OptionScene extends Scene{
    private GuiLayer guiLayer;

    public OptionScene(){
        init();
    }

    @Override
    public void init() {
        guiLayer = new GuiLayer();

        GuiButton optionButton = new GuiButton( /*gen option button*/
            "optionButton", 
            new Vec2(GAME_WIDTH - (50f*SCALE), GAME_HEIGHT*0.84f), //position
            new Vec2(16f*SCALE*2, 16f*SCALE*2), 
            AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToLSMButton.png", 16, 16), // change png later
            ()-> SceneManager.changeScene(Scenes.MENU_SCENE));

            guiLayer.addGuiComponent(optionButton);
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
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT); // draw center

        if(guiLayer != null)
            guiLayer.render(g);
    }

    @Override
    public void onDestroy() {
        System.out.println("destroy");
            guiLayer = null;
    }
    
}
