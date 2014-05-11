package v_0_1;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;

public class MapView extends JPanel{
	
	private MapScreen screen;
	private JScrollPane scroll = new JScrollPane();
	private RepaintManager rm;

	public MapView(MapScreen screen){
		this.screen = screen;
		scroll.setViewportView(this);
		setDoubleBuffered(true);
		changeMap();
		rm = RepaintManager.currentManager(screen);
		
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				drawTile(e.getX(), e.getY());
			}			
		});
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) {
				drawTile(e.getX(), e.getY());
			}
		});
	}
	
	public void paintComponent(Graphics g){
		Rectangle r = g.getClipBounds();
		int startX = r.x;
		int startY = r.y;
		int endX = startX + r.width;
		int endY = startY + r.height;
		
		startX = startX / Map.tilesize;
		startY = startY / Map.tilesize;
		endX = endX / Map.tilesize;
		endY = endY / Map.tilesize;
		
		if(endX < screen.getCurrentMap().getMap().length){
			endX++;
		}
		if(endY < screen.getCurrentMap().getMap()[0].length){
			endY++;
		}
		
		for(int x=startX; x<endX; x++){
			for(int y=startY; y<endY; y++){
				BufferedImage tile = screen.getCurrentMap().getTileImage(x, y);
				g.drawImage(tile, x*Map.tilesize, y*Map.tilesize, this);
			}
		}
		
	}
	
	public void changeMap(){
		int dx = screen.getCurrentMap().getMap().length;
		int dy = screen.getCurrentMap().getMap()[0].length;
		setPreferredSize(new Dimension(dx*Map.tilesize,dy*Map.tilesize));
		scroll.setViewportView(this);
	}
	
	public JScrollPane getScroll(){
		return this.scroll;
	}
	
	public void drawTile(int x, int y){
		x = x / Map.tilesize;
		y = y / Map.tilesize;
		screen.getCurrentMap().getMap()[x][y] = screen.getTilePalette().getCurrentTile();
		
		Rectangle r = scroll.getViewport().getViewRect();
		int dx = this.scroll.getLocation().x + screen.getInsets().left - r.x;
		int dy = this.scroll.getLocation().y + screen.getInsets().top - r.y;
		rm.addDirtyRegion(screen , dx+x*Map.tilesize, dy+y*Map.tilesize, Map.tilesize+1, Map.tilesize+1); // +1 just to make sure that everything is repainted in the right way ;)
	}
}
