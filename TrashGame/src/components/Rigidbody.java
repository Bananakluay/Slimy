package components;

import physics.Collision;
import utils.Vec2;

import static entity.EntityType.PLAYER;
import static physics.CollisionType.*;

import java.util.ArrayList;
import java.util.List;

import dataStructure.Transform;

public class Rigidbody extends Component {

    public  float mass;
    public  float friction;
    public Vec2 velocity;

    private List<Vec2> forces = new ArrayList<>();

    public boolean leftCollision, rightCollision, topCollision, botCollision;

    public Rigidbody(float mass ,float friction){
        this.mass = mass;
        this.friction = friction;
        this.velocity = new Vec2(0,0);
    }

    @Override
    public void update() {

        boolean isCollidingY = false;
        boolean isCollidingX = false;

        leftCollision = false;
        rightCollision = false;
        topCollision = false;
        botCollision = false;

        if(entity.hasComponent(Bounds.class)){            
            for(Collision collision : entity.getComponent(Bounds.class).checkCollision(velocity)){
                if(collision.object.equals(this.entity))
                    continue;

                Transform s = entity.getTransform();
                Transform o = collision.object.getTransform();
                
                for(Component c : this.entity.getAllComponents())
                    c.onCollision(collision);

                handleCollision(collision, s, o);

                if(collision.type == LEFT){
                    isCollidingX = true;
                    leftCollision = true;
                }else if (collision.type == RIGHT){
                    isCollidingX = true;
                    rightCollision = true;
                }else if (collision.type == TOP){
                    isCollidingY = true;
                    topCollision = true;
                }else if (collision.type == BOTTOM){
                    isCollidingY = true;
                    botCollision = true;
                }
                
            }
        }

        
        //avoid velocity --> 0.00...
        // if(Math.abs(velocity.x) < 0.025*SCALE)
        //     velocity.x = 0;
        // if(Math.abs(velocity.y)< 0.025*SCALE)
        //     velocity.y = 0;

        frictionOnX();
        //net force
        if(!forces.isEmpty()){
            if(this.entity.getType() == PLAYER){
            }
            for(Vec2 v : forces){
                velocity.x += v.x;
                velocity.y += v.y;
            }
            forces.clear();  
        }
        // velocity.x = MiniMath.clamp(velocity.x, -1.2f, 1.2f);
        
        // if not colliding (can move)
        if(!isCollidingX){
            entity.getTransform().position.x += velocity.x;            
        }
        if(!isCollidingY){
            entity.getTransform().position.y += velocity.y;
        }

        
    }

    @Override
    public void onCollision(Collision collision) {
        
        if(collision.subject.hasComponent(Rigidbody.class) && collision.object.hasComponent(Rigidbody.class)){
            Rigidbody s = collision.subject.getComponent(Rigidbody.class);
            Rigidbody o = collision.object.getComponent(Rigidbody.class);
            if(collision.type == LEFT || collision.type == RIGHT){      
                s.addForce(new Vec2(-velocity.x/o.mass, 0));
                o.addForce(new Vec2(velocity.x/o.mass, 0));
                
            }
    
            if(collision.type == TOP || collision.type == BOTTOM){
                s.addForce(new Vec2(0, -velocity.y*0.5f/o.mass));
                o.addForce(new Vec2(0, velocity.y*0.5f/o.mass));
                if(collision.type == BOTTOM){
                    s.addForce(new Vec2(o.velocity.x*0.2f/s.mass, 0));
                }
            }
        }
  
    }

    private void handleCollision(Collision collision, Transform s, Transform o){
        if (collision.type == LEFT || collision.type == RIGHT) {
            velocity.x = 0;
            s.position.x = collision.type == LEFT ? o.position.x + o.scale.x : o.position.x - s.scale.x;
        } else if (collision.type == TOP) {
            velocity.y = 0;
            s.position.y = o.position.y + o.scale.y;
        } else if (collision.type == BOTTOM) {
            velocity.y = 0;
            s.position.y = o.position.y - s.scale.y;
        }
    }
    public void addForce(Vec2 force){
        forces.add(force);
    }

    private void frictionOnX(){
        velocity.x -= Math.signum(velocity.x) * Math.min(Math.abs(velocity.x), friction);
    }

    public void moveX(float x){
        this.velocity.x = x;
    }
    public void moveY(float y){
        this.velocity.y = y;
    }

    @Override
    public String toString() {
        return this.entity.getName() + "| velX: " + velocity.x + " | " + velocity.y;
    }


}
