package traps;

import simplePhysics.Area;
import java.util.ArrayList;

import entities.Player;

public class Trap extends Area{
    
    protected static ArrayList<Player> players = new ArrayList<>();

    public Trap(float x, float y, float width, float height) {
        super(x, y, width, height);
        
    }


    protected void activate(Player player){
        System.out.println("activated!!!!");
    };

    public void detection(){
        for (Player player : players) {
            if(player.getHitbox().intersects(this)){
                this.activate(player);
            }
        }
    }


    
}
