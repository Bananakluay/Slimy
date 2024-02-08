package simplePhysic;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.Game;


public class RigidBody {
    protected float velX = 0, velY = 0;
    
    protected float mass = 1*Game.SCALE;
    protected float COR = 1 * Game.SCALE;

    protected float gravityForce = 0.2f* mass;
    protected boolean isColliding;

    protected Area hitbox;
    public static ArrayList<Area> areas = new ArrayList<Area>();
    public static ArrayList<RigidBody> objs = new ArrayList<RigidBody>();
    public RigidBody(Area hitbox){
        this.hitbox = hitbox;
    }
   
    public void update(){
        // System.out.println(isOnFloor());
        // if(!isOnFloor())
            // gravity();
        areasCollision();
    }
    protected void gravity(){
        velY += gravityForce;
    }
    protected void areasCollision(){
        for(int i=0;i<areas.size();i++){
            Area unKnowArea = areas.get(i);


            if(getBoundsX().intersects(unKnowArea)){
                if(velX>0){ //right
                    velX = 0;
                    hitbox.x = unKnowArea.x - hitbox.width;
                }
                else if(velX<0){//left
                    velX = 0;
                    hitbox.x = unKnowArea.x + unKnowArea.width + 1;
                }
            }
            
            if(getBoundsY().intersects(unKnowArea)){
                if(velY>0){// down
                    velY = 0;
                    hitbox.y = unKnowArea.y - hitbox.height;
                }
                else if(velY<0){ //up
                    velY = 0;
                    hitbox.y = unKnowArea.y + unKnowArea.height + 1;
                }
            }
        }
    }

 
    protected void objectCollision(){
        for(int i=0;i<objs.size();i++){
            if(objs.get(i).hashCode() == this.hashCode()){
                continue;
            }

            RigidBody obj = objs.get(i);
            
            
            if(getBoundsX().intersects(obj.getHitbox()) ){
                obj.velX = 0;      
                
                if(hitbox.intersects(obj.getHitbox())){
                    inElasticCollisionX(obj);
                }
                
                if(velX>0){ //right
                    
                    hitbox.x = obj.hitbox.x - hitbox.width;
                }
                else if(velX<0){//left
                    hitbox.x = obj.hitbox.x + obj.hitbox.width + 1;
                }
                else if(velX == 0){
                    if(hitbox.x < obj.hitbox.x + obj.hitbox.width/2) hitbox.x = obj.hitbox.x - hitbox.width;
                    else if(hitbox.x > obj.hitbox.x + obj.hitbox.width/2) hitbox.x = obj.hitbox.x + obj.hitbox.width ;
                } 
            }
            if(getBoundsY().intersects(obj.getHitbox())){
                obj.velY = 0;      
                
                if(hitbox.intersects(obj.getHitbox())){
                    inElasticCollisionY(obj);
                }
                if(velY>0){ 
                    hitbox.y = obj.hitbox.y - hitbox.height;
                }
                else if(velY<0){
                    hitbox.y = obj.hitbox.y + obj.hitbox.height + 1;
                }
                else if(velY == 0){
                    if(hitbox.y < obj.hitbox.y + obj.hitbox.height/2) hitbox.y = obj.hitbox.y - hitbox.height;
                    else if(hitbox.y > obj.hitbox.y + obj.hitbox.height/2) hitbox.y = obj.hitbox.y + obj.hitbox.height ;
                } 
            }

           
            

            
        }
    }

    protected void inElasticCollisionX(RigidBody obj){
        float combinedMass = mass + obj.getMass();
        float newVelX = ((velX * mass) + (obj.velX * obj.mass) * COR) / combinedMass;
        obj.velX = newVelX;
        
    }

    protected void inElasticCollisionY(RigidBody obj){
        float combinedMass = mass + obj.getMass();
        float newVelY = ((velY * mass) + (obj.velY * obj.getMass()) * COR) / combinedMass;
        obj.velY = newVelY;
        
    }
    
    protected Rectangle getBoundsX(){
        float bx = hitbox.x + velX;
        float by = hitbox.y + 2*Game.SCALE;
        float bw = hitbox.width;
        float bh = hitbox.height - 4*Game.SCALE;

        return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
    }

    protected Rectangle getBoundsY(){
        float bx = hitbox.x + 2*Game.SCALE;
        float by = hitbox.y + velY;
        float bw = hitbox.width - 4*Game.SCALE;
        float bh = hitbox.height;

        return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
    }

    protected float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    
    public static void setInterection(Area area){
        System.out.println("add area");
        areas.add(area);
    }
    public static void setInterection(RigidBody obj){
        objs.add(obj);
    }
    public static void setAreasInterection(ArrayList<Area> area){
        areas.addAll(area);
    }  
    public static void setObjectListInterection(ArrayList<RigidBody> obj){
        objs.addAll(obj);
    }


    public Area getHitbox() {
        return hitbox;
    }

    public float getMass(){
        return mass;
    }

    public void setVelX(float velX){
        this.velX = velX;
    }

   
}
