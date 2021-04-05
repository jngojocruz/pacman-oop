//package pacman;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.Image;
import java.lang.Math;

public class Ghost extends Sprite{
	private String name;
	private String state;
	private int speed;
	private boolean alive;
	private int targetX;
	private int targetY;
	private Image ghostImage;
	public final static int GHOST_WIDTH = 20;
	public final static int GHOST_HEIGHT = 20;

	public Ghost(String name, int x, int y){
		super(x,y);
		this.name = name;
		this.targetX = 280;
		this.targetY = 280;
		this.state = "Patrol";
		if(name == "Inky"){
			this.ghostImage = new Image("images/red_ghost.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
		}else if(name == "Blinky"){
			this.ghostImage = new Image("images/blue_ghost.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
		}else if(name == "Pinky"){
			this.ghostImage = new Image("images/pink_ghost.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
		}else if(name == "Clyde"){
			this.ghostImage = new Image("images/orange_ghost.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
		}

		this.speed = 2;
		
		this.alive = true;

		this.loadImage(ghostImage);
	}
	//getters

	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
		setImage(state);
	}

	public void setImage(String ghostState){
		if(ghostState == "Afraid"){
			System.out.println("Ghost are vulnerable!");
			ghostImage = new Image("images/vulnerable.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			setSpeed(getSpeed()-1);
			loadImage(ghostImage);
		}else if(ghostState == "Patrol"){
			if(name == "Inky") ghostImage = new Image("images/inky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			else if(name == "Blinky") ghostImage = new Image("images/blinky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			else if(name == "Pinky") ghostImage = new Image("images/pinky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			else if(name == "Clyde") ghostImage = new Image("images/clyde_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			setSpeed(getSpeed()+1);
			loadImage(ghostImage);		
		}else if(ghostState == "Dead"){
			if(name == "Inky"){
				ghostImage = new Image("images/ghost_dead.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
				this.setX(250);
				this.setY(360);
			}else if(name == "Blinky"){
				ghostImage = new Image("images/ghost_dead.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
				this.setX(280);
				this.setY(320);
			}else if(name == "Pinky"){
				ghostImage = new Image("images/ghost_dead.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
				this.setX(310);
				this.setY(360);
			}else if(name == "Clyde"){
				ghostImage = new Image("images/ghost_dead.png", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
				this.setX(220);
				this.setY(320);
			} 
			loadImage(ghostImage);		
		}
	}
	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	} 

	public int getSpeed(){
		return this.speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}


	public String getName(){
		return this.name;
	}

	public void die(){
    	this.alive = false;
    }

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setTargetX(int x){
		this.targetX = x;
	}

	public void setTargetY(int y){
		this.targetY = y;
	}

	public int getTargetX(){
		return this.targetX;
	}

	public int getTargetY(){
		return this.targetY;
	}

	public boolean isInsideGhostHouse(){

		if(this.getX()>=220 && this.getX()<=310 && this.getY()<=360 && this.getY()>=320){
			System.out.println("INSIDE GHOST HOUSE");
			return true;
		}
		return false;
	}
	public void exitGhostHouse(){
		if(this.getX()<this.getTargetX()) this.setDX(this.getSpeed());
		else if(this.getX()>this.getTargetX()) this.setDX(-this.getSpeed());
		else if(this.getY()<this.getTargetY()) this.setDY(this.getSpeed());
		else if(this.getY()>this.getTargetY()) this.setDY(-this.getSpeed());

	}

	public void move() {
		System.out.println("dx" + this.dx + "dy" + this.dy);
    	
    	if(this.dx <0){//going left-----------------------------------------------------------------
    		this.moveLeft();
    		if(name == "Inky" && this.state =="Patrol"){
				ghostImage = new Image("images/inky_left.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Blinky" && this.state =="Patrol"){
				ghostImage = new Image("images/blinky_left.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Pinky" && this.state =="Patrol"){
				ghostImage = new Image("images/pinky_left.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Clyde" && this.state =="Patrol"){
				ghostImage = new Image("images/clyde_left.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			} 
			loadImage(ghostImage);	
    	}

    	if(this.dy<0){//going up---------------------------------------------------------------------
    		this.moveUp();
    		if(name == "Inky" && this.state =="Patrol"){
				ghostImage = new Image("images/inky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Blinky" && this.state =="Patrol"){
				ghostImage = new Image("images/blinky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Pinky" && this.state =="Patrol"){
				ghostImage = new Image("images/pinky_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Clyde" && this.state =="Patrol"){
				ghostImage = new Image("images/clyde_up.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			} 
			loadImage(ghostImage);	
    	}

    	if(this.dx>0){//going right--------------------------------------------------------------------
    		this.moveRight();
    		if(name == "Inky" && this.state =="Patrol"){
				ghostImage = new Image("images/inky_right.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Blinky" && this.state =="Patrol"){
				ghostImage = new Image("images/blinky_right.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Pinky" && this.state =="Patrol"){
				ghostImage = new Image("images/pinky_right.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Clyde" && this.state =="Patrol"){
				ghostImage = new Image("images/clyde_right.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			} 
			loadImage(ghostImage);	
    	}
    	
    	if(this.dy>0){//going down-----------------------------------------------------------------------
    		this.moveDown();
    		if(name == "Inky" && this.state =="Patrol"){
				ghostImage = new Image("images/inky_down.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Blinky" && this.state =="Patrol"){
				ghostImage = new Image("images/blinky_down.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Pinky" && this.state =="Patrol"){
				ghostImage = new Image("images/pinky_down.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			}else if(name == "Clyde" && this.state =="Patrol"){
				ghostImage = new Image("images/clyde_down.gif", Ghost.GHOST_WIDTH, Ghost.GHOST_HEIGHT, false, false);
			} 
			loadImage(ghostImage);	
    	}
    	
    	if(this.x<0)this.x = GameStage.WINDOW_WIDTH;
    	if(this.x==GameStage.WINDOW_WIDTH-20 && this.dx==1)this.x = 0;
	}

	public void moveLeft(){
		if(GameTimer.cellIsNavigable(this.x+this.dx, this.y) && this.y%20==0){
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


}
