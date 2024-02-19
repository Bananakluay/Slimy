package Scene;

import static util.Constants.Game.TILES_SIZE;

import java.awt.Graphics;

import components.SubSprite;
import entity.Entity;
import level.LevelManager;
import physics.Prefabs.TileBlock;

public class LevelScene extends Scene{

    public LevelScene(){
        init();
        ready();
    }

    @Override
    public void init() {
        LevelManager.get();
        getAllEntities().addAll(LevelManager.getCurrentLevel().getAllEntities());
        renderer.submitAll(entities);
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
