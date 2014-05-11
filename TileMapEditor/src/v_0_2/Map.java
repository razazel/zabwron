package v_0_2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {
	
	public final static int tilesize = 32;
	
	private String mapName;
	private String tilesetFilename;
	
	private int[][][] map;
	
	// width and height in tiles. depth in layercount.
	private int width;
	private int height; 
	private int depth;
	
	private ArrayList<BufferedImage> tileset = new ArrayList<BufferedImage>();
	
	public Map(int[][][] map, String mapName, String tilesetFilename){
		this.map = map;
		this.mapName = mapName;
		this.tilesetFilename = tilesetFilename;
		
		this.width = map.length;
		this.height = map[0].length;
		this.depth = map[0][0].length;
		
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
}
