package gameData;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import main.Game;

public class Data {

    public enum ID {
        BLOCK, PLAYER, RIGIBODY, AREA
    }

    public class TileData {
        public static String TILESET = "TempTileSetV3.png";
        public static int BLOCK_HITBOX_WIDTH = Game.TILES_SIZE;
        public static int BLOCK_HITBOX_HEIGHT = Game.TILES_SIZE;
    }

    public static File[] GetAllLevels() {
        URL url = Data.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        File[] files = file.listFiles();
        // File[] filesSorted = new File[files.length];

        // for (int i = 0; i < filesSorted.length; i++)
        // for (int j = 0; j < files.length; j++) {
        // if (files[j].getName().equals((i + 1) + ".png"))
        // filesSorted[i] = files[j];

        // }
        return files;
    }

}
