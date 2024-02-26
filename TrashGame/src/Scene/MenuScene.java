package Scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Gui.GuiButton;
import Gui.GuiLayer;
import Gui.GuiText;
import dataStructure.AssetPool;
import input.Keyboard.KeyManager;
import main.Game;
import utils.Vec2;

public class MenuScene extends Scene{
    private GuiLayer guiLayer;

    public MenuScene(){
        init();
    }

   
    @Override
    public void init() {
        guiLayer = new GuiLayer();

        GuiButton playButton = new GuiButton(
            "PlayButton", 
            new Vec2(GAME_WIDTH/2, GAME_HEIGHT*0.8f),
            new Vec2(33f*SCALE*2, 16f*SCALE*2), 
            AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PlayButton.png", 33, 16),
            ()-> SceneManager.changeScene(Scenes.LEVEL_SCENE));
        guiLayer.addGuiComponent(playButton);
    }

    @Override
    public void update() {
        guiLayer.update();
        if(Game.KI.onPress(KeyEvent.VK_ENTER)){
            SceneManager.changeScene(Scenes.LEVEL_SCENE);
        }
    }

    @Override
    public void render(Graphics g) {
        gui(g);
    }

    @Override
    public void gui(Graphics g) {
        GuiText.drawString(g, "Slimey", new Vec2(GAME_WIDTH/2, GAME_HEIGHT*0.1f),  Color.BLACK, AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", 32*5));
        guiLayer.render(g);
    }

    @Override
    public void onDestroy() {
        System.out.println("destroy");
        guiLayer = null;
    }

    
}
