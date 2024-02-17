package traps;

import simplePhysics.Area;
import java.util.ArrayList;

import entities.Player;
import entities.Box;

public class Trap extends Area{
    
    public static ArrayList<Player> players = new ArrayList<>();

    public Trap(float x, float y, float width, float height) {
        super(x, y, width, height);
        
    }


    public void update() {
        System.out.println("Update trap");
    }

    protected void activate(Player player){
        System.out.println("activated!!!!");
    }

    

    public void detection(){
        for (Player player : players) {
            if(player.getHitbox().intersects(this)){
                this.activate(player);
            }
        }
        
    }
     public static void setInterection(ArrayList<Player> allplayers){
        players.addAll(allplayers);
     }

    
}
