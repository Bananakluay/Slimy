package simplePhysics;

import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Player;
import main.Game;


public class RigidBody {
    protected float velX = 0, velY = 0;
    
    protected float mass;
    protected float COR = 1f * Game.SCALE;

    protected Area hitbox;
    public static ArrayList<Area> areas = new ArrayList<Area>();
    public static ArrayList<RigidBody> objs = new ArrayList<RigidBody>();

    public RigidBody(Area hitbox){
        this.hitbox = hitbox;
        mass = hitbox.width * hitbox.height;
    }
   
    public void update(){
        move();
        objectCollision();
        areasCollision();
        
    }
    
    protected void move(){
        hitbox.x += velX;
        hitbox.y += velY;
        velX *= Game.SCALE*0.025;
        velY *= Game.SCALE*0.025;
    }
    protected void areasCollision(){

        for(int i=0;i<areas.size();i++){
            Area thatArea = areas.get(i);

            boolean isOnLeft = hitbox.x < thatArea.x + thatArea.width/2;
            boolean isOnRight = hitbox.x > thatArea.x + thatArea.width/2;
            boolean isOnTop = hitbox.y < thatArea.y + thatArea.height/2;
            boolean isOnDown = hitbox.y > thatArea.y + thatArea.height/2;

            if(getBoundsX().intersects(thatArea)){
                if(velX<0 && isOnRight || velX<0 && hitbox.intersects(thatArea)){//go left and is on is on right of that area
                    velX = 0;
                    hitbox.x = thatArea.x + thatArea.width;
                }
                else if(velX>0 && isOnLeft|| velX>0 && hitbox.intersects(thatArea)){//go right and is on left of that area
                    velX = 0;
                    hitbox.x = thatArea.x - hitbox.width;
                }
            }
            
            if(getBoundsY().intersects(thatArea)){
                if(velY<0 && isOnDown || velY<0 && hitbox.intersects(thatArea)){ //go up and is on down of that area
                    velY = 0;
                    hitbox.y = thatArea.y + thatArea.height;
                }
                else if(velY>0 && isOnTop || velY>0 && hitbox.intersects(thatArea)){//go down and is on top of that area
                    velY = 0;
                    hitbox.y = thatArea.y - hitbox.height;
                }
            }       
        }
    }

    protected void objectCollision(){

 
        for(int i=0;i<objs.size();i++){
            if(objs.get(i).hashCode() == this.hashCode())
                continue;
            
            RigidBody obj = objs.get(i);

            boolean isOnLeft = hitbox.x < obj.hitbox.x + obj.hitbox.width/2;
            boolean isOnRight = hitbox.x > obj.hitbox.x + obj.hitbox.width/2;
            boolean isOnTop = hitbox.y < obj.hitbox.y + obj.hitbox.height/2;
            boolean isOnDown = hitbox.y > obj.hitbox.y + obj.hitbox.height/2;
            
            // System.out.println(String.format("isOnLeft:%b isOnRight:%b isOnTop:%b isOnDown:%b", isOnLeft,isOnRight,isOnTop,isOnDown));
            if(getBoundsX().intersects(obj.hitbox)){

                if(hitbox.intersects(obj.hitbox)){
                    if(this instanceof Player)
                        inElasticCollisionX(obj); 
                    else 
                        obj.velX = 0;  
                }

                if(velX<0 && isOnRight){//go left and is on right of Object

                    hitbox.x = obj.hitbox.x + obj.hitbox.width;
                }
                else if(velX>0 && isOnLeft){//go right and is on left of Object
                    hitbox.x = obj.hitbox.x - hitbox.width;
                }
                else if(velX == 0 && !isOnTop ||  velX == 0 && !isOnDown){// avoid bug
                    if(isOnLeft) hitbox.x = obj.hitbox.x - hitbox.width;
                    else if(isOnRight) hitbox.x = obj.hitbox.x + obj.hitbox.width ;
                } 
            }

            if(getBoundsY().intersects(obj.hitbox)){

                if(hitbox.intersects(obj.hitbox)){
                    if(this instanceof Player)
                        inElasticCollisionY(obj);
                    else
                        obj.velY = 0;          
                }
         
                if(velY<0 && isOnDown){// go up and is on down of Object
                    hitbox.y = obj.hitbox.y + obj.hitbox.height;
                }
                else if(velY>0 && isOnTop){// go down and is on top of Object
                    hitbox.y = obj.hitbox.y - hitbox.height;
                }
                else if(velY == 0 && !isOnRight || velY == 0 && !isOnLeft){// avoid bug
                    if(isOnTop) hitbox.y = obj.hitbox.y - hitbox.height;
                    else if(isOnDown) hitbox.y = obj.hitbox.y + obj.hitbox.height;
                } 
            }  
        }
    }

    protected class inElasticCollisionSystem {
        float combinedMass;
        float momentum;
        float newVelX;
        ArrayList<RigidBody> objects = new ArrayList<>();
        public void inElasticCollisionX(){
            
        }
    }
    protected void inElasticCollisionX(RigidBody obj){
        float combinedMass = mass + obj.mass;
        float newVelX = ((velX * mass) + (obj.velX * obj.mass) * COR) / combinedMass;
        obj.velX = newVelX;
        
    }

    protected void inElasticCollisionY(RigidBody obj){
        float combinedMass = mass + obj.mass;
        float newVelY = ((velY * mass) + (obj.velY * obj.mass) * COR) / combinedMass;
        obj.velY = newVelY;
        
    }
    
    public Rectangle getBoundsX(){
        float bx = hitbox.x + velX;
        float by = hitbox.y + 2*Game.SCALE;
        float bw = hitbox.width;
        float bh = hitbox.height - 4*Game.SCALE;

        return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
    }

    public Rectangle getBoundsY(){
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
}
