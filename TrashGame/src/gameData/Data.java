package gameData;


import main.Game;

public class Data {
    public enum ID {
        BLOCK, PLAYER, RIGIBODY, AREA
    }
    public class PlayerData {
        public static String PLAYERSET = "Slime.png";
    }

    public class TileData {
        public static String TILESET = "TempTileSetV3.png";
        public static String TEST_MAP = "testmapv4.png";
        public static int BLOCK_HITBOX_WIDTH = Game.TILES_SIZE;
        public static int BLOCK_HITBOX_HEIGHT = Game.TILES_SIZE;
    }
}
