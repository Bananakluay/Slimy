package components;

import physics.Collision;
import utils.Vec2;

import static physics.CollisionType.*;
import static utils.Constants.Game.SCALE;

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

                // if(collision.subject.type == PLAYER && collision.object.type == PLAYER)
                //     continue;
                Transform s = entity.getTransform();
                Transform o = collision.object.getTransform();
                
                //If it crashs, what will happenn?
                for(Component c : this.entity.getAllComponents())
                    c.onCollision(collision);

                if(collision.type == LEFT){
                    velocity.x = 0;
                    s.position.x = o.position.x + o.scale.x;
                    isCollidingX = true;
                    leftCollision = true;
                    // frictionOnY();
                }
                else if(collision.type == RIGHT){
                    velocity.x = 0;
                    s.position.x = o.position.x - s.scale.x;
                    isCollidingX = true;
                    rightCollision = true;
                    // frictionOnY();
                }


                if(collision.type == TOP){
                    velocity.y = 0;
                    s.position.y = o.position.y + o.scale.y;
                    isCollidingY = true;  
                    topCollision = true;
                }
                else if(collision.type == BOTTOM){
                    velocity.y = 0;
                    s.position.y = o.position.y - s.scale.y;
                    isCollidingY = true;
                    botCollision = true;
                }
                
            }
        }

        
        //avoid velocity --> 0.00...
        if(Math.abs(velocity.x) < 0.025*SCALE)
            velocity.x = 0;
        if(Math.abs(velocity.y)< 0.025*SCALE)
            velocity.y = 0;
        frictionOnX();
        //net force
        if(!forces.isEmpty()){
            for(Vec2 v : forces){
                velocity.x += v.x;
                velocity.y += v.y;
            }
        }
        forces.clear();  
            
        // velocity.x = MiniMath.clamp(velocity.x, -3, 3);

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

    public void addForce(Vec2 force){
        forces.add(force);
    }

    private void frictionOnX(){
        velocity.x -= Math.signum(velocity.x) * Math.min(Math.abs(velocity.x), friction);
    }
    // private void frictionOnY() {
    //     velocity.y -= Math.signum(velocity.y) * Math.min(Math.abs(velocity.y), friction);
    //     // System.out.println(velocity.y + " friction : " + friction);
    // }

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
