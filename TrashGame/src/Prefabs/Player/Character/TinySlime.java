package Prefabs.Player.Character;

import dataStructure.Transform;
import utils.Vec2;

import static utils.Constants.Game.*;

import Prefabs.Player.Player;

public class TinySlime extends Player{

 

    public TinySlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE,TILES_SIZE)), zIndex);
        init();
    }
    
    private void init(){
        this.setMass(10);
        this.setFriction(1);
    }
  
    
}
