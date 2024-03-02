package components;

import static utils.Constants.Physics.*;

import utils.Vec2;
public class Physic2D extends Component {

    private Rigidbody rb;
    
    @Override
    public void ready() {
        if(entity.hasComponent(Rigidbody.class))
            rb = entity.getComponent(Rigidbody.class);
        else
            System.out.println("WARNING FROM CLASS: " +this.getClass()+ " | "+entity.getName() + "needs a Rigidbody.");
    }

    @Override
    public void update() {
        if(rb != null)
            fall();
        else
            return;
    }

    private void fall(){
        rb.addForce(new Vec2(0, GRAVITY));
        if(rb.velocity.y > DOWN_FORCE)
            rb.velocity.y = DOWN_FORCE;
    }

    public void showVelocity(String entityName){
        if(entity.getName().equals(entityName))
            System.out.println("velX: " + rb.velocity.x +" velY: " + rb.velocity.y);
    }
}
