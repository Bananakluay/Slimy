package utils;

import static utils.Constants.Game.SCALE;

public class Constants {
    public class Game{
        public final static int TILES_DEFAULT_SIZE = 16;
        public final static float SCALE = 4f;
        public final static int TILES_IN_WIDTH = 26;
        public final static int TILES_IN_HEIGHT = 14;
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
    
}