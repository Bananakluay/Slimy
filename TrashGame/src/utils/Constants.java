package utils;

import static utils.Constants.Game.SCALE;


public class Constants {
    public class Game{
        public final static int TILES_DEFAULT_SIZE = 16;
        public final static float SCALE = 3.2f;
        public final static int TILES_IN_WIDTH = 32;
        public final static int TILES_IN_HEIGHT = 18;
        public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
        public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    }

    public class Physics{
        public final static float GRAVITY = 0.025f * SCALE;
        public final static float DOWN_FORCE = 3*SCALE;
    }

    public class Player{
        public final static float JUMP_FORCE = 0.103f * SCALE;
        public final static float WALK_SPEED = 1.3f * SCALE;

        public final static String BLUE = "Blue";
        public final static String GREEN = "Green";
        public final static String YELLOW = "Yellow";
    }

    public class Layer{
        public final static int PLAYER_LAYER = 3;


    }

    public class TileSet {
        public static Vec2 GrassesOffset(int value){
            switch (value) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return new Vec2(0, -4*SCALE);
                case 4:
                    return new Vec2(0, -7*SCALE);
                case 5:
                    return new Vec2(0, -8*SCALE);
                case 6:
                case 8:
                    return new Vec2(0, -5*SCALE);
                case 9: 
                    return new Vec2(0, 0);
                case 10:
                case 11:
                    return new Vec2(0, -2*SCALE);
                case 12:
                case 13:
                case 14:
                    return new Vec2(0, 5*SCALE);
                case 15:
                    return new Vec2(0, 5*SCALE);
                case 18:
                case 19:
                case 20:
                case 21:
                    return new Vec2(0, -2*SCALE);
                default:
                    return new Vec2(0, 0);
            }
        }
    }
    
}
