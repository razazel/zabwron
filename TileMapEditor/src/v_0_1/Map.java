package v_0_1;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {
	
	// width and height of one tile in pixel
	public final static int tilesize = 32;
	
	private String mapName;
	private String tilesetFilename;
	
	private int[][] map;
	
	private int width;
	private int height; 
	
	private ArrayList<BufferedImage> tileset = new ArrayList<BufferedImage>();
	
	public Map(int[][] map, String mapName, String tilesetFilename){
		this.map = map;
		this.mapName = mapName;
		this.tilesetFilename = tilesetFilename;
		this.width = map.length;
		this.height = map[0].length;
		
		try {
			BufferedImage imageTileset = ImageIO.read(new File(tilesetFilename));
			int width = imageTileset.getWidth() / tilesize;
			int height = imageTileset.getHeight() / tilesize;
			for(int x = 0; x<width; x++){
				for(int y = 0; y<height; y++){
					BufferedImage tile = imageTileset.getSubimage(x*tilesize, y*tilesize, tilesize, tilesize);
					this.tileset.add(tile);
				}
			}
			
		} catch (IOException e) {
			System.err.println("Tileset "+tilesetFilename+" nicht gefunden.");
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTileImage(int x, int y){
		int tile = map[x][y];
		return tileset.get(tile);
	}
	
	public void setTile(int x, int y, int tileId){
		map[x][y] = tileId;
	}
	
	public ArrayList<BufferedImage> getTileset(){
		return this.tileset;
	}
	
	public int[][] getMap(){
		return this.map;
	}
	
	public String getMapName(){
		return this.mapName;
	}
	
	public String getTilesetFilename(){
		return this.tilesetFilename;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public static Map load(String mapFilename)
	{
		try {
			FileInputStream file = new FileInputStream(new File(mapFilename));
			BufferedInputStream bis = new BufferedInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(bis);
 
			int[][] map = (int[][]) ois.readObject();
			String mapName = (String) ois.readObject();
			String tilesetFilename = (String) ois.readObject();
 
			Map loadedMap = new Map(map, mapName, tilesetFilename );
 
			ois.close();
			
			return loadedMap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
