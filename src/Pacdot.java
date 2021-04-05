//package pacman;

import javafx.scene.image.Image;

public class Pacdot extends Sprite{
	private boolean alive;
	private String type;
	
	public final static int PACDOT_POINTS = 10;
	private final static int PACDOT_WIDTH = 20;
	public final static Image PACDOT_IMAGE = new Image("images/pacdot.png",Pacdot.PACDOT_WIDTH, Pacdot.PACDOT_WIDTH,false,false);

	public Pacdot(int x, int y, String type) {
		super(x,y);
		this.alive = true;
		this.type = type;
		this.loadImage(Pacdot.PACDOT_IMAGE);
	}
	
	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	}
	
	public void die(){
    	this.alive = false;
    }
	
	public String getType() {
		return this.type;
	}
	
//	public void setPoints() {
//		this.points += 10;
//	}
//	
//	public int getPoints() {
//		return this.points;
//	}

	
	
}
