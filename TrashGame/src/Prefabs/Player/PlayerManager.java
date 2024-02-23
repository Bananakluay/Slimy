package Prefabs.Player;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.time.Year;

import Prefabs.Player.Character.LargeSlime;
import Prefabs.Player.Character.TinySlime;
import Scene.Scene;
import Scene.Scenes;
import Scene.SceneManager;

import components.Bounds;
import components.Controller;
import entity.Entity;
import main.Game;
import Prefabs.Spike;
import Scene.SceneManager;

import static Prefabs.Player.SlimeStatus.*;
import static Prefabs.Player.SlimeType.*;
import static utils.Constants.Layer.*;
import static utils.Constants.Player.*;
import static entity.EntityType.*;

public class PlayerManager {

    private static PlayerManager playerManager = null;
    // [0] LargeSlime(blue) | [1] TinySlime(Green) | [2] TinySlime(Yellow)

    public static LargeSlime blueLargeSlime = null;
    private static TinySlime greenTinySlime = null;
    private static TinySlime yellowTinySlime = null;
    private static SlimeStatus status = MERGED;

    private static Scene levelScene;

    private PlayerManager(Scene levelScene) {
        PlayerManager.levelScene = levelScene;
    }

    public static PlayerManager get(Scene levelScene) {
        if (playerManager == null)
            playerManager = new PlayerManager(levelScene);
        return playerManager;

    }

    public static void update() {
        if (Game.KI.onPress(KeyEvent.VK_Q)) {
            if (status == MERGED) {
                splitSlime();
            } else if (status == SPLIT) {
                mergeSlime();
            }
        }
        if (Game.KI.onPress(KeyEvent.VK_R)) {
            switchPlayer();
        }
        if (Game.KI.onPress(KeyEvent.VK_ESCAPE)) {
            SceneManager.changeScene(Scenes.MENU_SCENE);
        }

        Died();
    }

    public static void spawnSlime(String name, float x, float y, SlimeType type) {
        if (type == LARGE_SLIME && name.equals(BLUE)) {
            blueLargeSlime = new LargeSlime(BLUE, x, y);
            blueLargeSlime.getComponent(Bounds.class).setColor(Color.blue);

        } else if (type == TINY_SLIME) {
            if (name.equals(GREEN)) {
                greenTinySlime = new TinySlime(GREEN, x, y, "TrashGame/res/assets/Character/GreenSlime.png");
                greenTinySlime.getComponent(Bounds.class).setColor(Color.green);
                greenTinySlime.getComponent(Controller.class).setActive(true);

            } else if (name.equals(YELLOW)) {
                yellowTinySlime = new TinySlime(YELLOW, x, y, "TrashGame/res/assets/Character/YellowSlime.png");
                yellowTinySlime.getComponent(Controller.class).setActive(false);
                yellowTinySlime.getComponent(Bounds.class).setColor(Color.yellow);

            }
        }
    }

    public static void splitSlime() {
        System.out.println("split Here");
        if (blueLargeSlime == null) {
            System.out.println(blueLargeSlime.getName() + " is Null");
            return;
        }
        if (status == MERGED) {

            // create
            spawnSlime(GREEN, blueLargeSlime.getTransform().position.x, blueLargeSlime.getTransform().position.y,
                    TINY_SLIME);
            spawnSlime(YELLOW, greenTinySlime.getTransform().position.x + greenTinySlime.getTransform().scale.x,
                    blueLargeSlime.getTransform().position.y, TINY_SLIME);

            greenTinySlime.getComponent(Controller.class).setActive(true);
            yellowTinySlime.getComponent(Controller.class).setActive(false);

            // add duel slime
            levelScene.addEntity(greenTinySlime);
            levelScene.addEntity(yellowTinySlime);
            levelScene.renderer.submit(greenTinySlime);
            levelScene.renderer.submit(yellowTinySlime);

            // remove single slime
            levelScene.removeEntity(BLUE, PLAYER);
            levelScene.renderer.remove(BLUE, PLAYER_LAYER);
            status = SPLIT;
        }

    }

    public static <T extends Entity> void mergeSlime() {
        System.out.println("merge");
        if (greenTinySlime == null && yellowTinySlime == null) {
            System.out.println(greenTinySlime.getName() + " and " + yellowTinySlime.getName() + " is Null");
            return;
        }

        Bounds greenBounds = greenTinySlime.getComponent(Bounds.class);
        Bounds yellowBounds = yellowTinySlime.getComponent(Bounds.class);
        if (greenBounds.interectBounds.intersects(yellowBounds.interectBounds)) {
            // create
            spawnSlime(BLUE, greenTinySlime.getTransform().position.x, greenTinySlime.getTransform().position.y,
                    LARGE_SLIME);
            blueLargeSlime.getComponent(Controller.class).setActive(true);

            // remove
            levelScene.removeEntity(GREEN, PLAYER);
            levelScene.removeEntity(YELLOW, PLAYER);

            levelScene.renderer.remove(GREEN, PLAYER_LAYER);
            levelScene.renderer.remove(YELLOW, PLAYER_LAYER);

            // add
            levelScene.addEntity(blueLargeSlime);
            levelScene.renderer.submit(blueLargeSlime);

            status = MERGED;
        }

    }

    public static void switchPlayer() {
        if (status == MERGED)
            return;

        Controller s1 = greenTinySlime.getComponent(Controller.class);
        Controller s2 = yellowTinySlime.getComponent(Controller.class);

        if (s1.isActive) {
            s1.setActive(false);
            s2.setActive(true);
        } else if (s2.isActive) {
            s1.setActive(true);
            s2.setActive(false);
        }

    }

    public static void onDestroy() {
        playerManager = null;
        status = MERGED;
    }

    public static void Died() {
        if (Spike.died_green || Spike.died_yellow) {
            Controller s1 = greenTinySlime.getComponent(Controller.class);
            Controller s2 = yellowTinySlime.getComponent(Controller.class);

            if (Spike.died_green) {
                levelScene.removeEntity(GREEN, PLAYER);

                levelScene.renderer.remove(GREEN, PLAYER_LAYER);
                s1.setActive(false);
                s2.setActive(true);
                // Spike.died_green = false;
                // greenTinySlime = null;
            } else if (Spike.died_yellow) {
                levelScene.removeEntity(YELLOW, PLAYER);

                levelScene.renderer.remove(YELLOW, PLAYER_LAYER);
                // Spike.died_yellow = false;
                s1.setActive(true);
                s2.setActive(false);
                // yellowTinySlime = null;
            }
        }
        if (Spike.died_blue) {
            levelScene.removeEntity(BLUE, PLAYER);

            levelScene.renderer.remove(BLUE, PLAYER_LAYER);
            // aadawdwas3.setActive(false);
            // blueLargeSlime = null;
        }
        SceneManager.restart();
    }
}
