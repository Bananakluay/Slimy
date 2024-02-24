package Scene;

import static utils.Constants.Game.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.joml.Vector2i;

import Prefabs.Player.PlayerManager;
import dataStructure.AssetPool;
import entity.Entity;
import file.FileLoader;
import level.LevelManager;
import main.Game;
import main.Window;
import ui.ClickListener;
import ui.GuiButton;
import ui.GuiLayer;
import ui.GuiText;

public class MenuScene extends Scene{  
    private BufferedImage image;

    @Override
    public void init() {
        
        GuiLayer.getInstance().addGuiComponent(new GuiButton(
            "Button", 
            new Vector2i(100, 100),
            new Vector2i(230, 48), 
            "TrashGame/res/something/button_layout.png", 
            () -> SceneManager.changeScene(Scenes.LEVEL_SCENE)
            
            )
        );
    }

    public BufferedImage loadResources() {
        return AssetPool.getBufferedImage("TrashGame/res/something/button layout.png", 48, 48);
    }

    @Override
    public void draw(Graphics g) {
        init();
    }

    @Override
    public void update() {
        if (Game.KI.onPress(KeyEvent.VK_ESCAPE)) {
            LevelManager.onDestroy();
            PlayerManager.onDestroy();
            SceneManager.changeScene(Scenes.LEVEL_SCENE);
        }
    }

    @Override
    public void onDestroy() {
        for (Entity entity : entities) {
            entity.onDestroy();
        }
    }
    
}
