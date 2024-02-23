package Scene;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Prefabs.Player.PlayerManager;
import entity.Entity;
import level.LevelManager;
import main.Game;

import java.awt.Color;
import static utils.Constants.Game.*;

public class MenuScene extends Scene{

    public MenuScene(){
        ready();
    }
    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.drawString("MENU", GAME_WIDTH / 2, 200);
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
