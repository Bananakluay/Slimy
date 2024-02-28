package prefabs.objects;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_SIZE;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import components.Bounds;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import entity.EntityType;
import utils.Vec2;
import utils.Constants.Layer;

public class TileBlock extends Entity {

    List<BufferedImage> tileImg;
    List<BufferedImage> outerTileImg;

    int colorCode;

    boolean option;
    boolean randomOuter = false;

    Random rand;

    public TileBlock(String name, float x, float y, List<BufferedImage> tileImg, int colorCode) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)), Layer.TILE);
        this.type = EntityType.TILE;
        this.colorCode = colorCode;
        this.tileImg = tileImg;
        this.option = false;
        init();
    }

    public TileBlock(String name, float x, float y, List<BufferedImage> tileImg, List<BufferedImage> outerTileImg, int colorCode) {
        super(name, new Transform(new Vec2(x, y), new Vec2(TILES_SIZE, TILES_SIZE)), Layer.TILE);

        this.type = EntityType.TILE;
        this.colorCode = colorCode;
        this.tileImg = tileImg;
        this.outerTileImg = outerTileImg;
        this.option = true;

        rand = new Random();
        randomOuter = rand.nextInt(100) < 20;

        init();
    }

    private void init() {
        addComponent(new Bounds(null, null));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // draw Tile
        g.drawImage(
                tileImg.get(colorCode),
                (int) this.getPosition().x,
                (int) this.getPosition().y,
                (int) this.getScale().x,
                (int) this.getScale().y,
                null);

        // draw OuterTile
        if (option && randomOuter) {
            g.drawImage(
                    outerTileImg.get(colorCode),
                    (int) (this.getPosition().x + OuterOffset(colorCode).x),
                    (int) (this.getPosition().y + OuterOffset(colorCode).y),
                    (int) this.getScale().x,
                    (int) this.getScale().y,
                    null);
        }
    }

    public static Vec2 OuterOffset(int value) {
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
                return new Vec2(0, -4 * SCALE);
            case 4:
                return new Vec2(0, -7 * SCALE);
            case 5:
                return new Vec2(0, -8 * SCALE);
            case 6:
            case 8:
                return new Vec2(0, -5 * SCALE);
            case 9:
                return new Vec2(0, 0);
            case 10:
            case 11:
                return new Vec2(0, -2 * SCALE);
            case 12:
            case 13:
            case 14:
            case 15:
                return new Vec2(0, 5 * SCALE);
            case 18:
            case 19:
            case 20:
            case 21:
                return new Vec2(0, -3 * SCALE);
            default:
                return new Vec2(0, 0);
        }
    }

}
