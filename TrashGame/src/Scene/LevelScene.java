package Scene;

import java.awt.Graphics;
import java.util.List;

import Prefabs.Player.PlayerManager;
import entity.Entity;
import level.LevelManager;

public class LevelScene extends Scene {
    public LevelScene() {
        init();
        ready();
    }

    @Override
    public void init() {
        LevelManager.get(this);
        PlayerManager.get(this);
    }

    @Override
    public void update() {
        PlayerManager.update();
        for (Entity entity : this.entities) {
            entity.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        renderer.render(g);
    }

    @Override
    public void onDestroy() {
        for (Entity entity : entities) {
            entity.onDestroy();
        }
    }

    public List<Entity> getEntity() {
        return entities;
    }

}
