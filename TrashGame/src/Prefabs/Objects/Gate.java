
package prefabs.objects;

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

public class Gate extends Entity {

    BufferedImage img;
    List<Button> buttons;
    boolean allButtonActive = false;
    private float timer = 0f; // Default to 0, meaning initially closed
    private final float openingSpeed = 0.001f;

    public Gate(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y - TILES_SIZE), new Vec2(TILES_SIZE, 2 * TILES_SIZE)), 1);
        init();
    }

    public void init() {
        type = EntityType.GATE;
        buttons = new ArrayList<>();
        img = AssetPool.getBufferedImage("TrashGame/res/assets/Object/Gate.png", 16, 32);
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
            }
        } else {
            timer -= openingSpeed;
            if (timer < 0f) {
                timer = 0f; // Clamp timer to avoid exceeding full close state
            }
        }

        float newScaleY = 2 * TILES_SIZE * timer;
        System.out.println(newScaleY);
        this.getComponent(Bounds.class).setBound(
                this.getTransform().position.x,
                this.getTransform().position.y,
                this.getTransform().scale.x,
                newScaleY);
    }

    public void addListener(Button button) {
        buttons.add(button);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (img == null) {
            System.out.println("here");
        }

        g.drawImage(img,
                (int) this.getPosition().x,
                (int) this.getPosition().y,
                (int) this.getScale().x,
                (int) this.getScale().y,
                null);
    }
}