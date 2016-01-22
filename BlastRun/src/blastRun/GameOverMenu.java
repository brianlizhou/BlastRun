package blastRun;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameOverMenu {
	Stage stage;
	Scene gameOverScene;
	public GameOverMenu(Stage primaryStage) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		Group root = new Group();
		stage = primaryStage;
		gameOverScene = new Scene(root, 800, 750);
		root.getChildren().add(new Button());
		
		Rectangle background = new Rectangle(gameOverScene.getWidth(), gameOverScene.getHeight());
		background.setFill(Color.BLACK);
		root.getChildren().add(background);
			
		Label gameOverMessage = new Label("Game Over");
		gameOverMessage.setTextFill(Color.RED);
		gameOverMessage.setFont(new Font("Arial", 40));
		gameOverMessage.setTranslateX(300);
		gameOverMessage.setTranslateY(325);
		root.getChildren().add(gameOverMessage);
			
		//Create start game button
		Button mainMenuBut = new Button("RETURN TO MAIN MENU");
		mainMenuBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage newWindow = new Stage(); 
				 newWindow.setTitle("BlastRun");
				 newWindow.show();
				new MainMenu(newWindow).display();
				stage.close();
				
			
				
			}
		});
		mainMenuBut.setTranslateX(300);
		mainMenuBut.setTranslateY(425);
		root.getChildren().add(mainMenuBut);
	}
	
	/**
	 * Display GameOverMenu on stage.
	 */
	public void display() {
			stage.setScene(gameOverScene);
			
	}
}

