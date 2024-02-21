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
import Prefabs.Player.PlayerManager;
import Prefabs.Player.SlimeType;
import Scene.LevelScene;
import components.SubSprite;
import dataStructure.AssetPool;
import entity.Entity;;
@SuppressWarnings("unused")
public class Level {

	private int[][] lvlData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
	private String imgLvlDataFile;
	private String tileSetFile;
	private BufferedImage imgLvlData;
	// private SubSprite spritesheet;

	private LevelScene levelScene;

	public Level(String imgLvlDataFile, String tileSetFile, LevelScene levelScene) {
		this.imgLvlDataFile = imgLvlDataFile;
		this.tileSetFile = tileSetFile;
		this.levelScene = levelScene;
		init();
		
	}
	
	public void init(){
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile);
		generateLevelData();
		loadTileBlock();
		loadPlayer();
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
						SubSprite s = new SubSprite(tileSetFile, 8);
						TileBlock tileBlock =  new TileBlock(col*TILES_SIZE, row*TILES_SIZE, TILES_SIZE, TILES_SIZE, s.sprites.get(value));
						levelScene.addEntity(tileBlock);
						levelScene.renderer.submit(tileBlock);
					}
				}
			}
	}

	public void loadPlayer(){
		PlayerManager.spawnSlime("Blue", TILES_SIZE*2,TILES_SIZE*2, SlimeType.LARGE_SLIME);
		levelScene.addEntity(PlayerManager.blueLargeSlime);
		levelScene.renderer.submit(PlayerManager.blueLargeSlime);
	}

	public void removeEntity(String name){

	}
	//TODO public void loadTrap(){}

	//TODO public void loadDoor(){}

	//TODO public void loadButton();





	



	
}