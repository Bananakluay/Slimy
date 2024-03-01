package scene;

import java.awt.Color;
import java.awt.Graphics;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import gui.GuiButton;
import gui.GuiLayer;
import gui.GuiText;
import gui.ToggleGuiButton;
import dataStructure.AssetPool;
import utils.Vec2;

public class OptionScene extends Scene{
    private GuiLayer guiLayer;

    public OptionScene(){
        init();
    }

    public void init() {
        guiLayer = new GuiLayer();

        
        GuiButton backButton = new GuiButton( /*gen option button*/
            "backButton", 
            new Vec2(GAME_WIDTH - (50f*SCALE), GAME_HEIGHT*0.84f), //position
            new Vec2(16f*SCALE*2, 16f*SCALE*2), 
            AssetPool.getBufferedImageList("TrashGame/res/assets/ui/BackToMenuButton.png", 16, 16), // change png later
            ()-> SceneManager.changeScene(Scenes.MENU_SCENE));

        ToggleGuiButton soundButton = new ToggleGuiButton(
                "SoundButton",
                new Vec2((float) (TILES_SIZE * 17.5), TILES_SIZE * 9),
                new Vec2(16f * SCALE * 1.5f, 16f * SCALE * 1.5f),
                AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16),
                () -> System.out.println("SoundButton"));

        ToggleGuiButton musicButton = new ToggleGuiButton(
                    "musicButton",
                    new Vec2((float) (TILES_SIZE * 17.5), TILES_SIZE * 7),
                    new Vec2(16f * SCALE * 1.5f, 16f * SCALE * 1.5f),
                    AssetPool.getBufferedImageList("TrashGame/res/assets/ui/MusicButton.png", 16, 16),
                    () -> System.out.println("musicButton"));
        
        guiLayer.addGuiComponent(musicButton);
        guiLayer.addGuiComponent(backButton);
        guiLayer.addGuiComponent(soundButton);
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

        GuiText.drawString(
                g,
                "music",
                new Vec2((float) (GAME_WIDTH / 2.1), GAME_HEIGHT * 0.425f),
                Color.BLACK,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", (int) (32 * SCALE)));
        
        GuiText.drawString(
                g,
                "sound",
                new Vec2((float) (GAME_WIDTH / 2.1), GAME_HEIGHT * 0.535f),
                Color.BLACK,
                AssetPool.getFont("TrashGame/res/assets/fonts/m3x6.ttf", (int) (32 * SCALE)));

        if(guiLayer != null)
            guiLayer.render(g);
    }

    @Override
    public void onDestroy() {
        System.out.println("destroy");
            guiLayer = null;
    }
    
}
