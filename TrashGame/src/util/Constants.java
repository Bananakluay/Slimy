package util;

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
        //Physics
        public final static float GRAVITY = 0.1f;
        public final static float DOWN_FORCE = 15;
    }

    public class Player{
        public final static float JUMP_FORCE = 3f;
        public final static float WALK_SPEED = 4;
    }

}
