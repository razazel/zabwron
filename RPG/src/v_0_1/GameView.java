package v_0_1;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameView extends JPanel implements Runnable {
	
	private GameScreen gameScreen;
	private Map map;
	private Hero hero;
	
	public GameView ( GameScreen gS ){
		this.gameScreen = gS;
		this.map = gS.getMap();
		this.hero = gS.getHero();
		
		setPreferredSize(new Dimension(640,480));
		setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g){
		drawMap(g);
		drawHero(g);
	}
	
	public void drawMap(Graphics g){
		int startX = gameScreen.getViewpoint().x;
		int startY = gameScreen.getViewpoint().y;
		int endX = startX + 20;
		int endY = startY + 15;
		for(int x = startX, rx = 0; x < endX; x++, rx++){
			for(int y = startY, ry = 0; y < endY ; y++, ry++){
				g.drawImage(map.getTileImage(x, y), rx*32, ry*32, this);
			}
		}
	}
	
	public void drawHero (Graphics g) {
		g.drawImage(hero.getImage(), hero.getPosition().x*32, hero.getPosition().y*32-16, this);
	}
	
	@Override
	public void run() {
		while(true){
			float start = System.currentTimeMillis();
			repaint();
			float run = System.currentTimeMillis() - start;
			if(GameScreen.MAX_GAME_SPEED > run){
				try {
					Thread.sleep(GameScreen.MAX_GAME_SPEED - (int)run);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
