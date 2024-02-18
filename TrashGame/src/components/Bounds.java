package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Scene.SceneManager;
import dataStructure.Transform;
import entity.Entity;
import physics.Collision;
import util.Vec2;

public class Bounds extends Component {

    public Rectangle2D.Float bounds = new Rectangle2D.Float();
    private boolean DEBUG = true;
    @Override
    public void update() {
        Transform t = entity.getTransform();
        bounds.setRect(t.position.x, t.position.y, t.scale.x, t.scale.y);
    }

    public List<Collision> checkCollision(Vec2 velocity){
        List<Collision> res = new ArrayList<>();

        Rectangle2D.Float predictedBounds = new Rectangle2D.Float(bounds.x+velocity.x, bounds.y+velocity.y,bounds.width,bounds.height);

        for(Entity entity : SceneManager.getCurrentScene().getEntitiesWithComponent(Bounds.class)){
            if(entity.equals(this.entity))
                continue;
            if(predictedBounds.intersects(entity.getComponent(Bounds.class).bounds)){
                // if(this.entity.getName().equals("player"))
                //     System.out.println("intersects with" + entity.getName() );
                res.add(new Collision(this.entity, entity));    
            }
        }
        return res;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(DEBUG)
            g2d.draw(bounds);
            
    }
    
}
