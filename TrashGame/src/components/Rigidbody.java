package components;

import physics.Collision;
import static physics.CollisionType.*;

import util.Vec2;
import util.MiniMath;

import java.util.ArrayList;
import java.util.List;

import dataStructure.Transform;

public class Rigidbody extends Component {

    public Vec2 velocity;
    public float mass;
    public int directionX, directionY;
    private List<Vec2> forces = new ArrayList<>();
    private final float friction;

    public Rigidbody(float friction){
        this.friction = friction;
        this.velocity = new Vec2(0,0);
    }

    @Override
    public void update() {

        boolean isCollidingY = false;
        boolean isCollidingX = false;
        directionX = Integer.signum((int)velocity.x);
        directionY = Integer.signum((int)velocity.y);
        if(entity.hasComponent(Bounds.class)){            
            for(Collision collision : entity.getComponent(Bounds.class).checkCollision(velocity)){
                if(collision.object.equals(this.entity))
                    continue;
                if(entity.getName().equals("Green")&& collision.type != BOTTOM){
                    System.out.println(collision.subject.getName()+" collision type:  "+collision.type);
                }

              
                Transform s = entity.getTransform();
                Transform o = collision.object.getTransform();
                
                //If it crashs, what will happenn?
                for(Component c : this.entity.getAllComponents())
                    c.onCollision(collision);

                if(collision.type == LEFT){
                    velocity.x = 0;
                    s.position.x = o.position.x + o.scale.x;
                    isCollidingX = true;
                    // frictionOnY();
                }
                else if(collision.type == RIGHT){
                    System.out.println("here");
                    velocity.x = 0;
                    s.position.x = o.position.x - s.scale.x;
                    isCollidingX = true;
                    // frictionOnY();
                }


                if(collision.type == TOP){
                    velocity.y = 0;
                    s.position.y = o.position.y + o.scale.y;
                    isCollidingY = true;  
                    frictionOnX();

                }
                else if(collision.type == BOTTOM){

                    velocity.y = 0;
                    s.position.y = o.position.y - s.scale.y;
                    isCollidingY = true;
                    frictionOnX();
                }
                
            }
        }

        
        // avoid velocity --> 0.00...
        if(Math.abs(velocity.x) < 0.1)
            velocity.x = 0;
        if(Math.abs(velocity.y)< 0.1)
            velocity.y = 0;
        // frictionOnX();
        //net force
        if(!forces.isEmpty()){
            for(Vec2 v : forces){
                velocity.x += v.x;
                velocity.y += v.y;
            }
        }
        forces.clear();  
            
        velocity.x = MiniMath.clamp(velocity.x, -3, 3);

        // System.out.println("isCollidingX: "+isCollidingX+"isColldingY: " + isCollidingY);
        // if not colliding (can move)
        if(!isCollidingX){
            entity.getTransform().position.x += velocity.x;            
        }
        // else{
        //     entity.getTransform().position.x -= velocity.x;   
        // }
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
                s.addForce(new Vec2(-velocity.x*0.12f, 0));
                o.addForce(new Vec2(velocity.x*0.12f, 0));
            }
    
            if(collision.type == TOP || collision.type == BOTTOM){
                s.addForce(new Vec2(0, -velocity.y*0.12f));
                o.addForce(new Vec2(0, velocity.y*0.12f));
                if(collision.type == BOTTOM){
                    s.addForce(new Vec2(o.velocity.x*0.05f, 0));
                }
            }
        }
  
        // if(!collision.object.getName().equals("player") && collision.type == BOTTOM){

        //     entity.getComponent(Rigidbody.class).addForce(new Vec2(collision.object.getComponent(Rigidbody.class).velocity.x*0.35f, 0f));
        // }


    }

    public void addForce(Vec2 force){
        forces.add(force);
    }

    private void frictionOnX(){
        velocity.x -= Math.signum(velocity.x) * Math.min(Math.abs(velocity.x), friction);
        // System.out.println(velocity.x + " friction : " + friction);
    }
    private void frictionOnY() {
        velocity.y -= Math.signum(velocity.y) * Math.min(Math.abs(velocity.y), friction);
        // System.out.println(velocity.y + " friction : " + friction);
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
