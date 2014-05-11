package v_0_1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {
	
	private int lastDirection = 0; // 3=top,0=down,1=left,2=right
	private boolean movement = false;
	private boolean top = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	public Controls(){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				lastDirection = 3;
				top = true;
				movement = true;
				break;
			case KeyEvent.VK_DOWN:
				lastDirection = 0;
				down = true;
				movement = true;
				break;
			case KeyEvent.VK_LEFT:
				lastDirection = 1;
				left = true;
				movement = true;
				break;
			case KeyEvent.VK_RIGHT:
				lastDirection = 2;
				right = true;
				movement = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				top = false;
				movement = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				movement = false;
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				movement = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				movement = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	public boolean getMovement(){
		return this.movement;
	}
	
	public int getLastDirection(){
		return this.lastDirection;
	}

	public boolean isTop() {
		return top;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
