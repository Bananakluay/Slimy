package Scene;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;

import Prefabs.Objects.Box;
import Prefabs.Objects.Button;
import Prefabs.Objects.Gate;
import Prefabs.Player.PlayerManager;
import Prefabs.Trap.FakeButton;
import Prefabs.Trap.Spike;
import entity.Entity;
import entity.EntityManager;

import level.LevelManager;

public class LevelScene extends Scene {

    private static PlayerManager playerManager;

    private static EntityManager entitiyManager;
    public LevelScene() {
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();
        init();
    }

    @Override
    public void init() {
        loadLevels();
        // Button b = new Button("Button", TILES_SIZE*8, TILES_SIZE*12);
        // entitiyManager.addEntity(b);
        // Button b2 = new Button("Button", TILES_SIZE*5, TILES_SIZE*12);
        // entitiyManager.addEntity(b2);

        // Gate g = new Gate("Gate", TILES_SIZE*10, TILES_SIZE*11);
        // entitiyManager.addEntity(g);
        // g.addListener(b);
        // g.addListener(b2);

        Box box = new Box("box", TILES_SIZE*4, TILES_SIZE*2, TILES_SIZE, TILES_SIZE, null, 3f*SCALE, 0.67f*SCALE);
        entitiyManager.addEntity(box);

        FakeButton fb = new FakeButton(TILES_SIZE*8, TILES_SIZE*12);
        entitiyManager.addEntity(fb);

        Spike sp = new Spike("spike", TILES_SIZE*10, TILES_SIZE*12);
        entitiyManager.addEntity(sp);
        entitiyManager.ready();
        
    }

    @Override
    public void update() {
        playerManager.update();
        entitiyManager.updateEntities();
        // System.out.println("Entity : " + entitiyManager.getAllEntities().size() +" Renderer : " +renderer.size(PLAYER_LAYER));
        

    }

    @Override
    public void draw(Graphics g) {
        renderer.render(g);
    }

    private void loadLevels(){
        LevelManager.get();
    }
    
    @Override
    public void onDestroy() {
        for (Entity entity : entitiyManager.getAllEntities()) {
            entity.onDestroy();
            entitiyManager.removeEntity(entity);
        }
    }

    public static void deleteCurrentLevel(){
        for (Entity entity : entitiyManager.getAllEntities()) {
            entity.onDestroy();
        }
        playerManager = null;
    }

    public static void createNextLevel(){
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();
        renderer.clear();
    }

    public static EntityManager getEntityManager(){
        return entitiyManager;
    }

    public static PlayerManager getPlayerManager(){
        return playerManager;
    }

}
