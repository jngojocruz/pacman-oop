//package pacman;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Pacman extends Sprite{
	private String name;
	private int lives;
	private boolean alive;
	private int score;
	
	public final static Image PACMAN_IMAGERIGHT = new Image("images/pacman-animated.gif",Pacman.PACMAN_WIDTH,Pacman.PACMAN_WIDTH,false,false);
	public final static Image PACMAN_IMAGEDOWN = new Image("images/pacmandown.gif",Pacman.PACMAN_WIDTH,Pacman.PACMAN_WIDTH,false,false);
	public final static Image PACMAN_IMAGELEFT = new Image("images/pacmanleft.gif",Pacman.PACMAN_WIDTH,Pacman.PACMAN_WIDTH,false,false);
	public final static Image PACMAN_IMAGEUP = new Image("images/pacmanup.gif",Pacman.PACMAN_WIDTH,Pacman.PACMAN_WIDTH,false,false);

	public final Image IMG200 = new Image("images/pt_200.png", 50, 50, false, false);
	public final Image IMG400 = new Image("images/pt_400.png", 50, 50, false, false);
	public final Image IMG800 = new Image("images/pt_800.png", 50, 50, false, false);
	public final Image IMG1600 = new Image("images/pt_1600.png", 50, 50, false, false);


	private final static int PACMAN_WIDTH = 20;

	public Pacman(String name, int x, int y){
		super(x,y);
		this.name = name;
		Random r = new Random();
		this.lives = 3;
		this.alive = true;
		
		this.loadImage(Pacman.PACMAN_IMAGERIGHT);
	}
	//getters

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	} 
	public String getName(){
		return this.name;
	}

	public void die(){
    	this.alive = false;
    }
	
	public void setScore(int score) {
		this.score += score;
	}
	
	public int getScore() {
		return this.score;
	}

	public void move() {
		System.out.println("dx" + this.dx + "dy" + this.dy);
		
    	
    	if(this.dx <0){//going left-----------------------------------------------------------------
    		this.moveLeft();
    	}

    	if(this.dy<0){//going up---------------------------------------------------------------------
    		this.moveUp();
    	}

    	if(this.dx>0){//going right--------------------------------------------------------------------
    		this.moveRight();
    	}
    	
    	if(this.dy>0){//going down-----------------------------------------------------------------------
    		this.moveDown();
    	}	
    	
    	if(this.x<0)this.x = GameStage.WINDOW_WIDTH;
    	if(this.x==GameStage.WINDOW_WIDTH-20 && this.dx==1)this.x = 0;
	}

	public void moveLeft(){
		if(GameTimer.cellIsNavigable(this.x+this.dx, this.y) && this.y%20==0){
			this.loadImage(PACMAN_IMAGELEFT);
    		this.x += this.dx;
    		if(this.dy>0){
    			if(GameTimer.cellIsNavigable(this.x, this.y + 20) && this.x%20==0){
		    		this.dx = 0;
	    		}
    		}else if(this.dy<0){//going up
	    		if(GameTimer.cellIsNavigable(this.x, this.y + this.dy ) && this.x%20==0){
		    		this.dx = 0;
	    		}
	    	}

	    	if((!GameTimer.cellIsNavigable(this.x+this.dx, this.y)) && this.x%20==0) this.dx =0;
		}
	}

	public void moveUp(){
		if(GameTimer.cellIsNavigable(this.x, this.y + this.dy ) && this.x%20==0){
			this.loadImage(PACMAN_IMAGEUP);
    		this.y += this.dy;
    		if(this.dx>0){//going right
	    		if(GameTimer.cellIsNavigable(this.x+20, this.y)  && this.y%20==0){
		    		this.dy = 0;	
	    		}
	    	}else if(this.dx <0){//going left
	    		if(GameTimer.cellIsNavigable(this.x+this.dx, this.y)  && this.y%20==0){
		    		this.dy = 0;
		    	}
		    }

	    	if((!GameTimer.cellIsNavigable(this.x, this.y-20))&& this.y%20==0) this.dy =0;

		}
	}

	public void moveRight(){
		if(GameTimer.cellIsNavigable(this.x+20, this.y) && this.y%20==0){
			this.loadImage(PACMAN_IMAGERIGHT);
    		this.x += this.dx;
    		if(this.dy>0){
    			if(GameTimer.cellIsNavigable(this.x, this.y + 20) && this.x%20==0){
		    		this.dx = 0;
	    		}
    		}else if(this.dy<0){//going up
	    		if(GameTimer.cellIsNavigable(this.x, this.y + this.dy ) && this.x%20==0){
		    		this.dx = 0;
	    		}
	    	}

	    	if((!GameTimer.cellIsNavigable(this.x+this.dx, this.y)) && this.x%20==0 ) this.dx =0;

    		
		}
	}

	public void moveDown(){
		if(GameTimer.cellIsNavigable(this.x, this.y + 20) && this.x%20==0){
			this.loadImage(PACMAN_IMAGEDOWN);
    		this.y += this.dy;
    		if(this.dx>0){//going right
	    		if(GameTimer.cellIsNavigable(this.x+20, this.y) && this.y%20==0){
		    		this.dy = 0;
		    		
		    		
	    		}
	    	}else if(this.dx <0){//going left
	    		if(GameTimer.cellIsNavigable(this.x+this.dx, this.y) && this.y%20==0){
		    		this.dy = 0;
		    		
		    	}
		    }

	    	if((!GameTimer.cellIsNavigable(this.x, this.y+20))&& this.y%20==0) this.dy =0;

		}
	}
	
	public void eatGhost(Ghost ghost, int succession, GraphicsContext gc){
		if(succession==1){
			gc.drawImage(this.IMG200, ghost.getX()-10, ghost.getY()-10);
			this.setScore(200);
		}else if(succession==2){
			gc.drawImage(this.IMG400, ghost.getX()-10, ghost.getY()-10);
			this.setScore(400);

		}else if(succession==3){
			gc.drawImage(this.IMG800, ghost.getX()-10, ghost.getY()-10);
			this.setScore(800);

		}else if(succession==4){
			gc.drawImage(this.IMG1600, ghost.getX()-10, ghost.getY()-10);
			this.setScore(1600);

		}
	}

	public void decreaseLife(){
		this.lives--;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public void aliveAgain() {
		this.alive = true;
	}

	public void subtractLife(){
		this.lives -=1;
	}
}
