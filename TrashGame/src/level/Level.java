package level;

import static utils.Constants.Game.SCALE;
import static utils.Constants.Game.TILES_IN_HEIGHT;
import static utils.Constants.Game.TILES_IN_WIDTH;
import static utils.Constants.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dataStructure.AssetPool;
import dataStructure.Transform;
import entity.Entity;
import prefabs.exits.Door;
import prefabs.objects.Button;
import prefabs.objects.Gate;
import prefabs.objects.Platform;
import prefabs.objects.TileBlock;
import prefabs.player.PlayerManager;
import prefabs.player.SlimeType;
import prefabs.trap.BombButton;
import prefabs.trap.Spike;
import scene.LevelScene;
import utils.Vec2;
import java.util.Random;
import static utils.Constants.TileSet.GrassesOffset;

@SuppressWarnings("unused")
public class Level {

	private BufferedImage imgLvlData;

	private HashMap<String, Entity> lvlData;

	public Level(String imgLvlDataFile) {
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile, 1, 1);
		lvlData = new HashMap<String, Entity>();
		init();

	}

	public void init() {
		// generateLevelData();
		loadTileBlock(); // RED 0 - 21
		loadDoor(); // GREEN 255

		loadButton(); // RED 40
		loadGate(); // GREEN 254

		loadTrap();// GREEN 0 - 10

		loadPlayer();// BLUE 100

		loadPlatform();// RED 22 - 24
	}

	public void loadTileBlock() {
		// RED colorCode : 0 - 21
		List<BufferedImage> tileSet = AssetPool.getBufferedImageList(
				"TrashGame/res/assets/Tile/wall.png",
				16,
				16);

		List<BufferedImage> outerTileSet = AssetPool.getBufferedImageList(
				"TrashGame/res/assets/Tile/grasses.png",
				16,
				21);

		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getRed();

				if (colorCode < 0 || colorCode > tileSet.size() || colorCode == 255)
					continue;

				String name = "TileBlock" + col + "" + row;

				TileBlock tileBlock = new TileBlock(
						name,
						col * TILES_SIZE,
						row * TILES_SIZE,
						tileSet, outerTileSet,
						colorCode);

				lvlData.put(name, tileBlock);
				LevelScene.getEntityManager().addEntity(tileBlock);

			}
		}
	}

	public void loadPlayer() {
		// BLUE Color Code : 100
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getBlue();

				if (colorCode == 100) {
					LevelScene.getPlayerManager().spawnSlime(
							"Green",
							TILES_SIZE * col,
							TILES_SIZE * row,
							SlimeType.TINY_SLIME);

					LevelScene.getPlayerManager().spawnSlime(
							"Yellow",
							TILES_SIZE * col,
							TILES_SIZE * row,
							SlimeType.TINY_SLIME);

					return;
				}
			}
		}

	}

	public void loadDoor() {
		// GREEN Color Code : 255
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getGreen();

				String name = "ExitsDoor" + col + "" + row;

				if (colorCode == 255) {
					Door door = new Door(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);
					lvlData.put(name, door);
					LevelScene.getEntityManager().addEntity(door);
					return;
				}
			}
		}
	}

	int maxButton = 5;

	public void loadGate() {
		// GREEN Color Code : 254
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getGreen();

				String name = "Gate" + col + "" + row;

				if (colorCode == 254) {
					Gate gate = new Gate(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);
					lvlData.put(name, gate);
					for (int i = 0; i < maxButton; i++) {
						Button button = (Button) lvlData.get("Button" + i + "For" + col + "" + row);
						if (button != null)
							gate.addListener(button);
					}
					LevelScene.getEntityManager().addEntity(gate);
					break;
				}
			}
		}
	}

	public void loadPlatform() {
		// RED colorCode : 22 - 24

		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getRed();

				if (colorCode < 22 || colorCode > 25)
					continue;

				String name = "Platform" + col + "" + row;

				Platform platform = new Platform(col * TILES_SIZE, row * TILES_SIZE, colorCode % 22);

				lvlData.put(name, platform);
				LevelScene.getEntityManager().addEntity(platform);

			}
		}

	}

	public void loadButton() {
		// Red Color Code : 40
		int i = 0;
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getRed();
				Vec2 posGate = new Vec2(color.getGreen(), color.getBlue());

				if (colorCode == 40) {
					String name = "Button" + (i++) + "For" + (int) posGate.x + "" + (int) posGate.y;
					Button button = new Button(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);
					lvlData.put(name, button);
					LevelScene.getEntityManager().addEntity(button);
					break;
				}
			}
		}
	}

	public void loadTrap() {
		// GREEN Color Code : 0 - 10
		// 0 : spike
		// 1 : bomb button

		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getGreen();

				Entity trap = null;

				if (colorCode == 0) {
					String name = "Spike" + col + "" + row;
					Spike spike = new Spike(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);

					trap = spike;
				} else if (colorCode == 1) {
					String name = "BombButton" + col + "" + row;
					BombButton bombButton = new BombButton(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);

					trap = bombButton;
				}

				if (trap != null) {
					LevelScene.getEntityManager().addEntity(trap);
				}
			}
		}
	}

}