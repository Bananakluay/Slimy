package level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

<<<<<<< HEAD
=======
import Prefabs.TileBlock;
>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66
import components.SubSprite;
import dataStructure.AssetPool;
import entity.Entity;

import static util.Constants.Game.TILES_IN_WIDTH;
import static util.Constants.Game.TILES_SIZE;
import static util.Constants.Game.SCALE;
import static util.Constants.Game.TILES_IN_HEIGHT;;
@SuppressWarnings("unused")
public class Level {

	
	private int[][] lvlData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
	private String imgLvlDataFile;
	private String tileSetFile;
	
	private BufferedImage imgLvlData;
	// private SubSprite spritesheet;

	private List<Entity> entities = new ArrayList<>();
	public Level(String imgLvlDataFile, String tileSetFile) {
		this.imgLvlDataFile = imgLvlDataFile;
		this.tileSetFile = tileSetFile;
		init();
		
	}
	
	public void init(){
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile);
		// spritesheet = new SubSprite(tileSetFile, 16, 16);
		generateLevelData();
		loadTileBlock();
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
<<<<<<< HEAD
						SubSprite s = new SubSprite(tileSetFile, 8, 8);
						TileBlock tileBlock =  new TileBlock(col*TILES_SIZE, row*TILES_SIZE, TILES_SIZE, TILES_SIZE, s.sprites.get(0));

=======
						SubSprite s = new SubSprite(tileSetFile, 8);
						TileBlock tileBlock =  new TileBlock(col*TILES_SIZE, row*TILES_SIZE, TILES_SIZE, TILES_SIZE, s.sprites.get(value));
>>>>>>> 52604e9134d0e7315bcd971cc24bdf4234f98e66
						entities.add(tileBlock);
					}
				}
			}

	}

	//TODO public void loadPlayer(){}

	//TODO public void loadTrap(){}

	//TODO public void loadDoor(){}

	//TODO public void loadButton();

	public List<Entity> getAllEntities(){
		return entities;
	}


	



	
}