package simplePhysics;
    // protected void ObjectCollision(ArrayList<RigidBody> objs){
    //     for(int i=0;i<objs.size();i++){
    //         RigidBody obj = objs.get(i);
    //         if(getBoundsX().intersects(obj.getBoundsX())){
    //             System.out.println("Collision");
    //             if(obj.velX < 0){
    //                 if(hitbox.x < obj.hitbox.x + obj.hitbox.width/2) hitbox.x = obj.hitbox.x - obj.hitbox.width;
    //             }
    //             else if(obj.velX > 0)
    //                 if(hitbox.x > obj.hitbox.x + obj.hitbox.width/2) hitbox.x = obj.hitbox.x + obj.hitbox.width;

    //             if(velX>0){ //objht
    //                 velX = 0;
    //                 hitbox.x = obj.hitbox.x - hitbox.width;
    //             }
    //             else if(velX<0){//left
    //                 velX = 0;
    //                 hitbox.x = obj.hitbox.x + obj.hitbox.width;
    //             }
    //         }

    //         if(getBoundsY().intersects(obj.getBoundsY())){
    //             System.out.println("Collision");
    //             if(obj.velY < 0){
    //                 if(hitbox.y < obj.hitbox.y + obj.hitbox.height/2) hitbox.y = obj.hitbox.y - obj.hitbox.height;
    //             }
    //             else if(obj.velY > 0)
    //                 if(hitbox.y > obj.hitbox.y + obj.hitbox.height/2) hitbox.y = obj.hitbox.y + obj.hitbox.height;

    //             if(velY>0){// down
    //                 velY = 0;
    //                 hitbox.y = obj.hitbox.y - hitbox.height;
    //             }
    //             else if(velY<0){ //up
    //                 velY = 0;
    //                 hitbox.y = obj.hitbox.y + obj.hitbox.height;
    //             }
    //         }
    //     }
    // }




        // public void checkCollion(){
    //     for (RigidBody obj : objs) {
    //         handleCollision(obj);
    //     }
    // }
    // protected void handleCollision(RigidBody otherBox) {
    //     if (getBoundsX().intersects(otherBox.getHitbox())) {
    //         float combinedMass = mass + otherBox.getMass();
            
    //         // Calculate new velocities using conservation of momentum and COR:
    //         float newVelX = ((velX * mass) + (otherBox.getVelX() * otherBox.getMass()) * COR) / combinedMass;
    //         float newVelY = ((velY * mass) + (otherBox.getVelY() * otherBox.getMass()) * COR) / combinedMass;
            
    //         // Reverse velocities if bouncing off a wall:
    //         // if (otherBox == wall) {
    //             //     newVelX = -newVelX;
    //             //     newVelY = -newVelY;
    //             // }
                
    //         velX = newVelX;
    //         velY = newVelY;
    //         if(velX > 0){
    //             otherBox.getHitbox().x = hitbox.x + hitbox.width;
    //         }
    //         else if(velX < 0){
    //             otherBox.getHitbox().x = hitbox.x - otherBox.getHitbox().width;
    //         }
    //         else if(velX == 0){
  
    //         }
    //     }
    // }