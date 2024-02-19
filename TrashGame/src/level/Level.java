package level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import components.Spritesheet;
import dataStructure.AssetPool;
import entity.Entity;
import physics.Prefabs.TileBlock;

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
	private Spritesheet spritesheet;

	private List<Entity> entities = new ArrayList<>();
	public Level(String imgLvlDataFile, String tileSetFile) {
		this.imgLvlDataFile = imgLvlDataFile;
		this.tileSetFile = tileSetFile;
		init();
		
	}
	
	public void init(){
		imgLvlData = AssetPool.getBufferedImage(imgLvlDataFile);
		spritesheet = new Spritesheet(tileSetFile, 16, 16);
		generateLevelData();
		loadTileBlockData();
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
	
	public void loadTileBlockData(){
			for(int row=0;row<imgLvlData.getHeight();row++){
				for(int col=0;col<imgLvlData.getWidth();col++){
					Color color = new Color(imgLvlData.getRGB(col, row));
					int value = color.getRed();
					if(value != 255){
						// TileBlock tile = new TileBlock(col * Game.TILES_SIZE, row * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE,TileSet[value]);
						// tiles.add(tile);
						System.out.println(col +" "+ row);
						TileBlock tileBlock =  new TileBlock(col*TILES_SIZE, row*TILES_SIZE, TILES_SIZE, TILES_SIZE, spritesheet.sprites.get(value));
						entities.add(tileBlock);
					}
				}
			}

	}

	public List<Entity> getAllEntities(){
		return entities;
	}


	



	
}