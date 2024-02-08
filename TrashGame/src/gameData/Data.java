package gameData;


import main.Game;

public class Data {
    public enum ID {
        BLOCK, PLAYER, RIGIBODY, AREA
    }


    public class TileData {
        public static String TILESET = "TempTileSetV3.png";
        public static String TEST_MAP = "mapdataV2.png";
        public static int BLOCK_HITBOX_WIDTH = Game.TILES_SIZE;
        public static int BLOCK_HITBOX_HEIGHT = Game.TILES_SIZE;
    }
}
