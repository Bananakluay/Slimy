package prefabs.objects;

import static utils.Constants.Game.TILES_SIZE;
import utils.Constants.Layer;

import java.awt.Color;

import components.Bounds;
import components.Rigidbody;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import utils.Constants.Game;

public class Platform extends Entity {

    private boolean isPlayerOnTop;
    private boolean justslightlyWhenMoveDown;
    private static boolean isPlayerOnSide;
    public Platform(float x, float y) {
        super("platform", new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE * 0.4f)), Layer.PLATFORM);
        init();
    }

    public void init() {
        this.type = EntityType.PLATFORM;
        this.addComponent(new Bounds(Color.green, null));
        this.getComponent(Bounds.class).isOneWay = true;
    }

    public boolean isMovable(Entity player) {
        checkIsPlayerOnTop(player);
        checkIsPlayerOnSide(player);
        checkIsjustslightlyWhenMoveDown(player);
        return isPlayerOnTop && isPlayerOnSide && !justslightlyWhenMoveDown;
    }

    private void checkIsPlayerOnTop(Entity player) {
        isPlayerOnTop = player.getPosition().y + player.getScale().y > this.getPosition().y;
    }

    private void checkIsPlayerOnSide(Entity player) {
        isPlayerOnSide = player.getPosition().x + player.getScale().x > this.getPosition().x
                && player.getPosition().x < this.getPosition().x + this.getScale().x;
    }

    private void checkIsjustslightlyWhenMoveDown(Entity player) {
        justslightlyWhenMoveDown =  player.getPosition().y - this.getPosition().y + player.getScale().y < Game.SCALE
                && player.getComponent(Rigidbody.class).velocity.y > 0;
    }

}
