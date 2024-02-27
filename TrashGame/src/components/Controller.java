package components;

import main.Game;
import physics.Collision;
import prefabs.player.Player;
import sound.Sound;
import sound.Sound.Volume;
import utils.Vec2;

import java.awt.event.KeyEvent;

import static prefabs.player.AnimationStatus.*;
import static utils.Constants.Player.*;
import prefabs.player.Player;

@SuppressWarnings("unused")
public class Controller extends Component {
    private Player player;
    private Rigidbody rigidbody;

    private float walkSpeed = WALK_SPEED;
    private float jumpForce = JUMP_FORCE;

    public boolean isActive = true;

    private final int JUMP_DURATION = 25;
    private int jumpDurationCounter = 0;

    @Override
    public void ready() {
        if (entity.hasComponent(Rigidbody.class))
            rigidbody = entity.getComponent(Rigidbody.class);
        else
            System.out.println(
                    "WARNING FROM CLASS: " + this.getClass() + " | " + entity.getName() + "needs a Rigidbody.");

        if (entity instanceof Player) {
            player = (Player) entity;
        }
    }

    @Override
    public void update() {
        if (!isActive)
            return;
        // Walk
        if (Game.KI.isHeld(KeyEvent.VK_A)) {
            rigidbody.moveX(-walkSpeed);
            player.setAnimation(MOVING);
            player.setDirectionX(-1);

        }

        else if (Game.KI.isHeld(KeyEvent.VK_D)) {
            rigidbody.moveX(walkSpeed);
            player.setAnimation(MOVING);
            player.setDirectionX(1);

        } else {
            rigidbody.moveX(0);
            if (player.isAlive())
                player.setAnimation(IDLE);

        }

        // Jump
        if (isOnFloor()) {
            jumpDurationCounter = JUMP_DURATION;
        }
        if (!isOnFloor()) {
            if (rigidbody.velocity.y < 0) {
                player.setAnimation(JUMPING);
                player.setDirectionY(-1);
            } else if (rigidbody.velocity.y > 0) {
                player.setAnimation(FALLING);
                player.setDirectionY(1);
            }
        }
        if (isOnFloor() && Game.KI.isHeld(KeyEvent.VK_W)) {
            rigidbody.addForce(new Vec2(0, -jumpForce));
            jumpDurationCounter = JUMP_DURATION;
            if (rigidbody.velocity.y < 0) {
                Sound.volume = Volume.LOW;
                Sound.JUMP.play(false);
            }
        }

        // Jump duration
        if (Game.KI.isHeld(KeyEvent.VK_W)) {
            if (jumpDurationCounter > 0) {
                rigidbody.addForce(new Vec2(0, -jumpForce));
                jumpDurationCounter--;
            }
        }

    }

    public boolean isOnFloor() {
        return rigidbody.botCollision;
    }

    public void setActive(boolean status) {
        this.isActive = status;
    }

    public void setMobility(float walkSpeed, float jumpForce) {
        this.walkSpeed = walkSpeed;
        this.jumpForce = jumpForce;
    }

    public boolean isActive() {
        return isActive;
    }
}
