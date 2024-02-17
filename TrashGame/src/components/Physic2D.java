package components;

import util.Vec2;

import static util.Constants.DOWN_FORCE;
import static util.Constants.GRAVITY;
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
        if(velocity != null){
            fall();
        }
            
        else
            return;
    }

    private void fall(){
        velocity.y += GRAVITY;
        if(velocity.y > DOWN_FORCE)
            velocity.y = DOWN_FORCE;
    }
}
