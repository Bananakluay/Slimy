package level;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_IN_HEIGHT;
import static utils.Constants.Game.TILES_IN_WIDTH;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Prefabs.TileBlock;
import Prefabs.Exits.Door;
import Prefabs.Player.PlayerManager;
import Prefabs.Player.SlimeType;
import Scene.LevelScene;
import components.SubSprite;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import utils.Vec2;;

@SuppressWarnings("unused")
public class Level {

	private String imgLvlDataFile;
	private String tileSetFile;

	private int[][] lvlData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
	private BufferedImage imgLvlData;

	private LevelScene levelScene;

	public Level(String imgLvlDataFile, String tileSetFile, LevelScene levelScene) {
		this.imgLvlDataFile = imgLvlDataFile;
		this.tileSetFile = tileSetFile;
		this.levelScene = levelScene;
		init();

	}

	public void init() {
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile);
		generateLevelData();
		loadTileBlock();
		loadPlayer();
		loadDoor();
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

	public void loadTileBlock() {
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {
				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getRed();

				SubSprite s = new SubSprite(tileSetFile, 8);

				if (value < 0 || value > s.sprites.size() || value == 255)
					continue;

				TileBlock tileBlock = new TileBlock(col * TILES_SIZE, row * TILES_SIZE, TILES_SIZE, TILES_SIZE,
						s.sprites.get(value));
				levelScene.addEntity(tileBlock);
				levelScene.renderer.submit(tileBlock);
			}
		}
	}

	public void loadPlayer() {
		// temp position
		PlayerManager.spawnSlime("Blue", TILES_SIZE * 2, TILES_SIZE * 2, SlimeType.LARGE_SLIME);
		levelScene.addEntity(PlayerManager.blueLargeSlime);
		levelScene.renderer.submit(PlayerManager.blueLargeSlime);
	}

	public void loadDoor() {
		// temp position
		Door Door = new Door("Exits Door",
				new Transform(new Vec2(TILES_SIZE * 15, TILES_SIZE * 11), new Vec2(TILES_SIZE, TILES_SIZE * 2)), 0);
		levelScene.addEntity(Door);
		levelScene.renderer.submit(Door);

		// for(int row=0;row<imgLvlData.getHeight();row++){
		// for(int col=0;col<imgLvlData.getWidth();col++){
		// Color color = new Color(imgLvlData.getRGB(col, row));
		// int value = color.getGreen();

		// Door door = new Door("Exits Door", new Transform(new Vec2(TILES_SIZE*col,
		// TILES_SIZE*row), new Vec2(TILES_SIZE, TILES_SIZE*2)), 0);
		// levelScene.addEntity(door);
		// levelScene.renderer.submit(door);
		// break;
		// }
		// }
	}

	// TODO public void loadTrap(){}

	// TODO public void loadPlayer(){}

	// TODO public void loadTrap(){}

	// TODO public void loadButton();
	/*
	 * public void onDestroy() {
	 * imgLvlData = null;
	 * for (Entity e : entities) {
	 * e = null;
	 * }
	 * entities.clear();
	 * lvlData = null;
	 * }
	 */
}