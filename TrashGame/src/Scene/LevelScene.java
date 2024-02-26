package Scene;

import static utils.Constants.Game.GAME_HEIGHT;
import static utils.Constants.Game.GAME_WIDTH;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Gui.GuiButton;
import Gui.GuiLayer;
import Gui.ToggleGuiButton;
import Prefabs.Objects.Box;
import Prefabs.Player.PlayerManager;
import Prefabs.Trap.FakeButton;
import Prefabs.Trap.Spike;
import dataStructure.AssetPool;
import entity.Entity;
import entity.EntityManager;

import level.LevelManager;
import main.Game;
import utils.Vec2;

public class LevelScene extends Scene {

    private static PlayerManager playerManager;

    private static EntityManager entitiyManager;
    private GuiLayer guiPlayingScene;
    private GuiLayer guiPauseScene;
    public LevelScene() {
        entitiyManager = new EntityManager();
        playerManager = new PlayerManager();

        guiPlayingScene = new GuiLayer();
        guiPauseScene = new GuiLayer();

        init();
        initGuiPlayingScene();
        initGuiPauseScene();
    }


    @Override
    public void init() {
        // Entity--------------------------------
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

    // Gui playing scene----------------------------------------------
    public void initGuiPlayingScene(){

        GuiButton playButton = new GuiButton(
            "PlayButton", 
            new Vec2(GAME_WIDTH/2, GAME_HEIGHT*0.8f),
            new Vec2(33f*SCALE*2, 16f*SCALE*2), 
            AssetPool.getBufferedImageList("TrashGame/res/assets/ui/PlayButton.png", 33, 16),
            ()-> System.out.println("play"));
        guiPlayingScene.addGuiComponent(playButton);
    }

    // Gui pause scene----------------------------------------------
    public void initGuiPauseScene(){
        ToggleGuiButton soundButton = new ToggleGuiButton(
            "SoundButton", 
            new Vec2(TILES_SIZE*10, TILES_SIZE*3),
            new Vec2(16f*SCALE*1.5f, 16f*SCALE*1.5f), 
            AssetPool.getBufferedImageList("TrashGame/res/assets/ui/SoundButton.png", 16, 16), 
            ()->System.out.println("SoundButton"));
        guiPauseScene.addGuiComponent(soundButton);
        
    }


    @Override
    public void update() {
        //pause and player
        if(Game.KI.onPress(KeyEvent.VK_ESCAPE)){
            if(isRunning == true){
                isRunning = false;
            }else{
                isRunning = true;
            }
        }
        
        if(isRunning){
            playerManager.update();
            entitiyManager.updateEntities();
            guiPlayingScene.update();
        }else{
            guiPauseScene.update();
        }
        // System.out.println("Entity : " + entitiyManager.getAllEntities().size() +" Renderer : " +renderer.size(PLAYER_LAYER));
 
    }

    @Override
    public void render(Graphics g) {
        if(isRunning){
            renderer.render(g);
            guiPlayingScene.render(g);
        }else{
            renderer.render(g);
            guiPauseScene.render(g);
        }
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
