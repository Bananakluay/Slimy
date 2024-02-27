package prefabs.trap;

import static utils.Constants.Game.TILES_SIZE;
import static utils.Constants.Layer.TRAP;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import components.Detector;
import components.Rigidbody;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import interaction.Behavior;
import prefabs.player.Player;
import sound.Sound;
import utils.Vec2;

public class BombButton extends Entity implements Behavior {

    private List<BufferedImage> imgs;
    private boolean isActive;

    public BombButton(float x, float y) {
        super("Fake Button", new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)), TRAP);
        init();
    }

    private void init() {
        type = EntityType.TRAP;
        this.addComponent(new Detector(
                (int) this.getTransform().position.x + TILES_SIZE * 0.1f,
                (int) this.getTransform().position.y + TILES_SIZE * 0.8f,
                TILES_SIZE * 0.8f,
                TILES_SIZE * 0.2f,
                List.of(EntityType.PLAYER, EntityType.BOX),
                this,
                true));
        imgs = AssetPool.getBufferedImageList("TrashGame/res/assets/Object/button.png", 16, 16);

    }

    @Override
    public void activateOneShot(Entity entity) {
        if (!entity.hasComponent(Rigidbody.class))
            return;
        if (entity instanceof Player player) {
            player.getComponent(Rigidbody.class).addForce(new Vec2(0, -20));
            player.die();
            isActive = true;
        } else {
            entity.getComponent(Rigidbody.class).addForce(new Vec2(-10, -10));
        }
        Sound.EXPLOSION.play(false);
    }

    @Override
    public void activateOn(Entity entity) {}
    
    @Override
    public void activateOff() {}

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (isActive) {
            g.drawImage(
                    imgs.get(1),
                    (int) this.getTransform().position.x,
                    (int) this.getTransform().position.y,
                    (int) this.getTransform().scale.x,
                    (int) this.getTransform().scale.y,
                    null);
        } else {
            g.drawImage(
                    imgs.get(0),
                    (int) this.getTransform().position.x,
                    (int) this.getTransform().position.y,
                    (int) this.getTransform().scale.x,
                    (int) this.getTransform().scale.y,
                    null);
        }
    }

}
