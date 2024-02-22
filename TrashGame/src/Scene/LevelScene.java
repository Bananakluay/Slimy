package Scene;



import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;

import Prefabs.Box;
import Prefabs.Player.PlayerManager;
import entity.Entity;
import level.LevelManager;

public class LevelScene extends Scene{
    public LevelScene(){
        init();
        ready();
    }


    @Override
    public void init() {
        LevelManager.get(this);
        PlayerManager.get(this);

        Box box = new Box("Box", TILES_SIZE*11, TILES_SIZE*10, TILES_SIZE, TILES_SIZE, Color.BLACK, 10, 1, true);
        addEntity(box);
        renderer.submit(box);

        Box box1 = new Box("Box1", TILES_SIZE*12, TILES_SIZE*10, 2*TILES_SIZE, 2*TILES_SIZE, Color.BLACK, 10, 1, true);
        addEntity(box1);
        renderer.submit(box1);

        Box box2 = new Box("Box2", TILES_SIZE*13, TILES_SIZE*10, TILES_SIZE, TILES_SIZE, Color.BLACK, 10, 1, true);
        addEntity(box2);
        renderer.submit(box2);
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

    }


    
}
