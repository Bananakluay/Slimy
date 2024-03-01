
package Prefabs.Objects;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import components.Bounds;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import utils.Constants.Layer;

public class Gate extends Entity {

    List<BufferedImage> img;
    List<Button> buttons;
    boolean allButtonActive = false;
    private float timer = 0f; // Default to 0, meaning initially closed
    private final float openingSpeed = 0.01f; // Adjust speed as needed
    private int currentIndex = 0;

    public Gate(String name, float x, float y) {
        super(name,
                new Transform(new Vec2(x, y - TILES_SIZE), new Vec2(TILES_SIZE, 2 * TILES_SIZE)), Layer.OBJECTS);
        init();
    }

    public void init() {
        type = EntityType.GATE;
        buttons = new ArrayList<>();
        img = AssetPool.getBufferedImageList("TrashGame/res/assets/Object/Gate.png", 16, 32); // Specify 4 frames
        System.out.println(img.size());
        addComponent(new Bounds(Color.DARK_GRAY, null));
    }

    @Override
    public void update() {
        super.update();
        if (buttons == null) {
            System.out.println("No button to receive signal");
            return;
        }

        for (Button button : buttons) {
            allButtonActive = button.isActive();
            if (!allButtonActive) {
                break;
            }
        }

        if (allButtonActive) {
            timer += openingSpeed;
            if (timer > 1f) {
                timer = 1f; // Clamp timer to avoid exceeding full open state
                currentIndex = img.size() - 1; // Set last frame for fully open state
            } else {
                currentIndex = (int) (timer * (img.size() - 1)); // Calculate frame based on timer
            }
        } else {
            timer -= openingSpeed;
            if (timer < 0f) {
                timer = 0f; // Clamp timer to avoid exceeding full close state
                currentIndex = 0; // Set first frame for fully closed state
            } else {
                currentIndex = (int) (timer * (img.size() - 1)); // Calculate frame based on timer
            }
        }

        float newScaleY = 2 * TILES_SIZE - 2 * TILES_SIZE * timer;
        this.getComponent(Bounds.class).setBound(
                (int) this.getPosition().x,
                (int) this.getPosition().y,
                (int) this.getScale().x,
                newScaleY);
    }

    public void addListener(Button button) {
        buttons.add(button);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (img == null) {
            System.out.println("img is null");
        }
        g.drawImage(img.get(currentIndex),
                (int) this.getPosition().x,
                (int) this.getPosition().y,
                (int) (16 * SCALE),
                (int) TILES_SIZE * 2,
                null);
    }
}