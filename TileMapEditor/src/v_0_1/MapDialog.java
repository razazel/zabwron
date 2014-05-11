package v_0_1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MapDialog extends JDialog {
	
	private MapScreen screen;
	
	private JTextField txt_mapName = new JTextField();
	private JTextField txt_mapWidth = new JTextField();
	private JTextField txt_mapHeight = new JTextField();
	private JTextField txt_tilesetFilename = new JTextField();
 
	private JLabel lbl_mapName = new JLabel("Name:");
	private JLabel lbl_tilesetFilename = new JLabel("Tileset:");
	private JLabel lbl_mapWidth = new JLabel("Map Width:");
	private JLabel lbl_mapHeight = new JLabel("Map Height:");
	
	private JButton btn_create = new JButton("Create");
	
	public MapDialog(MapScreen screen){
		super(screen, "Create Map");
		this.screen = screen;
		
		Dimension dim = new Dimension(100, 20);
		 
		txt_mapWidth.setPreferredSize(dim);
		txt_mapHeight.setPreferredSize(dim);
		txt_mapName.setPreferredSize(dim);
		txt_tilesetFilename.setPreferredSize(dim);
 
		lbl_mapWidth.setPreferredSize(dim);
		lbl_mapHeight.setPreferredSize(dim);
		lbl_mapName.setPreferredSize(dim);
		lbl_tilesetFilename.setPreferredSize(dim);
 
		btn_create.addActionListener(new ActionListener(){	 
			@Override
			public void actionPerformed(ActionEvent e) {
				createMap();
			}
		});
		
		setSize(250,200);
		setLayout(new FlowLayout());
 
		add(lbl_mapName);
		add(txt_mapName);
 
		add(lbl_mapWidth);
		add(txt_mapWidth);
 
		add(lbl_mapHeight);
		add(txt_mapHeight);
		
		add(lbl_tilesetFilename);
		add(txt_tilesetFilename);
		
		add(btn_create);
		
		setVisible(true);
	}
	
	public void createMap(){
		int width = Integer.parseInt(txt_mapWidth.getText());
		int height = Integer.parseInt(txt_mapHeight.getText());
		int[][] map = new int[width][height];
		String mapName = txt_mapName.getText();
		String tileset = txt_tilesetFilename.getText();
		screen.setCurrentMap(new Map(map, mapName, tileset));
 
		screen.getMapView().changeMap();
		screen.getTilePalette().repaint();
		screen.getMapView().repaint();
		screen.getMapView().getScroll().repaint();
		screen.repaint();
		dispose();
	}
}
