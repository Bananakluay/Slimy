package prefabs.player;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import prefabs.player.character.*;
import scene.LevelScene;
import components.Bounds;
import components.Controller;
import entity.Entity;
import level.LevelManager;
import main.Game;

import static prefabs.player.SlimeType.*;
import static prefabs.player.SlimeMode.*;
import static utils.Constants.Game.SCALE;
import static utils.Constants.Player.*;

public class PlayerManager {

    private static Map<String, Player> slimes;
    private static SlimeMode mode;

    public PlayerManager() {
        slimes = new HashMap<>();
        mode = DUAL;
    }

    public void update() {
        // switch mode SINGLE / DUAL
        if (Game.KI.onPress(KeyEvent.VK_SPACE)) {
            if (mode == SINGLE) {
                splitSlime();
            } else if (mode == DUAL) {
                mergeSlime();
            }
        }

        // switch Player
        if (Game.KI.onPress(KeyEvent.VK_R)) {
            switchPlayer();
        }

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

        LevelScene.getEntityManager().removeEntity(slime);

    }

    public void splitSlime() {
        if (mode != SINGLE || !slimes.containsKey(BLUE)) {
            return;
        }

        // get position of Blue
        Player blueSlime = slimes.get(BLUE);
        float x = blueSlime.getTransform().position.x;
        float y = blueSlime.getTransform().position.y;

        spawnSlime(GREEN, x, y - 0.1f, TINY_SLIME);
        spawnSlime(YELLOW, x, y, TINY_SLIME);

        // Remove Blue
        removeSlime(BLUE);

        mode = DUAL;
    }

    public void mergeSlime() {

        if (mode != DUAL || !slimes.get(GREEN).isAlive() || !slimes.get(YELLOW).isAlive()) {
            return;
        }
        Entity greenSlime = slimes.get(GREEN);
        Entity yellowSlime = slimes.get(YELLOW);

        // check is within range
        if (!greenSlime.getComponent(Bounds.class).interectBounds
                .intersects(yellowSlime.getComponent(Bounds.class).interectBounds)) {
            return;
        }

        Player activeSlime = null;
        Player inactiveSlime = null;

        for (Player slime : slimes.values()) {
            if (slime.isAlive() && slime.isActive()) {
                activeSlime = slime;
            } else if (slime.isAlive() && !slime.isActive()) {
                inactiveSlime = slime;
            }
        }

        if (activeSlime != null && inactiveSlime != null) {

            float inactiveX = inactiveSlime.getPosition().x;
            float inactiveY = inactiveSlime.getPosition().y;

            spawnSlime(BLUE, inactiveX, inactiveY - 10 * SCALE, LARGE_SLIME);

        }

        // Remove Green and Yellow
        removeSlime(GREEN);
        removeSlime(YELLOW);

        mode = SINGLE;
    }

    public static void switchPlayer() {
        if (mode == SINGLE)
            return;

        Player green = slimes.get(GREEN);
        Player yellow = slimes.get(YELLOW);

        if (!green.isAlive() || !yellow.isAlive()) {
            return;
        }

        if (green.isActive() && yellow.isAlive()) {
            green.setActive(false);
            yellow.setActive(true);
        } else if (yellow.isActive() && green.isAlive()) {
            green.setActive(true);
            yellow.setActive(false);
        } else {
            green.setActive(false);
            yellow.setActive(false);
        }

        System.out.println(green.isActive() + " " + yellow.isActive());

    }

    public static void clear() {
        slimes.clear();
        mode = DUAL;
    }

    public static void resetIfDead() {
        Player blue = slimes.get(BLUE);
        Player green = slimes.get(GREEN);
        Player yellow = slimes.get(YELLOW);

        if (blue != null && !blue.isAlive()) {
            LevelManager.resetLevel();
        } else if (green != null && yellow != null && !green.isAlive() && !yellow.isAlive()) {
            LevelManager.resetLevel();
        }

    }

}
