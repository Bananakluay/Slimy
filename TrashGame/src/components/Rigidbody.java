package components;

import physics.Collision;
import prefabs.objects.Box;
import prefabs.player.Player;
import prefabs.player.character.TinySlime;
import utils.MiniMath;
import utils.Vec2;

import static physics.CollisionType.*;
import static utils.Constants.Game.SCALE;

import java.util.ArrayList;
import java.util.List;

import dataStructure.Transform;

public class Rigidbody extends Component {

    public float mass;
    public float friction;
    public Vec2 velocity;

    private List<Vec2> forces = new ArrayList<>();

    public boolean leftCollision, rightCollision, topCollision, botCollision;
    public float extremumSpeed = 0.34f * SCALE;
    
    public Rigidbody(float mass, float friction) {
        this.mass = mass;
        this.friction = friction;
        this.velocity = new Vec2(0, 0);

    }

    @Override
    public void update() {

        boolean isCollidingY = false;
        boolean isCollidingX = false;

        leftCollision = false;
        rightCollision = false;
        topCollision = false;
        botCollision = false;

        if (entity.hasComponent(Bounds.class)) {
            for (Collision collision : entity.getComponent(Bounds.class).checkCollision(velocity)) {
                if (collision.object.equals(this.entity))
                    continue;

                Transform s = entity.getTransform();
                Transform o = collision.object.getTransform();

                for (Component c : this.entity.getAllComponents())
                    c.onCollision(collision);

                handleCollision(collision, s, o);

                if (collision.type == LEFT) {
                    isCollidingX = true;
                    leftCollision = true;
                } else if (collision.type == RIGHT) {
                    isCollidingX = true;
                    rightCollision = true;
                } else if (collision.type == TOP) {
                    isCollidingY = true;
                    topCollision = true;
                } else if (collision.type == BOTTOM) {
                    isCollidingY = true;
                    botCollision = true;
                }

            }
        }

        // avoid velocity --> 0.00...
        if (Math.abs(velocity.x) < 0.001)
            velocity.x = 0;
        if (Math.abs(velocity.y) < 0.001)
            velocity.y = 0;

        if (botCollision || topCollision) {
            frictionOnX();
        }
        // System.out.println(velocity.x + " | " + velocity.y);
        // net force
        if (!forces.isEmpty()) {
            for (Vec2 v : forces) {
                velocity.x += v.x;
                velocity.y += v.y;

            }
            forces.clear();
        }

        velocity.x = MiniMath.clamp(velocity.x, -this.extremumSpeed, this.extremumSpeed);
        // if not colliding (can move)
        if (!isCollidingX) {
            entity.getTransform().position.x += velocity.x;
        }
        if (!isCollidingY) {
            entity.getTransform().position.y += velocity.y;
        }

    }

    @Override
    public void onCollision(Collision collision) {

        if (collision.subject.hasComponent(Rigidbody.class) && collision.object.hasComponent(Rigidbody.class)) {

            if (collision.subject instanceof Player p && collision.object instanceof Box) {
                if (p instanceof TinySlime) {
                    return;
                }
            }
            Rigidbody s = collision.subject.getComponent(Rigidbody.class);
            Rigidbody o = collision.object.getComponent(Rigidbody.class);
            if (collision.type == LEFT || collision.type == RIGHT) {
                s.addForce(new Vec2(-velocity.x / o.mass, 0));
                o.addForce(new Vec2(velocity.x / o.mass, 0));

            }

            if (collision.type == TOP || collision.type == BOTTOM) {
                s.addForce(new Vec2(0, -velocity.y / o.mass));
                o.addForce(new Vec2(0, velocity.y / o.mass));
                if (collision.type == BOTTOM) {
                    s.addForce(new Vec2(o.velocity.x * 0.2f / s.mass, 0));
                }
            }
        }

    }

    private void handleCollision(Collision collision, Transform s, Transform o) {
        if (collision.type == LEFT || collision.type == RIGHT) {
            velocity.x = 0;
            s.position.x = collision.type == LEFT ? o.position.x + o.scale.x + 0.0001f
                    : o.position.x - s.scale.x - 0.0001f;
        } else if (collision.type == TOP) {
            velocity.y = 0;
            s.position.y = o.position.y + o.scale.y;
        } else if (collision.type == BOTTOM) {
            velocity.y = 0;
            s.position.y = o.position.y - s.scale.y;
        }
    }

    public void addForce(Vec2 force) {
        forces.add(force);
    }

    private void frictionOnX() {
        this.addForce(new Vec2(-velocity.x * friction, 0));
    }

    public void moveX(float x) {
        this.velocity.x = x;
    }

    public void moveY(float y) {
        this.velocity.y = y;
    }

    public void setExtrmumXSpeed(float speed) {
        this.extremumSpeed = speed;
    }

    @Override
    public String toString() {
        return this.entity.getName() + "| velX: " + velocity.x + " | " + velocity.y;
    }

}
