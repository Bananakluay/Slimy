package components;

import util.Vec2;

import static util.Constants.physics.*;
public class Physic2D extends Component {

    private Vec2 velocity;
    
    @Override
    public void ready() {
        if(entity.hasComponent(Rigidbody.class))
            velocity = entity.getComponent(Rigidbody.class).velocity;
        else
            System.out.println("WARNING FROM CLASS: " +this.getClass()+ " | "+entity.getName() + "needs a Rigidbody.");
    }

    @Override
    public void update() {
        if(velocity != null)
            fall();
        else
            return;
        // showVelocity("Box");
    }

    private void fall(){
        velocity.y += GRAVITY;
        if(velocity.y > DOWN_FORCE)
            velocity.y = DOWN_FORCE;
    }

    public void showVelocity(String entityName){
        if(entity.getName().equals(entityName))
            System.out.println("velX: " + velocity.x +" velY: " + velocity.y);
    }
}
