package v_0_1;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MapScreen extends JFrame {
	
	private Map currentMap;
	private TilePalette tilePalette;
	private MapView mapView;
	private MapMenu mapMenu;
	
	public MapScreen(){
		setSize(1024, 800);
		setTitle("Map Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		requestFocus();
		setLayout(new BorderLayout());
		
		int map[][] = new int[30][30];
		
		String mapName = "Our First Map";
		String tilesetFilename = "Tileset.png";
		
		currentMap = new Map(map, mapName ,tilesetFilename);
		
		mapMenu = new MapMenu(this);
		mapView = new MapView(this);
		tilePalette = new TilePalette(this);
		
		add(mapMenu, BorderLayout.NORTH);
		add(tilePalette, BorderLayout.WEST);
		add(mapView.getScroll() , BorderLayout.CENTER);
	}
	
	public Map getCurrentMap(){
		return this.currentMap;
	}
	
	public void setCurrentMap(Map map){
		this.currentMap = map;
	}
	
	public MapView getMapView(){
		return this.mapView;
	}
	
	public TilePalette getTilePalette(){
		return this.tilePalette;
	}

}
