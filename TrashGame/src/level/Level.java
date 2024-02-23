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

import Prefabs.Spike;
import Prefabs.TileBlock;
import Prefabs.Exits.Door;
import Prefabs.Player.PlayerManager;
import Prefabs.Player.SlimeType;
import Scene.LevelScene;
import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import utils.Vec2;
import java.util.Random;
import static utils.Constants.TileSet.GrassesOffset;

@SuppressWarnings("unused")
public class Level {

	private String imgLvlDataFile;

	private int[][] lvlData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];

	private LevelScene levelScene;

	private BufferedImage imgLvlData;
	private List<BufferedImage> tileSet;
	private List<BufferedImage> grassesTileSet;

	private Random rand;

	public Level(String imgLvlDataFile, LevelScene levelScene) {
		this.imgLvlDataFile = imgLvlDataFile;
		this.levelScene = levelScene;
		rand = new Random();
		init();

	}

	public void init() {
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile, 1, 1);

		tileSet = AssetPool.getBufferedImageList("TrashGame/res/assets/Tile/wall.png", 16, 16);
		grassesTileSet = AssetPool.getBufferedImageList("TrashGame/res/assets/Tile/grasses.png", 16, 21);

		generateLevelData();
		loadTileBlock();
		loadPlayer();
		loadDoor();
		loadTrap();
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

				if (value < 0 || value > tileSet.size() || value == 255)
					continue;

				Vec2 posGrasses = new Vec2(col * TILES_SIZE, row * TILES_SIZE);
				posGrasses.add(GrassesOffset(value));

				int r = rand.nextInt(5);

				TileBlock tileBlock = new TileBlock(
						tileSet.get(value), new Vec2(col * TILES_SIZE, row * TILES_SIZE),
						grassesTileSet.get(value), posGrasses,
						r == 1);
				levelScene.addEntity(tileBlock);
				levelScene.renderer.submit(tileBlock);

			}
		}
	}

	public void loadPlayer() {
		// temp position
		PlayerManager.spawnSlime("Blue", TILES_SIZE * 4, TILES_SIZE * 10, SlimeType.LARGE_SLIME);
		levelScene.addEntity(PlayerManager.blueLargeSlime);
		levelScene.renderer.submit(PlayerManager.blueLargeSlime);
	}

	public void loadDoor() {
		// temp position
		Door Door = new Door("Exits Door",
				new Transform(new Vec2(TILES_SIZE * 15, TILES_SIZE * 11), new Vec2(TILES_SIZE, TILES_SIZE)));
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

	public void loadTrap() {
		Spike spike = new Spike("spike", TILES_SIZE * 10, TILES_SIZE * 12);
		levelScene.addEntity(spike);
		levelScene.renderer.submit(spike);
	}

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