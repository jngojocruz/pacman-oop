//package pacman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameStage {
	public static final int WINDOW_HEIGHT = 740;
	public static final int WINDOW_WIDTH = 560;
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	//private MenuStage menu;
	
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.BLACK);	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.gc,this.scene);
		//this.menu = new MenuStage(this.gc, this.scene);
	}


	public void setStage(Stage stage) {
		this.stage = stage;	     
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Mini Ship Shooting Game");
		this.stage.setScene(this.scene);
		this.gametimer.start();
		this.stage.show();
	}
}
