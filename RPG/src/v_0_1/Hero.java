package v_0_1;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hero extends Sprite {

	BufferedImage charset;
	private Point position = new Point(9,8);
	private GameScreen screen;
	private Controls controls;
	
	private int charWidth = 32;
	private int charHeight = 48;
	
	float animation = 0.0f;
 
	public Hero(String charsetFilename, GameScreen screen)
	{
		this.screen = screen;
		this.controls = screen.getControls();
 
		try {
			charset = ImageIO.read(new File(charsetFilename));
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	@Override
	public BufferedImage getImage() {
		if(controls.getMovement())
			animation += 0.33;
		else 
			animation = 0.0f;
 
		if((int)animation == 4 ) 
			animation = 0.0f;
		
		return charset.getSubimage(((int)animation)*charWidth, controls.getLastDirection()*charHeight, charWidth, charHeight);
	}
	
	public Point getPosition(){
		return this.position;
	}

}
