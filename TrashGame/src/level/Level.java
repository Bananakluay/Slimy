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
import Prefabs.Exits.Door;
import Prefabs.Objects.Box;
import Prefabs.Objects.Button;
import Prefabs.Objects.Gate;
import Prefabs.Objects.Platform;
import Prefabs.Objects.TileBlock;
import Prefabs.Player.PlayerManager;
import Prefabs.Player.SlimeType;
import Prefabs.Trap.ArrowTrap;
import Prefabs.Trap.BombButton;
import Prefabs.Trap.Spike;
import Scene.LevelScene;
import utils.Vec2;
import java.util.Random;

import components.Sprite;

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
		loadTileBlock(); // RED [0 , 21]

		loadPlatform();// RED [32 , 34]

		loadButton(); // RED : 40 | BLUE : posX Gate | GREEN : posY Gate

		loadTrap();// RED : 255 | GREEN [0 , 10] | BLUE : 80

		loadBox(); // RED : 220 | GREEN : 150 | BLUE : 90

		loadGate(); // RED : 100 | GREEN : 100 | BLUE : 100

		loadDoor(); // RED : 255 | GREEN : 255 | BLUE : 255

		loadPlayer(); // RED : 100 | GREEN : 255 | BLUE : 100

	}

	public void loadTileBlock() {
		// RED [0 , 21]
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

				if (colorCode < 0 || colorCode > 22 || colorCode == 255)
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

	public void loadPlatform() {
		// RED [32 , 34]

		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				int colorCode = color.getRed();

				if (colorCode < 32 || colorCode > 35)
					continue;

				String name = "Platform" + col + "" + row;

				Platform platform = new Platform(col * TILES_SIZE, row * TILES_SIZE, colorCode - 32);

				lvlData.put(name, platform);
				LevelScene.getEntityManager().addEntity(platform);

			}
		}

	}

	public void loadButton() {
		// RED : 40 | BLUE : posX Gate | GREEN : posY Gate
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
		// RED : 255 | GREEN [0 , 10] | BLUE : 80
		// 0 : spike
		// 1 : bomb button
		// 2 : arrow trap

		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));

				Entity trap = null;
				if (color.getRed() != 255 && color.getBlue() != 80)
					continue;
				if (color.getGreen() == 0) {
					String name = "Spike" + col + "" + row;
					Spike spike = new Spike(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);
					trap = spike;
				} else if (color.getGreen() == 1) {
					String name = "BombButton" + col + "" + row;
					BombButton bombButton = new BombButton(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);

					trap = bombButton;
				}else if(color.getGreen() == 2){
					String name = "ArrowTrap" + col + "" + row;
					ArrowTrap spike = new ArrowTrap(
							name,
							col * TILES_SIZE,
							row * TILES_SIZE);
					trap = spike;
				}

				if (trap != null) {
					LevelScene.getEntityManager().addEntity(trap);
				}
			}
		}
	}

	public void loadBox() {
		// RED : 220 | GREEN : 150 | BLUE : 90
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));

				String name = "Box" + col + "" + row;
				if (color.getRed() == 220 && color.getGreen() == 150 && color.getBlue() == 90) {
					Box box = new Box(name, col * TILES_SIZE, row * TILES_SIZE);
					LevelScene.getEntityManager().addEntity(box);
				}
			}
		}
	}

	int maxButton = 5;

	public void loadGate() {
		// RED : 100 | GREEN : 100 | BLUE : 100
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));

				String name = "Gate" + col + "" + row;

				if (color.getRed() == 100 && color.getGreen() == 100 && color.getBlue() == 100) {
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

	public void loadDoor() {
		// RED : 255 | GREEN : 255 | BLUE : 255
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));

				String name = "ExitsDoor" + col + "" + row;

				if (color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255) {
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

	public void loadPlayer() {
		// RED : 100 | GREEN : 255 | BLUE : 100
		for (int row = 0; row < imgLvlData.getHeight(); row++) {
			for (int col = 0; col < imgLvlData.getWidth(); col++) {

				Color color = new Color(imgLvlData.getRGB(col, row));
				if (color.getRed() == 100 && color.getGreen() == 255 && color.getBlue() == 100) {
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

}