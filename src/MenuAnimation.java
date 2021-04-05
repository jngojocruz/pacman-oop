//package firstsample;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MenuAnimation extends AnimationTimer{
	private GraphicsContext gc;
	private Scene scene;
	private long startSpawn;
	private Image title;
	private Image pacman;
	private Image ghost;
	private Image bg;
	private int titleXPos;
	private double pacmanXPos;
	private double pacmanYPos;
	private double ghostXPos;
	private double ghostYPos;
	private Rectangle2D pacmanBounds;
	
	MenuAnimation(GraphicsContext gc, Scene scene){
		this.gc = gc;
		this.scene = scene;
		this.startSpawn = System.nanoTime();	//get current nanotime
		this.title = new Image("images/title.png",350,200,false,false);
		this.pacman = new Image("images/pacman_0_1.png",50,50,false,false);
		this.ghost = new Image("images/ghost_0_1.png",50,50,false,false);
		this.bg = new Image("images/background.png", 450, 570, false, false);
		this.titleXPos = 52;
		this.pacmanXPos = 50;
		this.pacmanYPos = 200;
		this.ghostXPos = 0;
		this.ghostYPos = 200;
		//this.handleKeyPressEvent();  
		this.handleMouseEvent();
	}
	@Override
	public void handle(long currentNanoTime) {
		// TODO Auto-generated method stub
		this.addComponents();	
		
		/*
		 * TODO SAMPLE 1. D - Move the biscuit from left to right of the window screen
		 * */
		if(this.pacmanXPos <= GameStage.WINDOW_WIDTH-50){
			this.pacmanXPos += 0.25;	
		}
		this.movePacman(this.pacmanXPos, this.pacmanYPos);
		if(this.ghostXPos <= GameStage.WINDOW_WIDTH-50){
			this.ghostXPos += 0.25;	
		}
		this.moveGhost(this.ghostXPos, this.ghostYPos);

	}
	private void movePacman(double x, double y) {
        //draw the biscuit image at location x and y 
             gc.drawImage( this.pacman, x, y );
	}
	
	private void moveGhost(double x, double y) {
        //draw the biscuit image at location x and y 
             gc.drawImage( this.ghost, x, y );
	}

	private void addComponents(){
		this.gc.clearRect(0, 0, 400, 400);
        	//this.gc.drawImage(pacman, this.poroXPos, 150); 
			this.gc.drawImage(bg, 0, 0);
			this.gc.drawImage(title, this.titleXPos, 50);
		/*
		 * SAMPLE 1. G - Add biscuit bounds for the mouse event listener
		 * */
		//get size of the biscuit
             double width = this.pacman.getWidth();
             double height = this.pacman.getHeight();
             //add bound/rectangle to the biscuit
             this.pacmanBounds = new Rectangle2D(pacmanXPos, pacmanYPos, width, height);
             //System.out.println("BISCUIT BOUNDS: "+this.biscuitBounds.toString());
             		
	}
	
	private void handleMouseEvent() {
		this.scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				if ( pacmanBounds.contains( e.getX(), e.getY() ) ){
					System.out.println("Biscuit clicked!");
				}
			}
		});
    }
}

