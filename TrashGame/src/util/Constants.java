package util;

import static util.Constants.Game.SCALE;

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
        public final static float JUMP_FORCE = 0.75f * SCALE;
        public final static float WALK_SPEED = 1.3f * SCALE;
    }
    
}
