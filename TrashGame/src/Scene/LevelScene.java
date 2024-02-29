package Scene;

import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.util.List;

import Level.LevelManager;
import Prefabs.Box;
import Prefabs.Button;
import Prefabs.Gate;
import Prefabs.Player.PlayerManager;
import entity.Entity;

public class LevelScene extends Scene {
    public LevelScene() {
        init();
        ready();
    }

    @Override
    public void init() {
        LevelManager.get(this);
        PlayerManager.get(this);
        
        Gate g = new Gate("Gate", TILES_SIZE*10, TILES_SIZE*11);
        addEntity(g);
        renderer.submit(g);

        Button b = new Button("Button", TILES_SIZE*8, TILES_SIZE*12, g);
        addEntity(b);
        renderer.submit(b);

        Box box = new Box("box", TILES_SIZE*4, TILES_SIZE*2, TILES_SIZE, TILES_SIZE, null, 10, 2, true);
        addEntity(box);
        renderer.submit(box);
        


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
