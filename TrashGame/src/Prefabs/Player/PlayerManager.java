package Prefabs.Player;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;


import Prefabs.Player.Character.*;
import Scene.LevelScene;
import components.Bounds;
import components.Controller;
import entity.Entity;
import level.LevelManager;
import main.Game;

import static Prefabs.Player.SlimeType.*;
import static Prefabs.Player.SlimeMode.*;
import static utils.Constants.Player.*;

public class PlayerManager {


    private static Map<String, Player> slimes;
    private static SlimeMode mode = SINGLE;

    public PlayerManager() {
        this.slimes = new HashMap<>();
        this.mode = SINGLE;
    }

    public void update() {

        //switch mode SINGLE / DUAL
        if (Game.KI.onPress(KeyEvent.VK_Q)) {
            if (mode == SINGLE) {
                splitSlime();
            } else if (mode == DUAL) {
                mergeSlime();
            }
        }

        //switch player
        if (Game.KI.onPress(KeyEvent.VK_R)) {
            switchPlayer();
        }
        // System.out.println(isDead());
        //checkStatus
        resetIfDead();
        
    }

    public void spawnSlime(String name, float x, float y, SlimeType type) {
        // spawn LARGE SLIME
        if (type == LARGE_SLIME && name.equals(BLUE)) {
            LargeSlime slime = new LargeSlime(BLUE, x, y);
            slime.getComponent(Bounds.class).setColor(Color.blue);
            slimes.put(name, slime);
            LevelScene.getEntityManager().addEntity(slime);
        // spawn TINY SLIME
        } else if (type == TINY_SLIME) {
            if (name.equals(GREEN)) {
                TinySlime slime = new TinySlime(GREEN, x, y, "TrashGame/res/assets/Character/GreenSlime.png");
                slime.getComponent(Bounds.class).setColor(Color.green);
                slime.getComponent(Controller.class).setActive(true);
                slimes.put(name, slime);
                LevelScene.getEntityManager().addEntity(slime);

            } else if (name.equals(YELLOW)) {
                TinySlime slime = new TinySlime(YELLOW, x, y, "TrashGame/res/assets/Character/YellowSlime.png");
                slime.getComponent(Bounds.class).setColor(Color.yellow);
                slime.getComponent(Controller.class).setActive(false);
                slimes.put(name, slime);
                LevelScene.getEntityManager().addEntity(slime);
            }
        }
    }

    private void removeSlime(String name) {
        Entity slime = slimes.remove(name);
        if (slime != null) {
            LevelScene.getEntityManager().removeEntity(slime);
            LevelScene.getRenderer().remove(slime,slime.getZindex());
        }
    }

    public void splitSlime() {
        if (mode != SINGLE || !slimes.containsKey(BLUE)) {
            return;
        }

        //get position of Blue
        Entity blueSlime = slimes.get(BLUE);
        float x = blueSlime.getTransform().position.x;
        float y = blueSlime.getTransform().position.y;

        //Spawn Green and Yellow
        spawnSlime(GREEN, x, y, TINY_SLIME);
        spawnSlime(YELLOW, x + blueSlime.getTransform().scale.x, y, TINY_SLIME);

        //Remove Blue
        removeSlime(BLUE);

        mode = DUAL;
        }
    
    public void mergeSlime(){

        if(mode != DUAL || !slimes.containsKey(GREEN) || !slimes.containsKey(YELLOW)){
            return;
        }

        Entity greenSlime = slimes.get(GREEN);
        Entity yellowSlime = slimes.get(YELLOW);

        // check is within range
        if (!greenSlime.getComponent(Bounds.class).interectBounds.intersects(yellowSlime.getComponent(Bounds.class).interectBounds)) {
            return;
        }

        //Spawn Blue
        spawnSlime(BLUE, greenSlime.getTransform().position.x, greenSlime.getTransform().position.y, LARGE_SLIME);

        //Remove Green and Yellow
        removeSlime(GREEN);
        removeSlime(YELLOW);

        mode = SINGLE;
    }
    
    public static void switchPlayer() {
        
        if (mode == SINGLE)
            return;
        if(!slimes.get(GREEN).isAlive() || !slimes.get(YELLOW).isAlive())
            return;

        Controller green = slimes.get(GREEN).getComponent(Controller.class);
        Controller yellow = slimes.get(YELLOW).getComponent(Controller.class);

        if (green.isActive) {
            green.setActive(false);
            yellow.setActive(true);
        } else if (yellow.isActive) {
            green.setActive(true);
            yellow.setActive(false);
        }

    }
    
    private void resetIfDead() {
        Player blue = slimes.get(BLUE);
        Player green = slimes.get(GREEN);
        Player yellow = slimes.get(YELLOW);
    
        if (blue != null && !blue.isAlive()) {
            resetLevel();
            return;
        } else if (green != null && yellow != null && !green.isAlive() && !yellow.isAlive()) {
            // Reset
            resetLevel();
            return;
        }
    }
    public void resetLevel(){
        LevelManager.resetLevel();
    }
}
