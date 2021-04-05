/*
 * PACMAN VERSION AS OF:
 * November 27, 2019
 * 7:54 PM
 * */

//package pacman;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.animation.AnimationTimer;

public class MenuStage {
	// add attributes here
	private Scene scene;
	private Group root;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image titleImg;
	private Image pacman1;
	private Image ghost1;
	private Image pacman2;
	private Image ghost2;
//	private GameTimer gametimer;
	
	public static final int WIDTH = 560;
	public static final int HEIGHT = 720;

	private Stage infoWindow ;

	public final Image bg = new Image("images/background.png", MenuStage.WIDTH, MenuStage.HEIGHT, false, false);
	public final Image instructionImage = new Image("images/instructions.png", 400, 520, false, false);
	public final Image aboutImage = new Image("images/about.png", 400, 520, false, false);

	public MenuStage() {
		// TODO Auto-generated constructor stub
		this.root = new Group();
		this.scene = new Scene(root, MenuStage.WIDTH, MenuStage.HEIGHT);
		this.canvas = new Canvas(MenuStage.WIDTH,MenuStage.HEIGHT);
		this.titleImg = new Image("images/title.png", 500, 300, false, false);
		this.pacman1 = new Image("images/pacman-animated.gif",30,30,false,false);
		this.ghost1 = new Image("images/inky_right.gif",30,30,false,false);
		this.pacman2 = new Image("images/pacmanleft.gif",30,30,false,false);
		this.ghost2 = new Image("images/vulnerable.gif",30,30,false,false);
		this.gc = canvas.getGraphicsContext2D();
//		this.gametimer = new GameTimer(gc, scene);
	}

	private void Gaming(){
		
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//pacman board
				GameStage game = new GameStage();
				game.setStage(stage);
			}
		});
		pause.play();
	}

	public void setStageComponents(Stage stage) {
		// TODO Auto-generated method stub
		//add widgets here
		this.stage = stage;
		
		Font pacmanFont = Font.loadFont(getClass().getResourceAsStream("images/Emulogic-zrEw.ttf"), 15);

		Button play = new Button("Play");
		play.setFont(pacmanFont);
		play.setLayoutX(240);
		play.setLayoutY(340);

		Button instructions = new Button("Instructions");
		instructions.setFont(pacmanFont);
		instructions.setLayoutX(180);
		instructions.setLayoutY(400);

		Button about = new Button("About");
		about.setFont(pacmanFont);
		about.setLayoutX(233);
		about.setLayoutY(440);


		Button exit = new Button("Exit");
		exit.setFont(pacmanFont);
		exit.setLayoutX(240);
		exit.setLayoutY(480);
		 
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
					handleButtonClick("exit");
			}
		});

		about.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
					handleButtonClick("about");
			}
		});

		instructions.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
					handleButtonClick("instructions");
			}
		});
		
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
					handleButtonClick("play");
			}
		});

		this.addComponents();
		//set stage elements here
		this.root.getChildren().add(canvas);
		this.root.getChildren().add(play);
		this.root.getChildren().add(instructions);
		this.root.getChildren().add(about);
		this.root.getChildren().add(exit);
		this.stage.setTitle("Pac-man");
		this.stage.setScene(this.scene);
		this.stage.show();

	}


	private void addComponents(){
		new AnimationTimer() //use AnimationTimer to continue the game loop
        {
            double frameX = 0;
            double frameY = 0;
            double xPac = 0;
            double yPac = 0;
            double xGhost = 0;
            double yGhost = 0;
            boolean reverse = false;
             
            public void handle(long currentNanoTime)
            {
                 
                if(frameX > 27) //goes to right
                {
                    frameX -= 0.25;
                    reverse = true;
                } 
                else if(frameX < 0) 
                {
                    frameX += 0.25;
                    reverse = false;
                }
                 
                xPac = frameX * 5 + 40;
                yPac = frameY * 5;
                xGhost = frameX * 5;
                yPac = frameY * 5;
                
                if(reverse == true)
                    frameX -= 0.05;
                else
                    frameX += 0.05;

                
        		gc.clearRect(0, 0, 400, 400);
        		
        		gc.drawImage(bg, 0, 0);
        		gc.drawImage(titleImg, 35, 15);
                 
                // Draw next image
        		if(reverse){
        			gc.drawImage(pacman2, xPac, 335);
        			gc.drawImage(ghost2, xGhost, 335);
        		}else {
        			gc.drawImage(pacman1, xPac, 335);
        			gc.drawImage(ghost1, xGhost, 335);
        		}
        		
            }
        }.start();
	}

	public void infoWindow(int num){
		infoWindow = new Stage();
		

		Button ok = new Button("OK");

		StackPane infoPane = new StackPane();

		infoPane.getChildren().add(ok);

		Scene scene = new Scene(infoPane, 400, 520);
	    ok.setFont(Font.font ("Consolas", 15));


		if(num == 0) {
			infoWindow.setTitle("INSTRUCTIONS");

		}else if(num == 1) {
			infoWindow.setTitle("ABOUT");
		}
	    infoWindow.setScene(scene);
	    ok.setOnAction(evt -> infoWindow.hide());
	    infoWindow.showAndWait();
	}

	
	private void handleButtonClick(String btnName){
		if(btnName.equals("play")){
			System.out.println("Play button clicked!");
			Gaming();
		}else if(btnName.equals("instructions")){
			System.out.println("Instructions button clicked!");
			infoWindow(0);
		}else if(btnName.equals("about")){
			System.out.println("About button clicked!");
			infoWindow(1);
		}else{
			System.out.println("End of program! Bye!");
			System.exit(0);
		}	
	}

	Scene getScene(){
		return this.scene;
	}

}
