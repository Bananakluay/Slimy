package components;

import static utils.Constants.Physics.*;

import utils.Vec2;
public class Physic2D extends Component {

    private Rigidbody rig;
    
    @Override
    public void ready() {
        if(entity.hasComponent(Rigidbody.class))
            rig = entity.getComponent(Rigidbody.class);
        else
            System.out.println("WARNING FROM CLASS: " +this.getClass()+ " | "+entity.getName() + "needs a Rigidbody.");
    }

    @Override
    public void update() {
        if(rig != null)
            fall();
        else
            return;
        // showVelocity("Green");
    }

    private void fall(){
        rig.addForce(new Vec2(0, GRAVITY));
        if(rig.velocity.y > DOWN_FORCE)
            rig.velocity.y = DOWN_FORCE;
    }

    public void showVelocity(String entityName){
        if(entity.getName().equals(entityName))
            System.out.println("velX: " + rig.velocity.x +" velY: " + rig.velocity.y);
    }
}
