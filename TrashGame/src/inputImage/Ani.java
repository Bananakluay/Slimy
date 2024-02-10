package inputImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import gameData.Data;
import gameState.GameState;
import gameState.GameStatesManager;
import input.MouseInputs;

public class Ani extends Sprite {

    private BufferedImage[] aniLeft = loadSpriteAsList(Data.PlayerData.player_left) ; //sprite list
    private BufferedImage[] aniRight = loadSpriteAsList(Data.PlayerData.player_right) ;
    private BufferedImage[][] aniSprite;
    private int aniTick, aniIndex , aniSpeed = 30;
    private MouseInputs mouseInputs;
    private GameStatesManager gms;
    private GameState currentState;

    public Ani(){
         
    }

    public BufferedImage[] getAniLeft(){
        return aniLeft;
    }

    public BufferedImage[] getAniRight(){
        return aniRight;
    }
    
    public int aniIndex(){
        updateAnimationTick();
        return aniIndex;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= aniLeft.length)
                aniIndex = 0;
        }
    }

    

    
    

}
