package v_0_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MapMenu extends JMenuBar {
	
	MapScreen screen;
	
	JMenu m_file = new JMenu("File");
	JMenuItem mi_create = new JMenuItem("New Map");
	JMenuItem mi_save = new JMenuItem("Save Map");
	JMenuItem mi_load = new JMenuItem("Load Map");
	
	public MapMenu(MapScreen screen){
		this.screen = screen;
		
		mi_create.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
 
		mi_load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		mi_save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		m_file.add(mi_create);
		m_file.add(mi_save);
		m_file.add(mi_load);
		add(m_file);
	}
	
	public void create(){
		new MapDialog(screen);
	}
 
	public void load(){
		try {
			JFileChooser loadDialog=new JFileChooser();
			loadDialog.showOpenDialog(screen);
			FileInputStream file = new FileInputStream(loadDialog.getSelectedFile());
			BufferedInputStream bis = new BufferedInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(bis);
 
			int[][] map = (int[][]) ois.readObject();
			String mapName = (String) ois.readObject();
			String tilesetFilename = (String) ois.readObject();
			screen.setCurrentMap(new Map(map, mapName, tilesetFilename));
			ois.close();
			screen.getMapView().changeMap();
			screen.getTilePalette().repaint();
			screen.getMapView().repaint();
			screen.getMapView().getScroll().repaint();
			screen.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
 
	public void save(){
		try {
			JFileChooser saveDialog = new JFileChooser();
			saveDialog.showSaveDialog(screen);
			FileOutputStream file = new FileOutputStream(saveDialog.getSelectedFile());
			BufferedOutputStream bos = new BufferedOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(screen.getCurrentMap().getMap());
			oos.writeObject(screen.getCurrentMap().getMapName());
			oos.writeObject(screen.getCurrentMap().getTilesetFilename());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
