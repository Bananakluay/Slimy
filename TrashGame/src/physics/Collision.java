package physics;

import dataStructure.Transform;
import entity.Entity;
import static physics.CollisionType.*;
public class Collision {
    public final CollisionType type;
    public final Entity subject;
    public final Entity object;

    public Collision(Entity subject, Entity object){
        this.subject = subject;
        this.object = object;
        this.type = getCollisionType(subject.getTransform(), object.getTransform());
    }

    public CollisionType getCollisionType(Transform s, Transform o){
        float s_left = s.position.x;
        float s_right = s_left + s.scale.x;
        float s_top = s.position.y;
        float s_bot = s_top + s.scale.y;

        float o_left = o.position.x;
        float o_right = o_left + o.scale.x;
        float o_top = o.position.y;
        float o_bot =  o_top + o.scale.y;
        
        float s_CenterX = s.position.x + s.scale.x/2;
        float s_CenterY = s.position.y + s.scale.y/2;
        float o_CenterX = o.position.x + o.scale.x/2;
        float o_CenterY = o.position.y + o.scale.y/2;


        //Q1 (Q4 in normal math graph) of subject
        if(s_CenterX < o_CenterX && s_CenterY < o_CenterY){
            if(s_right - o_left < s_bot - o_top) //horizontal overlap < vertical overlap
                return RIGHT;// s --> o
            else
                return BOTTOM;
        }
        //Q2 (Q3 in normal graph) of subject
        else if(s_CenterX > o_CenterX && s_CenterY < o_CenterY){
            if(o_right - s_left < s_bot - o_top) //horizontal overlap < vertical overlap
                return LEFT; //o <-- s
            else    
                return BOTTOM;
        }
        //Q3 (Q4 in normal graph) of subject
        else if(s_CenterX > o_CenterX && s_CenterY > o_CenterY){
            if(o_right - s_left < o_bot - s_top) //horizontal overlap < vertical overlap
                return LEFT;
            else
                return TOP;
        }
        //Q4 (Q1 in normal graph) of subject
        else if(s_CenterX < o_CenterX && s_CenterY > o_CenterY){
            if(s_right - o_left < o_bot - s_top)
                return RIGHT;
            else 
                return TOP;
        }
        else
            return NONE;

    }
    
}
