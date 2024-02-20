package Scene;

<<<<<<< HEAD
import static util.Constants.Game.TILES_SIZE;

import java.awt.Graphics;

import components.SubSprite;
=======

import static util.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import components.Controller;

import Prefabs.Box;
>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66
import entity.Entity;
import entity.EntityType;
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
        Box player = new Box("Green", 10*TILES_SIZE, 6*TILES_SIZE, TILES_SIZE, TILES_SIZE,Color.GREEN,1f,2f, true);
        player.tag = EntityType.PLAYER;
        player.addComponent(new Controller());
        addEntity(player);
        renderer.submit(player);

        Box bw = new Box("Yellow", 12*TILES_SIZE, 3*TILES_SIZE, TILES_SIZE, TILES_SIZE,Color.YELLOW,7f, 1f,true);
        addEntity(bw);
        renderer.submit(bw);

        Box bw2 = new Box("Blue", 12*TILES_SIZE, 7*TILES_SIZE, TILES_SIZE, TILES_SIZE,Color.BLUE,5f ,1f,true);
        addEntity(bw2);
        renderer.submit(bw2);

        Box bw3 = new Box("Box3", 10*TILES_SIZE, 2*TILES_SIZE, 2*TILES_SIZE, 2*TILES_SIZE,null,10f, 1f,true);
        addEntity(bw3);
        renderer.submit(bw3);

        Box bw4 = new Box("Box4", 10*TILES_SIZE, 4*TILES_SIZE, 2*TILES_SIZE, 2*TILES_SIZE,null,10f, 1f,true);
        addEntity(bw4);
        renderer.submit(bw4);
        

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
