package Prefabs.Player.Character;

import dataStructure.Transform;
import utils.Vec2;

import static utils.Constants.Game.*;
import static utils.Constants.Player.*;
import Prefabs.Player.Player;


public class LargeSlime extends Player{


    public LargeSlime(String name, float x, float y, int zIndex) {
        super(name, new Transform(new Vec2(x, y), new Vec2(1.3f*TILES_SIZE,1.3f*TILES_SIZE)), zIndex);
        init();

    }

    private void init(){
        this.setMass(30);
        this.setFriction(2);
        this.setMobility(WALK_SPEED, JUMP_FORCE);
    }

   
    
    
}
