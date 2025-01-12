package prefabs.objects;

import static utils.Constants.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import components.Detector;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import interaction.Behavior;
import sound.Sound;
import utils.Vec2;

public class Button extends Entity implements Behavior {

    List<BufferedImage> img;
    private boolean isActive = false;

    public Button(String name, float x, float y) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)), 1);
        init();
    }

    private void init() {
        type = EntityType.BUTTON;
        this.addComponent(new Detector(
                (int) this.getTransform().position.x + TILES_SIZE * 0.1f,
                (int) this.getTransform().position.y + TILES_SIZE * 0.8f,
                TILES_SIZE * 0.8f,
                TILES_SIZE * 0.2f,
                List.of(EntityType.PLAYER, EntityType.BOX),
                this,
                false));
        img = AssetPool.getBufferedImageList("TrashGame/res/assets/Object/button.png", 16, 16);
    }

    @Override
    public void activateOn(Entity entity) {
        isActive = true;
        Sound.BLIP_ON.play(false);
    }

    @Override
    public void activateOff() {
        isActive = false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (isActive) {
            g.drawImage(
                    img.get(1),
                    (int) this.getTransform().position.x,
                    (int) this.getTransform().position.y,
                    (int) this.getTransform().scale.x,
                    (int) this.getTransform().scale.y,
                    null);
        } else {
            g.drawImage(
                    img.get(0),
                    (int) this.getTransform().position.x,
                    (int) this.getTransform().position.y,
                    (int) this.getTransform().scale.x,
                    (int) this.getTransform().scale.y,
                    null);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void activateOneShot(Entity entity) {
    }

}
