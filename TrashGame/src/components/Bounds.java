package components;

import static entity.EntityType.PLAYER;
import static utils.Constants.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.EntityType;
import physics.Collision;
import prefabs.objects.Platform;
import prefabs.player.Player;
import scene.LevelScene;
import utils.Vec2;

public class Bounds extends Component {
    private Color color;

    private Rectangle2D.Float boundsX;
    private Rectangle2D.Float boundsY;

    private Rectangle2D.Float interectBounds;

    private float interectBoundsoffset = 10 * SCALE;

    private boolean DEBUG = false;

    public Bounds(Color color, List<EntityType> types) {
        this.color = color != null ? color : Color.WHITE;

        boundsX = new Rectangle2D.Float();
        boundsY = new Rectangle2D.Float();

        interectBounds = new Rectangle2D.Float();
        
    }

    @Override
    public void update() {

        if (entity.getType() == PLAYER) {
            interectBounds.setRect(
                    entity.getPosition().x - interectBoundsoffset / 2f,
                    entity.getPosition().y - interectBoundsoffset / 2f,
                    entity.getScale().x + interectBoundsoffset,
                    entity.getScale().y + interectBoundsoffset);
        }

        boundsX = getBoundsX(entity.getPosition().x, entity.getPosition().y, entity.getScale().x, entity.getScale().y);
        boundsY = getBoundsY(entity.getPosition().x, entity.getPosition().y, entity.getScale().x, entity.getScale().y);
    }

    public List<Collision> checkCollision(Vec2 velocity) {

        List<Collision> results = new ArrayList<>();

        Rectangle2D.Float predictedBoundsX = new Rectangle2D.Float(
                boundsX.x + velocity.x,
                boundsX.y, boundsX.width,
                boundsX.height);

        Rectangle2D.Float predictedBoundsY = new Rectangle2D.Float(
                boundsY.x,
                boundsY.y + velocity.y,
                boundsY.width,
                boundsY.height);

        for (Entity entity : LevelScene.getEntityManager().getEntitiesWithComponent(Bounds.class)) {

            // avoid self collision
            if (entity.equals(this.entity))
                continue;

            // handle one way platform
            if (this.entity instanceof Player player && entity instanceof Platform platform) {
                if (platform.isMovable(player)) {
                    continue;
                }
            }

            // check collision
            if (predictedBoundsX.intersects(entity.getComponent(Bounds.class).boundsX) ||
                    predictedBoundsY.intersects(entity.getComponent(Bounds.class).boundsY)) {
                results.add(new Collision(this.entity, entity));
            }

        }
        return results;
    }

    private Rectangle2D.Float getBoundsX(float x, float y, float w, float h) {
        float bx = x;
        float by = y + 2;
        float bw = w;
        float bh = h - 4;

        return new Rectangle2D.Float(bx, by, bw, bh);
    }

    private Rectangle2D.Float getBoundsY(float x, float y, float w, float h) {
        float bx = x + 2;
        float by = y;
        float bw = w - 4;
        float bh = h;

        return new Rectangle2D.Float(bx, by, bw, bh);
    }

    public Rectangle2D.Float getBoundsX() {
        return boundsX;
    }

    public Rectangle2D.Float getBoundsY() {
        return boundsY;
    }

    public Rectangle2D.Float getInterectBounds() {
        return interectBounds;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setBound(float x, float y, float width, float height) {
        entity.getTransform().position.x = x;
        entity.getTransform().position.y = y;
        entity.getTransform().scale.x = width;
        entity.getTransform().scale.y = height;

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (DEBUG) {
            g2d.setColor(color);
            if (true) {
                g2d.draw(boundsX);
                g2d.draw(boundsY);
            }

        }
    }

}
