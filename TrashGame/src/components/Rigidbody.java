package components;

import physics.Collision;
import static physics.CollisionType.*;

import util.Vec2;
import static util.Constants.Game.*;

import java.util.ArrayList;
import java.util.List;

import dataStructure.Transform;

public class Rigidbody extends Component {
    public Vec2 velocity;
    public int directionX;
    private List<Vec2> forces = new ArrayList<>();
    private final float friction;

    public Rigidbody(float friction){
        this.friction = friction * SCALE;
        this.velocity = new Vec2(0,0);
    }

    @Override
    public void update() {

        boolean isCollidingY = false;
        boolean isCollidingX = false;
        directionX = Integer.signum((int)velocity.x);
        if(entity.hasComponent(Bounds.class)){
            for(Collision cObject : entity.getComponent(Bounds.class).checkCollision(velocity)){
                if(cObject.object.equals(this.entity))
                    continue;

                Transform s = entity.getTransform();
                Transform o = cObject.object.getTransform();


                if(cObject.type == LEFT && velocity.x<0){
                    velocity.x = 0;
                    s.position.x = o.position.x + o.scale.x;
                    isCollidingX = true;
                    // frictionOnY();
                }
                else if(cObject.type == RIGHT && velocity.x>0){
                        velocity.x = 0;
                    s.position.x = o.position.x - s.scale.x;
                    isCollidingX = true;
                    // frictionOnY();
                }

                if(cObject.type == TOP && velocity.y<0){
                    velocity.y = 0;
                    s.position.y = o.position.y + o.scale.y;
                    isCollidingY = true;
                    frictionOnX();
                }
                else if(cObject.type == BOTTOM && velocity.y>0){

                    velocity.y = 0;
                    s.position.y = o.position.y - s.scale.y;
                    isCollidingY = true;
                    frictionOnX();
                }

                //If it crashs, what will happenn?
                for(Component c : entity.getAllComponents())
                    c.onCollision(cObject);
            }
        }
        
        // avoid velocity --> 0.00...
        if(Math.abs(velocity.x) < 0.1)
            velocity.x = 0;
        if(Math.abs(velocity.y)< 0.1)
            velocity.y = 0;

        if(!forces.isEmpty())
            // net force
            for(Vec2 v : forces){
                velocity.x += v.x;
                velocity.y += v.y;
            }
        forces.clear();  

        //if not colliding (can move)
        if(!isCollidingX)
            entity.getTransform().position.x += velocity.x;
        if(!isCollidingY)
            entity.getTransform().position.y += velocity.y;


        
    }

    @Override
    public void onCollision(Collision collision) {
        if(collision.object.getName().equals("Box")){
            if(collision.type == LEFT || collision.type == RIGHT){
                System.out.println(directionX);
                collision.object.getComponent(Rigidbody.class).forces.add(new Vec2(directionX*0.5f, 0));
            }
        }
    }

    public void addForce(Vec2 force){
        velocity.add(force);
    }

    private void frictionOnX(){
        velocity.x -= Math.signum(velocity.x) * Math.min(Math.abs(velocity.x), friction);
        System.out.println(velocity.x + " friction : " + friction);
    }
    // private void frictionOnY(){

    //     velocity.y -= Math.signum(velocity.y) * Math.min(Math.abs(velocity.y), friction);
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
