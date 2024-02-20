package Manager;

import java.awt.event.KeyEvent;

import Prefabs.BigSlime;
import Prefabs.SmallSlime;
import main.Game;

public class PlayerManager {

    private static BigSlime bigSlime;
    private static SmallSlime smallSlime1;
    private static SmallSlime smallSlime2;
    public PlayerManager(){

    }

    public void update(){
        if(Game.KI.isKeyPressed(KeyEvent.VK_R)){
            switchPlayer();
        }
    }

    public BigSlime spawnBigSlime(float x, float y){
        if(bigSlime == null){
            bigSlime = new BigSlime("BigSlime", x, y, 0);
        }
        return bigSlime;
    }

    public SmallSlime spawnSmallSlime1(float x, float y){
        if(smallSlime1 == null){
            smallSlime1 = new SmallSlime("SmallSlime1", x, y, 0);
        }
        else{
            smallSlime1.getTransform().position.x = x;
            smallSlime1.getTransform().position.y = y;
        }
        return smallSlime1;
    }

    public SmallSlime spawnSmallSlime2(float x, float y){
        if(smallSlime2 == null){
            smallSlime2 = new SmallSlime("SmallSlime2", x, y, 0);
        }
        else{
            smallSlime1.getTransform().position.x = x;
            smallSlime1.getTransform().position.y = y;
        }
        return smallSlime2 ;
    }

    public static void switchPlayer(){
        if(smallSlime1.getController().isCurrentPlayer){
            smallSlime1.getController().setCurrentPlayer(false);
            smallSlime2.getController().setCurrentPlayer(true);
        }
        else{
            smallSlime1.getController().setCurrentPlayer(true);
            smallSlime2.getController().setCurrentPlayer(false);
        }
    }

}

