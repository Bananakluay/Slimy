package levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import inputImage.Sprite;
import main.Game;
import simplePhysic.Area;
import TileMap.Block;
import TileMap.Tile;
import gameData.*;

@SuppressWarnings("unused")
public class Level {

	/* private int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

	private BufferedImage imgLvlData;
	private BufferedImage[] TileSet = Sprite.loadSpriteAsList(Data.TileData.TILESET);
	private ArrayList<Block> blocks = new ArrayList<>();

	public Level(BufferedImage imgLvlData) {
		this.imgLvlData = imgLvlData;
		generateLevelData();
		loadBlockdata();
		System.out.println(TileSet.length);
	}

	public void update(){
		for (Block block : blocks) {
			block.update();
		}
	}

	public void draw(Graphics g){
		// g.drawImage(TileSet[0], 100, 100, null);
		for (Block block : blocks) {
			block.draw(g);
		}
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
	public void loadBlockdata(){
			for(int row=0;row<imgLvlData.getHeight();row++){
				for(int col=0;col<imgLvlData.getWidth();col++){
					Color color = new Color(imgLvlData.getRGB(col, row));
					int value = color.getRed();
					if(value != 255){
						// Tile tile = new ...
						Block block = new Block(col*Game.TILES_SIZE, row*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, TileSet[value]);
						blocks.add(block);
						//tiles.add(tile);
					}
				}
			}
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	} */

	


	
}