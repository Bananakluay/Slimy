package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Player;
import entities.PlayerManager;
import inputImage.Sprite;
import main.Game;
import simplePhysics.Area;
import simplePhysics.RigidBody;
import tileMap.TileBlock;

import traps.PlateButtonOff;
import traps.PlateButtonOn;
import traps.Spike;
import traps.Trap;
import traps.ArrowButton;
import gameData.*;

@SuppressWarnings("unused")
public class Level {

	private int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

	private BufferedImage imgLvlData;
	private BufferedImage[] TileSet = Sprite.loadSpriteAsList(Data.TileData.TILESET);

	private ArrayList<Area> tiles = new ArrayList<>();
	private ArrayList<Trap> traps = new ArrayList<>();
	private PlayerManager playerManager;

	public Level(BufferedImage imgLvlData, PlayerManager player) {
		this.imgLvlData = imgLvlData;
		this.playerManager = player;
		generateLevelData();
		loadTileBlock();

		Trap.setInterection(player.getPlayer());
		System.out.println(Trap.players.size());
		loadTrap();

	}

	public void update(){
		for (Trap trap : traps) {trap.update();}
	}

	public void draw(Graphics g){
		for (Area tile : tiles) {tile.draw(g);}
		for (Trap trap : traps) {trap.draw(g);}
	}


	public void generateLevelData(){
		for(int row=0; row<imgLvlData.getHeight();row++){
			for(int col=0; col<imgLvlData.getWidth();col++){
				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getRed();
				lvlData[row][col] = value;
			}
		}

	}
	public void loadTileBlock(){
		for(int row=0;row<imgLvlData.getHeight();row++){
			for(int col=0;col<imgLvlData.getWidth();col++){
				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getRed();
				if(value != 255){
					TileBlock tile = new TileBlock(col * Game.TILES_SIZE, row * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE,TileSet[value]);
					tiles.add(tile);
				}
			}
		}
		RigidBody.setAreasInterection(tiles);
	}

	public void loadTrap(){
		float gameSize = Game.TILES_SIZE;

		for(int row=0;row<imgLvlData.getHeight();row++){

			for(int col=0;col<imgLvlData.getWidth();col++){

				Color color = new Color(imgLvlData.getRGB(col, row));
				int value = color.getBlue();

				if (value == 0) {
					Spike spike = new Spike(col * gameSize, (row * gameSize)  + (gameSize / 2) , gameSize, gameSize / 2);
					traps.add(spike);
				}

				if (value == 1) {
					PlateButtonOff plateButtonOff = new PlateButtonOff(col *gameSize, (row * gameSize) +(gameSize-gameSize * 0.1f)  , gameSize, gameSize * 0.1f);
					traps.add(plateButtonOff);
					// if (player.getHitbox().intersects(this))
					// 	PlateButtonOn plateButtonOn = new PlateButtonOn(col *gameSize, (row * gameSize) +(gameSize-gameSize * 0.1f)  , gameSize, gameSize * 0.1f);
					// 	traps.add(plateButtonOn);
				}

				if (value == 2) {
					ArrowButton arrowButton = new ArrowButton(col *gameSize + gameSize / 4, (row * gameSize)  + (gameSize / 2)  , gameSize / 2, gameSize / 2, null);
					traps.add(arrowButton);
				}
				
			}
		}
	}

	public ArrayList<Area> getTiles() {
		return tiles;
	}


	
}