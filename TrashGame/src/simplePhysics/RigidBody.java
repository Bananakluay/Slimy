package simplePhysics;
import java.awt.Rectangle;
import java.util.ArrayList;


import main.Game;


public class RigidBody {
    
    protected float velX = 0, velY = 0;
    
    protected float mass;
    protected float COR = 0.25f * Game.SCALE;
    protected float gravity = 0.04f * Game.SCALE;
    protected float friction_box = 0.27f * Game.SCALE;
    protected Area hitbox;
    
    public static ArrayList<Area> areas = new ArrayList<Area>();
    
    public static ArrayList<RigidBody> objs = new ArrayList<RigidBody>();

    protected boolean isOnFloor = false;

    public RigidBody(Area hitbox){
        this.hitbox = hitbox;
        mass = hitbox.width * hitbox.height;

        areas.add(this.hitbox);

        objs.add(this);
    }
   
    public void update(){
        objectCollision();// เช็ตการชนของ obj
        areasCollision();//เช็คการชนของ กำแพง
        checkIsOnFloor();//อยู่พิ้น?
        move();
        
    }
    
    protected void move(){

        
        if(!isOnFloor)
            velY += gravity; //gravity
        
        hitbox.x += velX;
        hitbox.y += velY;

        //แรงเสียดทาน
        velX *= friction_box;
        if(velX>0 && velX<gravity)
            velX = 0;
        if(velX>-gravity && velX<0)
            velX = 0;
    }

    protected void checkIsOnFloor(){
        updateIsNotOnFloor();
        updateIsOnFloor();
    }

    public void updateIsOnFloor(){
        for(Area area : areas){
            if(this.hitbox != area && area.intersects(getFloorHitbox())){
                isOnFloor = true;
            }
           
        }
    }
    public void updateIsNotOnFloor(){
        for(Area area : areas){
            if(this.hitbox != area && !area.intersects(getFloorHitbox())){
                isOnFloor = false;
            }
        }
    }

    protected float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    protected void areasCollision(){

        for(int i=0;i<areas.size();i++){
            Area area = areas.get(i);

            boolean isOnLeft = hitbox.x + hitbox.width/2 < area.x + area.width/2;
            boolean isOnRight = hitbox.x + hitbox.width/2 > area.x + area.width/2;
            boolean isOnTop = hitbox.y + hitbox.height/2 < area.y + area.height/2;
            boolean isOnBot = hitbox.y + hitbox.height/2 > area.y + area.height/2;

            if(getBoundsX().intersects(area)){
                if(velX<0 && isOnRight){//go left and is on is on right of that area
                    velX = 0;
                    hitbox.x = area.x + area.width;
                }
                else if(velX>0 && isOnLeft){//go right and is on left of that area
                    velX = 0;
                    hitbox.x = area.x - hitbox.width;
                }
            }
            
            if(getBoundsY().intersects(area)){
                if(velY<0 && isOnBot){ //go up and is on down of that area
                    velY = 0;
                    hitbox.y = area.y + area.height;
                }
                else if(velY>0 && isOnTop){//go down and is on top of that area
                    velY = 0;
                    hitbox.y = area.y - hitbox.height;
                }
            }  
        }
    }

    protected void objectCollision(){

        for(int i=0;i<objs.size();i++){
            if(objs.get(i).hashCode() == this.hashCode())//ป้องกันกัน เป็นเช็คตัวเอง
                continue;
            
            RigidBody obj = objs.get(i);

            boolean isOnLeft = hitbox.x + hitbox.width/2 < obj.hitbox.x + obj.hitbox.width/2;
            boolean isOnRight = hitbox.x + hitbox.width/2 > obj.hitbox.x + obj.hitbox.width/2;

            if(getBoundsX().intersects(obj.hitbox)){
                if(velX<0 && isOnRight){//go left and is on is on right of that area
                    hitbox.x = obj.hitbox.x + obj.hitbox.width;
                    if(hitbox.x == obj.hitbox.x + obj.hitbox.width ){
                        obj.velX = (velX*mass + obj.velX*obj.mass*COR) / (obj.mass + mass);
                        velX = 0;
                        hitbox.x = obj.hitbox.x + obj.hitbox.width;
                    }
                }
                else if(velX>0 && isOnLeft){//go right and is on left of that area
                    hitbox.x = obj.hitbox.x - hitbox.width;
                    if(hitbox.x + hitbox.width == obj.hitbox.x){
                        obj.velX = (velX*mass + obj.velX*obj.mass*COR) / (obj.mass + mass);
                        velX = 0;
                        hitbox.x = obj.hitbox.x - hitbox.width;
                    }
                }
            }
                                 
        }
    }

    // protected void inElasticCollisionX(RigidBody obj){
    //     float combinedMass = mass + obj.mass;
    //     float newVelX = ((velX * mass) + (obj.velX * obj.mass) * COR) / combinedMass;
    //     obj.velX = newVelX;
        
    // }

    // protected void inElasticCollisionY(RigidBody obj){
    //     float combinedMass = mass + obj.mass;
    //     float newVelY = ((velY * mass) + (obj.velY * obj.mass) * COR) / combinedMass;
    //     obj.velY = newVelY;    
    // }



    public Rectangle getBoundsX(){

        float bx = hitbox.x + velX;
        float by = hitbox.y + 2;
        float bw = hitbox.width;
        float bh = hitbox.height - 4;
            
        return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
    }

    public Rectangle getBoundsY(){
        float bx = hitbox.x + 2;
        float by = hitbox.y + velY;
        float bw = hitbox.width - 4;
        float bh = hitbox.height;

        return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
    }
    
    public Area getFloorHitbox(){
        float floorHitboxWidth = hitbox.width * 0.8f; 
        float floorHitboxHeight = 4; 

        float floorHitboxX = hitbox.x + (hitbox.width - floorHitboxWidth) / 2;
        float floorHitboxY = hitbox.y + hitbox.height - floorHitboxHeight;

        return new Area(floorHitboxX, floorHitboxY, floorHitboxWidth, floorHitboxHeight+2);
    }
    public float getVelX(){
        return velX;
    }

    public static void setAreaInterection(Area area){
        areas.add(area);
    }

    public static void setAreasInterection(ArrayList<Area> area){
        areas.addAll(area);
    }  

    
}
