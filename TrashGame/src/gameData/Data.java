package gameData;


import main.Game;

public class Data {
    public enum ID {
        BLOCK, PLAYER, RIGIBODY, AREA
    }


    public class TileData {
        public static String TILESET = "Wall.png";
        public static String TEST_MAP = "Tmap.png";
        public static int BLOCK_HITBOX_WIDTH = Game.TILES_SIZE;
        public static int BLOCK_HITBOX_HEIGHT = Game.TILES_SIZE;
    }
}
