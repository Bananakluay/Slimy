package components;

import static entity.EntityType.PLAYER;
import static utils.Constants.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Scene.LevelScene;
import dataStructure.Transform;
import entity.Entity;
import physics.Collision;
import utils.Vec2;

public class Bounds extends Component {
    public Color color;

    public Rectangle2D.Float boundsX = new Rectangle2D.Float();
    public Rectangle2D.Float boundsY = new Rectangle2D.Float();

    public Rectangle2D.Float interectBounds = new Rectangle2D.Float();
    public float interectBoundsoffset = 10*SCALE;
    private boolean DEBUG = false;

    public Bounds(Color color){
        if(color == null)
            this.color = Color.white;
        else
            this.color = color;
    }
    @Override
    public void update() {
        Transform t = entity.getTransform();
        if(entity.getType() == PLAYER)
            interectBounds.setRect(entity.getTransform().position.x - interectBoundsoffset/2, entity.getTransform().position.y - interectBoundsoffset/2, entity.getTransform().scale.x + interectBoundsoffset, entity.getTransform().scale.y + interectBoundsoffset);
        boundsX = getBoundsX(t.position.x, t.position.y, t.scale.x, t.scale.y);
        boundsY = getBoundsY(t.position.x, t.position.y, t.scale.x, t.scale.y);
    }

    public List<Collision> checkCollision(Vec2 velocity){
        List<Collision> res = new ArrayList<>();

        Rectangle2D.Float predictedBoundsX = new Rectangle2D.Float(boundsX.x+velocity.x, boundsX.y,boundsX.width,boundsX.height);
        Rectangle2D.Float predictedBoundsY = new Rectangle2D.Float(boundsY.x, boundsY.y + velocity.y,boundsY.width,boundsY.height);
        for(Entity entity : LevelScene.getEntityManager().getEntitiesWithComponent(Bounds.class)){
            if(entity.equals(this.entity))
                continue;
            
            if(predictedBoundsX.intersects(entity.getComponent(Bounds.class).boundsX) || predictedBoundsY.intersects(entity.getComponent(Bounds.class).boundsY)){
                res.add(new Collision(this.entity, entity)); 
            }
        }
        return res;
    }

    public Rectangle2D.Float getBoundsX(float x, float y, float w, float h){
        float bx = x;
        float by = y + 2;
        float bw = w;
        float bh = h - 4;

        return new Rectangle2D.Float(bx, by, bw, bh);
    }

    protected Rectangle2D.Float getBoundsY(float x, float y, float w, float h){
        float bx = x + 2;
        float by = y;
        float bw = w- 4;
        float bh = h;

        return new Rectangle2D.Float(bx, by, bw, bh);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setBound(float x, float y, float w, float h){
        entity.getTransform().position.x = x;
        entity.getTransform().position.y = y;
        entity.getTransform().scale.x = w;
        entity.getTransform().scale.y = h;
    

    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(DEBUG){
            g2d.setColor(color);
            if(true)
                g2d.fillRect(
                    (int)entity.getTransform().position.x, 
                    (int)entity.getTransform().position.y, 
                    (int)entity.getTransform().scale.x, 
                    (int)entity.getTransform().scale.y);
        }   

            
    }
    
}
