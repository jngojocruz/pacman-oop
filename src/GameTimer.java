//package pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.lang.Exception;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

public class GameTimer extends AnimationTimer{
	private GraphicsContext gc;
	private Scene theScene;
	private Image background;
	final static int CELL = 20;
	private String bg = "src/images/level1.txt";
	private static Cell[][] cells;
	static int tileHeight;
	static int tileWidth;
	
	private Pacman pacman;
	private int succession;
	private int pacdotCount;
	private ArrayList<Image> livesArray;

	public final Image horizontal = new Image("images/horizontal.png", CELL, CELL, false, false);
	public final Image vertical = new Image("images/vertical.png", CELL, CELL, false, false);
	public final Image northeast = new Image("images/northeast.png", CELL, CELL, false, false);
	public final Image northwest = new Image("images/northwest.png", CELL, CELL, false, false);
	public final Image southeast = new Image("images/southeast.png", CELL, CELL, false, false);
	public final Image southwest = new Image("images/southwest.png", CELL, CELL, false, false);
	public final Image ghostdoor = new Image("images/ghostdoor.png", CELL, CELL, false, false);
	public final Image pacdot = new Image("images/pacdot.png", CELL, CELL, false, false);
	public final Image powerup = new Image("images/powerup.png", CELL, CELL, false, false);
	
	public final Image win = new Image("images/win.png", CELL, CELL*4, false, false);
	
	private long startSpawn;
	private long ghostAfraidTimer;
	//private boolean gameIsOver;

	private Ghost ghost1;
	private Ghost ghost2;
	private Ghost ghost3;
	private Ghost ghost4;

	Random r = new Random();
	
	GameTimer(GraphicsContext gc, Scene theScene){
		this.createCellArray(this.bg);
		this.gc = gc;
		this.theScene = theScene;
		this.background = new Image("images/black_background.jpg", GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, false, false);

		this.pacman = new Pacman("jenz",270,400);
		this.succession = 0;
		this.pacdotCount = 0;
		
		this.startSpawn = System.nanoTime();	//get current nanotime

		this.ghost1 = new Ghost("Clyde", 220, 320);
		this.ghost2 = new Ghost("Inky",  250, 360);
		this.ghost3 = new Ghost("Blinky",280, 320);
		this.ghost4 = new Ghost("Pinky", 310, 360);
		
		this.livesArray = new ArrayList<Image>();
		for(int i=0; i<3; i++) {
			Image pacmanLiveImage = new Image("images/pacman_0_1.png", CELL, CELL, false, false);
			this.livesArray.add(i, pacmanLiveImage);
		}
		
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentNanoTime) {
		
		if(this.pacdotCount==222){
			this.gc.drawImage(win, 100, 120, 370, 180);
			stop();
		}
		
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc.drawImage(this.background, 0, 0);
		this.initGameBoard(this.gc);
		
		this.pacman.move();
		this.pacman.render(this.gc);
		
		checkIfGhostCaughtPacman(ghost1);
		checkIfGhostCaughtPacman(ghost2);
		checkIfGhostCaughtPacman(ghost3);
		checkIfGhostCaughtPacman(ghost4);
		
		if(ghost1.getState() == "Afraid" || ghost2.getState() == "Afraid" || ghost3.getState() == "Afraid" || ghost4.getState() == "Afraid"){
			long timerSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			if(ghost1.collidesWith(pacman)){
				this.succession++;
				this.pacman.eatGhost(ghost1, this.succession, this.gc);
				ghost1.setState("Dead");
			} 
			if(ghost2.collidesWith(pacman)){
				this.succession++;
				this.pacman.eatGhost(ghost2, this.succession, this.gc);
				ghost2.setState("Dead");
			}
			if(ghost3.collidesWith(pacman)){
				this.succession++;
				this.pacman.eatGhost(ghost3, this.succession, this.gc);
				ghost3.setState("Dead");
			}
			if(ghost4.collidesWith(pacman)){
				this.succession++;
				this.pacman.eatGhost(ghost4, this.succession, this.gc);
				ghost4.setState("Dead");
			}
			if(timerSec - ghostAfraidTimer > 5){ // when pacman eats a power pellet, the ghosts become vulnerable for 15 seconds
				this.succession = 0;
				ghost1.setState("Patrol");
				ghost2.setState("Patrol");
				ghost3.setState("Patrol");
				ghost4.setState("Patrol");
			}
		}

		long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);	

		if(ghost1.isInsideGhostHouse()) ghost1.exitGhostHouse();
		if(ghost2.isInsideGhostHouse()) ghost2.exitGhostHouse();
		if(ghost3.isInsideGhostHouse()) ghost3.exitGhostHouse();
		if(ghost4.isInsideGhostHouse()) ghost4.exitGhostHouse();

		if(!ghost1.isInsideGhostHouse() && !ghost2.isInsideGhostHouse() && !ghost3.isInsideGhostHouse() && !ghost4.isInsideGhostHouse()){
			if((currentSec - startSec)%3 == 0){
			changeGhostDirection(this.ghost1);
			changeGhostDirection(this.ghost2);
			changeGhostDirection(this.ghost3);
			changeGhostDirection(this.ghost4);
			this.startSpawn = currentNanoTime;
			}
		}
		


		this.ghost1.move();
		this.ghost2.move();
		this.ghost3.move();
		this.ghost4.move();

		this.ghost1.render(this.gc);
		this.ghost2.render(this.gc);
		this.ghost3.render(this.gc);
		this.ghost4.render(this.gc);
		
		this.eatPacdot();
		
		
		this.printScore(this.gc);
		this.updateLives();
		
//		try {
//			this.checkCollision();
//		}catch(Exception e) {}
		
	}
	
	private void handleKeyPressEvent() {
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                movePacman(code);
			}
			
		});
    }
	
	
	private void movePacman(KeyCode ke) {
		if(ke==KeyCode.UP) this.pacman.setDY(-1);                 

		if(ke==KeyCode.LEFT) this.pacman.setDX(-1);

		if(ke==KeyCode.DOWN) this.pacman.setDY(1);
		
		if(ke==KeyCode.RIGHT) this.pacman.setDX(1);
		
		System.out.println(ke+" key pressed.");
   	}
	
	
	private void stopPacman(KeyCode ke){
		this.pacman.setDX(0);
		this.pacman.setDY(0);
	}
	
	
	private void createCellArray(String mapFile) {
        Scanner fileReader;
        ArrayList<String> lineList = new ArrayList<String>();

        try {
            fileReader = new Scanner(new File(mapFile));
            while (true) {
                String line = null;
                try {
                    line = fileReader.nextLine();
                } catch (Exception eof) {}

                if (line == null) {
                    break;
                }

                lineList.add(line);
            }

            tileHeight = lineList.size();
            tileWidth  = lineList.get(0).length();

            cells = new Cell[tileHeight][tileWidth];

            for (int row = 0; row < tileHeight; row++) {
                String line = lineList.get(row);
                for (int column = 0; column < tileWidth; column++) {
                    char type = line.charAt(column);
                    cells[row][column] = new Cell(column, row, type);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Maze map file not found");
        }
    }//endof createCellArray()
	
	
	public void initGameBoard(GraphicsContext gc) {
		for (int row = 0; row < tileHeight; row++) {
            for (int column = 0; column < tileWidth; column++) {
                this.initBoard(cells[row][column], gc);
            }
        }
	}//endof initGameBoard
	
	
	
	public void initBoard(Cell cell, GraphicsContext gc) {
	      switch (cell.getType()) {
	      case 'e' :
	        gc.drawImage(ghostdoor, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case 'h' :
	        gc.drawImage(horizontal, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case 'v' :
	        gc.drawImage(vertical, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case '1' :
	        gc.drawImage(northeast, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case '2' :
	        gc.drawImage(northwest, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case '3' :
	        gc.drawImage(southeast, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case '4' :
	        gc.drawImage(southwest, cell.getX() * CELL, cell.getY() * CELL);
	          break;

	      case 'o' :
	          break;    

	      case 'd' :
	    	  Pacdot pcdot = new Pacdot(cell.getX()*CELL, cell.getY()*CELL, "Pacdot");
	    	  pcdot.render(gc);
	    	  break;

	      case 'p' :
	          PowerPellet pwrup = new PowerPellet(cell.getX()*CELL, cell.getY()*CELL, "Powerup");
	          pwrup.render(gc);
	          break;

	      case 'f' :
	    	  if(pacman.getScore() >= 1000 && pacman.getScore() <= 3000) {
	    		  Fruit fruit = new Fruit(cell.getX()*CELL, cell.getY()*CELL, "Cherry");
	    		  fruit.render(gc);
	    	  }
	    	  else if(pacman.getScore() >= 5000 && pacman.getScore() <= 6500) {
	    		  Fruit fruit = new Fruit(cell.getX()*CELL, cell.getY()*CELL, "Strawberry");
	    		  fruit.render(gc);
	    	  }
	    	  else if(pacman.getScore() >= 9000 && pacman.getScore() <= 11000) {
	    		  Fruit fruit = new Fruit(cell.getX()*CELL, cell.getY()*CELL, "Orange");
	    		  fruit.render(gc);
	    	  }
	      	  break;

	      case 'x' :
	      case 'g' :
	      default :
	          break;
	      }
	  }//endof initBoard
	
	
	public void eatPacdot() {
		int col = this.pacman.getX() / CELL;
		int row = this.pacman.getY() / CELL;
		System.out.println("COL:" + row +"  X:"+this.pacman.getX() +"  ROW:" + col+"  Y:"+this.pacman.getY());
		if(cells[row][col].getType() == 'd') {
			this.pacdotCount++;
			System.out.println("PACDOT EATEN! Score:" + pacman.getScore());
			pacman.setScore(Pacdot.PACDOT_POINTS);
			cells[row][col].setType('o');
		}
		if(cells[row][col].getType() == 'p') {
			this.pacdotCount++;
			System.out.println("POWERPELLET EATEN! Score:" + pacman.getScore());
			pacman.setScore(PowerPellet.POWERPELLET_POINTS);
			cells[row][col].setType('o');
			
			this.ghost1.setState("Afraid");
			this.ghost2.setState("Afraid");
			this.ghost3.setState("Afraid");
			this.ghost4.setState("Afraid");
			this.ghostAfraidTimer = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			
		}
		if(cells[row][col].getType() == 'f') {
			System.out.println("FRUIT EATEN! Score:" + pacman.getScore());
			pacman.setScore(Fruit.FRUIT_POINTS);
			cells[row][col].setType('o');
		}
	}//endof eatPacdot
	
	
	public static boolean cellIsNavigable(int y, int x){
	    System.out.println("------");
	    // System.out.println("TYPE: " + cells[280/CELL][460/CELL].getType());
	     System.out.println("TYPE: " + cells[x/CELL][y/CELL].getType());
	    System.out.println("X: " + x/CELL + "   Y: " + y/CELL);

	    if(cells[x/CELL][y/CELL].getType() == 'o' ||
	       cells[x/CELL][y/CELL].getType() == 'd' ||
	       cells[x/CELL][y/CELL].getType() == 'p' ||
	       cells[x/CELL][y/CELL].getType() == 'f' ||
	       cells[x/CELL][y/CELL].getType() == 'e' || // ghost door
	       cells[x/CELL][y/CELL].getType() == 'g'){ // ghost house
	      return true;
	    }
	    return false;
	  }//endof cellIsNavigable
	
	
	public String getStringScore() {
		String strScore = "0000000" + pacman.getScore();
		strScore = strScore.substring(strScore.length() - 7, strScore.length());
		return strScore;
	}
	
	public void printScore(GraphicsContext gc) {
		Font pacmanFont = Font.loadFont(getClass().getResourceAsStream("images/Emulogic-zrEw.ttf"), 20);
		
		gc.setFont(pacmanFont);
		gc.fillText("Score", 11.25*CELL, 1*CELL);
		gc.setFill(Color.WHITE);
		gc.fillText(this.getStringScore(), 10.25*CELL, 2*CELL);
		gc.setFill(Color.WHITE);
		gc.fillText("Lives:", CELL, 36*CELL);
		gc.setFill(Color.WHITE);
		
		gc.drawImage(livesArray.get(0), CELL*7, 35*CELL);
		gc.drawImage(livesArray.get(1), CELL*8, 35*CELL);
		gc.drawImage(livesArray.get(2), CELL*9, 35*CELL);
	}
	
	public void updateLives() {
		if(this.pacman.getLives() != 3) {
			Image transparent = new Image("images/black_background.jpg", CELL, CELL, false, false);
			livesArray.remove(this.pacman.getLives());
			livesArray.add(this.pacman.getLives(), transparent);
		}
	}
	
	
	private void checkIfGhostCaughtPacman(Ghost ghost){
    	if(ghost.getState() == "Patrol" && ghost.collidesWith(pacman)){
    		System.out.println("Oh no! One of the ghosts caught Pacman!");
    		pacman.die();
    		pacman.subtractLife();

    		if(pacman.getLives()>0){
    			pacman.aliveAgain();
    			pacman.setX(270);
    			pacman.setY(400);

    			ghost1.setX(220);
    			ghost1.setY(320);
    			ghost2.setX(250);
    			ghost2.setY(360);
    			ghost3.setX(280);
    			ghost3.setY(320);
    			ghost4.setX(310);
    			ghost4.setY(360);

    		}else{
    			stop();
    			gc.drawImage(new Image("images/title.png"), 100, 120, 370, 180);
    			//gameIsOver = true;
    			new AnimationTimer() {
    				public void handle(long currentNanoTime) {
    					gc.drawImage(new Image("images/loser.gif"), 210, 320, 150, 60);
    				}
    			}.start();
    		}
    	}
    }


    private void changeGhostDirection(Ghost ghost){
		Random r = new Random();
		int direction = r.nextInt(4);
		if(direction == 0){
			ghost.setDY(-ghost.getSpeed()); // UP
		}else if(direction == 1){
			ghost.setDX(-ghost.getSpeed()); // LEFT
		}else if(direction == 2){
			ghost.setDY(ghost.getSpeed()); //DOWN
		}else if(direction == 3){
			ghost.setDX(ghost.getSpeed()); //RIGHT
		}
	}
}
