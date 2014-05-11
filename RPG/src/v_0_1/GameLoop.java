package v_0_1;

import java.awt.Point;

public class GameLoop implements Runnable {
	private GameScreen gameScreen;
	private Map map;
	private Controls controls;
	private Point viewpoint;
 
	public GameLoop ( GameScreen gS ){
		this.gameScreen = gS;
		this.map = gS.getMap();
		this.controls = gS.getControls();
		this.viewpoint = gS.getViewpoint();
	}
 
	public void run(){
		while(true){
 
			float start = System.currentTimeMillis();
 
			if(controls.isTop()){
				viewpoint.y--;
			}
			else if(controls.isDown()){
				viewpoint.y++;
			}
			else if(controls.isLeft()){
				viewpoint.x--;
			}
			else if(controls.isRight()){
				viewpoint.x++;
			}
			
			if(viewpoint.x > map.getWidth() - 20){
				viewpoint.x--;
			}
			else if(viewpoint.y > map.getHeight() - 15){
				viewpoint.y--;
			}
			else if(viewpoint.x < 0){
				viewpoint.x++;
			}
			else if(viewpoint.y < 0){
				viewpoint.y++;
			}		
			
			float run = System.currentTimeMillis() - start;
			if(GameScreen.MAX_GAME_SPEED > run)
			{
				try {
					Thread.sleep(GameScreen.MAX_GAME_SPEED - (int)run);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
