package gameData;


import main.Game;

public class Data {
    public enum ID {
        BLOCK, PLAYER, RIGIBODY, AREA
    }


    public class TileData {
        public static String TILESET = "TempTileSetV3.png";
        public static String MAP_DATA = "traptestmap5.png";

        public static int BLOCK_HITBOX_WIDTH = Game.TILES_SIZE;
        public static int BLOCK_HITBOX_HEIGHT = Game.TILES_SIZE;

        public static String SPIKES_DATA = "spikes6.png";

        public static String PLATEOFF_DATA = "plateoff.png";
        public static String PLATEON_DATA = "plateon.png";
    }
}
