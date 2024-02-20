package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Scene.SceneManager;
import dataStructure.Transform;
import entity.Entity;
import main.Game;
import physics.Collision;
import util.Vec2;

public class Bounds extends Component {
    public Color color;
    public Rectangle2D.Float bounds = new Rectangle2D.Float();
    public Rectangle2D.Float boundsX = new Rectangle2D.Float();
    public Rectangle2D.Float boundsY = new Rectangle2D.Float();
    public boolean isOverlap = false;
    private boolean DEBUG = true;

    public Bounds(Color color){
        if(color == null)
            this.color = Color.white;
        else
            this.color = color;
    }
    @Override
    public void update() {
        Transform t = entity.getTransform();
        bounds.setRect(t.position.x, t.position.y, t.scale.x, t.scale.y);
        boundsX = getBoundsX(t.position.x, t.position.y, t.scale.x, t.scale.y);
        boundsY = getBoundsY(t.position.x, t.position.y, t.scale.x, t.scale.y);
    }

    public List<Collision> checkCollision(Vec2 velocity){
        List<Collision> res = new ArrayList<>();

        isOverlap = false;
        Rectangle2D.Float predictedBounds = new Rectangle2D.Float(bounds.x+velocity.x, bounds.y+velocity.y,bounds.width,bounds.height);
        Rectangle2D.Float predictedBoundsX = new Rectangle2D.Float(boundsX.x+velocity.x, boundsX.y,boundsX.width,boundsX.height);
        Rectangle2D.Float predictedBoundsY = new Rectangle2D.Float(boundsY.x, boundsY.y + velocity.y,boundsY.width,boundsY.height);
        for(Entity entity : SceneManager.getCurrentScene().getEntitiesWithComponent(Bounds.class)){
            if(entity.equals(this.entity))
                continue;
            // if(predictedBounds.intersects(entity.getComponent(Bounds.class).bounds)){
            //     // if(this.entity.getName().equals("player"))
            //     //     System.out.println("intersects with" + entity.getName() );
            //     res.add(new Collision(this.entity, entity));    
            // }
            // if(predictedBoundsX.intersects(entity.getComponent(Bounds.class).boundsX)){
            //     res.add(new Collision(this.entity, entity)); 
            // }
            // if(predictedBoundsY.intersects(entity.getComponent(Bounds.class).boundsY)){
            //     res.add(new Collision(this.entity, entity));    
            // }

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
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(DEBUG){
            g2d.setColor(color);
            // g2d.draw(bounds);
            g2d.draw(boundsX);
            g2d.draw(boundsY);
        }

            
    }
    
}
