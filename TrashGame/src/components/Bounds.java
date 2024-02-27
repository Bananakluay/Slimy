package components;

import static entity.EntityType.PLAYER;
import static utils.Constants.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Interaction.Listener;
import Scene.LevelScene;
import entity.Entity;
import entity.EntityType;
import physics.Collision;
import utils.Vec2;

public class Bounds extends Component{
    public Color color;

    public Rectangle2D.Float boundsX = new Rectangle2D.Float();
    public Rectangle2D.Float boundsY = new Rectangle2D.Float();

    public Rectangle2D.Float interectBounds = new Rectangle2D.Float();
    public float interectBoundsoffset = 10*SCALE;
    private boolean DEBUG = false;
    private List<EntityType> ignoreET;
    public Bounds(Color color, List<EntityType> ignoreET){
        this.color = color != null ? color : Color.WHITE;
        this.ignoreET = ignoreET != null ? ignoreET : null;
    }

    @Override
    public void update() {
        
        if(entity.getType() == PLAYER){
            interectBounds.setRect(
                entity.getPosition().x - interectBoundsoffset/2f, 
                entity.getPosition().y - interectBoundsoffset/2f, 
                entity.getScale().x + interectBoundsoffset, 
                entity.getScale().y + interectBoundsoffset
            );
        }

        boundsX = getBoundsX(entity.getPosition().x, entity.getPosition().y, entity.getScale().x, entity.getScale().y);
        boundsY = getBoundsY(entity.getPosition().x, entity.getPosition().y, entity.getScale().x, entity.getScale().y);
    }

    public List<Collision> checkCollision(Vec2 velocity){
        List<Collision> results = new ArrayList<>();

        Rectangle2D.Float predictedBoundsX = new Rectangle2D.Float(boundsX.x+velocity.x, boundsX.y,boundsX.width,boundsX.height);
        Rectangle2D.Float predictedBoundsY = new Rectangle2D.Float(boundsY.x, boundsY.y + velocity.y,boundsY.width,boundsY.height);

        for(Entity entity : LevelScene.getEntityManager().getEntitiesWithComponent(Bounds.class)){
            if(entity.equals(this.entity))
                continue;
            
            if(predictedBoundsX.intersects(entity.getComponent(Bounds.class).boundsX) || predictedBoundsY.intersects(entity.getComponent(Bounds.class).boundsY)){
                results.add(new Collision(this.entity, entity)); 
            }
            if(ignoreET != null && ignoreET.contains(entity.getType()))
                continue;
        }
        return results;
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
        float bw = w - 4;
        float bh = h;

        return new Rectangle2D.Float(bx, by, bw, bh);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setBound(float x, float y, float width, float height){
        entity.getTransform().position.x = x;
        entity.getTransform().position.y = y;
        entity.getTransform().scale.x = width;
        entity.getTransform().scale.y = height;
    

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


    public void addListener(){};

    
}
