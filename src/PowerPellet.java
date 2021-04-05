
import javafx.scene.image.Image;

public class PowerPellet extends Sprite{
	private boolean alive;
	private String type;
	private int points;
	
	public final static int POWERPELLET_POINTS = 50;
	private final static int POWERPELLET_WIDTH = 20;
	public final static Image POWERUP_IMAGE = new Image("images/powerup3.gif",PowerPellet.POWERPELLET_WIDTH, PowerPellet.POWERPELLET_WIDTH,false,false);

	public PowerPellet(int x, int y, String type) {
		super(x,y);
		this.alive = true;
		this.type = type;
		this.points = 0;
		this.loadImage(PowerPellet.POWERUP_IMAGE);
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
//		this.points += 50;
//	}
//	
//	public int getPoints() {
//		return this.points;
//	}

	
	
}
