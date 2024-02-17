package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import inputImage.Sprite;
import main.Game;
import simplePhysics.Area;
import simplePhysics.RigidBody;
import tileMap.TileBlock;
import gameData.*;

@SuppressWarnings("unused")
public class Level {

	private int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

	private BufferedImage imgLvlData;
	private BufferedImage[] TileSet = Sprite.loadSpriteAsList(Data.TileData.TILESET);
	private ArrayList<Area> tiles = new ArrayList<>();

	public Level(BufferedImage imgLvlData) {
		this.imgLvlData = imgLvlData;
		generateLevelData();
		loadTileBlockData();
		RigidBody.setAreasInterection(tiles);
	}

	public void update() {

	}

	public void draw(Graphics g) {
		for (Area tile : tiles) {
			tile.draw(g);
		}
	}

	public void generateLevelData() {
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {
				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getRed();
				lvlData[row][col] = value;
			}
		}

	}

	public void loadTileBlockData() {
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {
				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getRed();
				if (value != 255) {
					TileBlock tile = new TileBlock(col * Game.TILES_SIZE, row * Game.TILES_SIZE, Game.TILES_SIZE,
							Game.TILES_SIZE, TileSet[value]);
					tiles.add(tile);
				}
			}
		}
	}

	public ArrayList<Area> getTiles() {
		return tiles;
	}

	public void reset() {
		lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		for (Area tile : tiles) {
			if (tile instanceof TileBlock) {
				((TileBlock) tile).reset();
			}
		}
	}

}