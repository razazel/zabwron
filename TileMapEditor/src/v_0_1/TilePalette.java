package v_0_1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TilePalette extends JPanel {
	private MapScreen screen;
	private int currentTile = 0;
	
	public TilePalette(MapScreen screen){
		this.screen = screen;
		setPreferredSize(new Dimension(4*Map.tilesize, (screen.getCurrentMap().getTileset().size()/4)*Map.tilesize));
		setDoubleBuffered(true);
		
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				setCurrentTile(e.getX(), e.getY());
			}
		});
	}
	
	public void paintComponent(Graphics g){
		int tileNumber = screen.getCurrentMap().getTileset().size();
		int row = 0;
		int col = 0;
		
		for(int i=0; i<tileNumber; i++){
			BufferedImage tile = screen.getCurrentMap().getTileset().get(i);
			g.drawImage(tile, col * Map.tilesize, row * Map.tilesize, this);
			
			if(i % 4 == 3){
				row++;
				col = 0;
			}else {
				col++;
			}
		}
		
	}
	
	public int getCurrentTile(){
		return this.currentTile;
	}
	
	public void setCurrentTile(int x, int y)
	{
		int col = x / Map.tilesize;
		int row = y / Map.tilesize;
		int currentTile = row *4 + col;
		if(currentTile < screen.getCurrentMap().getTileset().size())
		{
			this.currentTile = currentTile;
		}
	}
}
