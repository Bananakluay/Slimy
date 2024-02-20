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

    private float walkSpeed = WALK_SPEED;
    private float jumpForce = JUMP_FORCE;
    public boolean isCurrentPlayer = false;
    
    public Controller(boolean status){
    
        isCurrentPlayer = status;
    }

    @Override
    public void ready() {
        if(entity.hasComponent(Rigidbody.class))
            rigidbody = entity.getComponent(Rigidbody.class);
        else
            System.out.println("WARNING FROM CLASS: " +this.getClass()+ " | "+entity.getName() + "needs a Rigidbody.");
    }

    @Override
    public void update() {
        if(!isCurrentPlayer)
            return;

        walk();
        jump();
    }

    private void walk(){
        if(Game.KI.isKeyPressed(KeyEvent.VK_A))
            rigidbody.moveX(-walkSpeed );
        else if(Game.KI.isKeyPressed(KeyEvent.VK_D))
            rigidbody.moveX(walkSpeed);
        else
            rigidbody.moveX(0);

        // if(!Game.KI.isKeyPressed(KeyEvent.VK_A))
        //     rigidbody.addForce(new Vec2(-0.1f, 0));
        // if(!Game.KI.isKeyPressed(KeyEvent.VK_D))
        //     rigidbody.addForce(new Vec2(0.1f, 0));


    }
    private void jump(){
        if(Game.KI.isKeyPressed(KeyEvent.VK_W) && jumpCount < maxJumpCount){
            rigidbody.addForce(new Vec2(0, -jumpForce));
            jumpCount++;
        }
    }

    @Override
    public void onCollision(Collision collision) {
        if(collision.type == BOTTOM){
            jumpCount = 0;

        }
    }

    public void setCurrentPlayer(boolean isCurrentPlayer) {
        this.isCurrentPlayer = isCurrentPlayer;
    }

    public void setMobility(float walkSpeed, float jumpForce){
        this.walkSpeed = walkSpeed;
        this.jumpForce = jumpForce;
    }

    



    
}
