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
            allButtonActive = true;
            if (!button.isActive()) {
                allButtonActive = false;
                break;
            }
        }

        if (allButtonActive) {
            open();
        } else {
            close();
        }

    }

    private void open() {
        this.getComponent(Bounds.class).setBound(
                this.getTransform().position.x,
                this.getTransform().position.y,
                this.getTransform().scale.x,
                this.getTransform().scale.y * 0.3f);
    }

    private void close() {
        this.getComponent(Bounds.class).setBound(
                this.getTransform().position.x,
                this.getTransform().position.y,
                this.getTransform().scale.x,
                2 * TILES_SIZE);
    }

    public void addListener(Button button) {
        buttons.add(button);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (allButtonActive) {
            g.drawImage(img,
                        (int) this.getPosition().x,
                        (int) this.getPosition().y,
                        (int) this.getScale().x,
                        (int) this.getScale().y, 
                        null);

        } else {
            g.drawImage(img,
                    (int) this.getPosition().x,
                    (int) this.getPosition().y,
                    (int) this.getScale().x,
                    (int) this.getScale().y, null);
        }
    }

}
