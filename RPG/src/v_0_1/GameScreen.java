package v_0_1;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class GameScreen extends JFrame {
	
	public final static int MAX_GAME_SPEED = 33;
	
	private Point viewpoint = new Point(0,0);
	private Map map;
	private Controls controls;
	private Hero hero;
	private GameLoop loop;
	private GameView view;
	
	Thread drawThread;
	Thread loopThread;
	
	public GameScreen(){
		/* Set Frame Options */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(640,480));
		setTitle("Game");
		
		/* Add Controls */
		controls = new Controls();
		addKeyListener(controls);
		
		/* Load a Map */
		map = Map.load("scene1.pam");
		
		/* Load a Hero */
		hero = new Hero("Charset.png", this);
		
		/* Create a view and loop of the Game */
		view = new GameView(this);
		loop = new GameLoop(this);
 
		/* Add the View to the JFrame */
		add(view);
		
		/* Ready... */
		drawThread=new Thread(view);
		loopThread=new Thread(loop);
		
		/* Steady... */
		drawThread.start();
		loopThread.start();
		
		/* Go! */
		setVisible(true);
	}
	
	public Controls getControls(){
		return this.controls;
	}
	
	public Hero getHero(){
		return this.hero;
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public Point getViewpoint(){
		return this.viewpoint;
	}
}
