import javafx.scene.image.Image;

public class Fruit extends Sprite{
	private boolean alive;
	private String type;
	private int points;
	
	public final static int FRUIT_POINTS = 100;
	private final static int FRUIT_WIDTH = 20;
	public final static Image FRUIT_IMAGE01 = new Image("images/cherry.png",Fruit.FRUIT_WIDTH, Fruit.FRUIT_WIDTH,false,false);
	public final static Image FRUIT_IMAGE02 = new Image("images/fruit02.png",Fruit.FRUIT_WIDTH, Fruit.FRUIT_WIDTH,false,false);
	public final static Image FRUIT_IMAGE03 = new Image("images/fruit03.png",Fruit.FRUIT_WIDTH, Fruit.FRUIT_WIDTH,false,false);


	public Fruit(int x, int y, String type) {
		super(x,y);
		this.type = type;
		this.points = 0;
		if(type == "Cherry") this.loadImage(Fruit.FRUIT_IMAGE01);
		else if(type == "Strawberry") this.loadImage(Fruit.FRUIT_IMAGE02);
		else if(type == "Orange") this.loadImage(Fruit.FRUIT_IMAGE01);
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
//		this.points += 100;
//	}
//	
//	public int getPoints() {
//		return this.points;
//	}

	
	
}
