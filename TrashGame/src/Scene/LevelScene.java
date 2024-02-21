package Scene;


import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;

import components.Controller;

import Prefabs.Box;
import Prefabs.Spike;
import Prefabs.Player.PlayerManager;
import entity.Entity;
import entity.EntityType;
import level.LevelManager;

public class LevelScene extends Scene{
    public static LevelManager levelManager;
    public static PlayerManager playerManager;
    public LevelScene(){
        init();
        ready();
    }

    @Override
    public void init() {
        levelManager = LevelManager.get();
        playerManager = PlayerManager.get();
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        renderer.render(g);
    }

    @Override
    public void onDestroy() {

    }

    
}
