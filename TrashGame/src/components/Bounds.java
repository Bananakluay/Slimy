package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import Scene.SceneManager;
import dataStructure.Transform;
import entity.Entity;
import physics.Collision;
import util.Vec2;

public class Bounds extends Component {

    public Rectangle bounds = new Rectangle();
    private boolean DEBUG = true;
    @Override
    public void update() {
        // System.out.println(entity.getName() + entity.getTransform().scale.x);
        Transform t = entity.getTransform();
        bounds.setRect(t.position.x, t.position.y, t.scale.x, t.scale.y);
    }

    public List<Collision> checkCollision(Vec2 velocity){
        List<Collision> res = new ArrayList<>();

        Rectangle predictedBounds = new Rectangle((int)(bounds.x+velocity.x), (int)(bounds.y+velocity.y),bounds.width,bounds.height);

        for(Entity entity : SceneManager.getCurrentScene().getEntitiesWithComponent(Bounds.class)){
            if(entity.equals(this.entity))
                continue;
            if(predictedBounds.intersects(entity.getComponent(Bounds.class).bounds))
                res.add(new Collision(this.entity, entity));    
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
