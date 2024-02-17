package components;

import main.Game;
import physics.Collision;
import util.Vec2;

import java.awt.event.KeyEvent;

import static physics.CollisionType.BOTTOM;
import static util.Constants.Player.*;
public class Controller extends Component{
    private Rigidbody rigidbody;
    private int jumpCount = 0;
    private int maxJumpCount = 1;
    @Override
    public void ready() {
        if(entity.hasComponent(Rigidbody.class))
            rigidbody = entity.getComponent(Rigidbody.class);
        else
            System.out.println("WARNING FROM CLASS: " +this.getClass()+ " | "+entity.getName() + "needs a Rigidbody.");
    }

    @Override
    public void update() {
        walk();
        jump();
    }

    private void walk(){
        if(Game.KI.isKeyPressed(KeyEvent.VK_A))
            rigidbody.moveX(-WALK_SPEED);
  
        else if(Game.KI.isKeyPressed(KeyEvent.VK_D))
            rigidbody.moveX(WALK_SPEED);
        else
            rigidbody.moveX(0);


    }
    private void jump(){
        if(Game.KI.isKeyPressed(KeyEvent.VK_W)){
            if(jumpCount < maxJumpCount){
                rigidbody.addForce(new Vec2(0, -JUMP_FORCE));
                jumpCount++;
            }
        }
    }

    @Override
    public void onCollision(Collision collision) {
        if(collision.type == BOTTOM)
            jumpCount = 0;
        //invoke onCollision that attached to object that player collided with
        for(Component c : collision.object.getAllComponents()){
            if(!c.equals(this))
                c.onCollision(collision);
        }
    }

    
}
