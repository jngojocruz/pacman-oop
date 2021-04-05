//package pacman;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
//		GameStage theGameStage = new GameStage();
//		theGameStage.setStage(stage);
		MenuStage menu = new MenuStage();
		menu.setStageComponents(stage);
	}

}
