package blastRun;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {
	public static final int SIZE = 1300;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 250 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	
static Stage stage;
private static StageOne myGame;
private static StageTwo myGame2;
private static StageThree myGame3;
public MainMenu(Stage s){
	 stage= s;		
}
public Parent createContent() {
	
	Pane root = new Pane();
	
	root.setPrefSize(SIZE,750);
	
	Image is = new Image(getClass().getClassLoader().getResourceAsStream("Space2.jpg")); 
		ImageView img = new ImageView(is);
		img.setFitWidth(SIZE);;
		img.setFitHeight(750);;
		root.getChildren().add(img); 
	
	
	Title title = new Title("B L A S T R U N");
	title.setTranslateX(125);
	title.setTranslateY(200);
	
	MenuBox vbox = new MenuBox(
			new MenuItem("PLAY"),
			new MenuItem("STAGE ONE"),
			new MenuItem("STAGE TWO"),
			new MenuItem("STAGE THREE")
			);
	vbox.setTranslateX(200);
	vbox.setTranslateY(350);
	
	root.getChildren().addAll(title,vbox);
	return root;
}

private static class Title extends StackPane{
	public Title(String Name){
	Rectangle bg = new Rectangle(500,80);
	bg.setStroke(Color.WHITE);
	bg.setStrokeWidth(2);;
	bg.setFill(null);;
	
	Text text = new Text(Name);
	text.setFill(Color.WHITE);
	text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,65));
	setAlignment(Pos.CENTER);
	getChildren().addAll(bg,text);
	}
}

private static class MenuBox extends VBox{
	public MenuBox(MenuItem...items) {
		getChildren().add(createline());
		
		for(MenuItem item: items) {
			getChildren().addAll(item,createline());
		}
	}
	
	private Line createline() {
		Line sep = new Line();
		sep.setEndX(300);
		sep.setStroke(Color.DARKGREY);
		return sep;
	}
}

public static class MenuItem extends StackPane{
public MenuItem(String Name) {
		
	LinearGradient gradient = new LinearGradient(0d,1d,1d,0d, true, CycleMethod.NO_CYCLE, 
			new Stop[]{
					new Stop(0, Color.DARKVIOLET),
		            new Stop(0.33, Color.BLACK),
		            new Stop(0.67, Color.BLACK),
		            new Stop(1, Color.DARKVIOLET),
	});
Rectangle bg = new Rectangle(300,50);
bg.setOpacity(0.4);

Text text = new Text(Name);
text.setFill(Color.DARKGREY);
text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,22));

setAlignment(Pos.CENTER);
getChildren().addAll(bg,text);

setOnMouseEntered(event ->{
	bg.setFill(gradient);;
	text.setFill(Color.WHITE);
});

setOnMouseExited(event ->{
	bg.setFill(Color.BLACK);
	text.setFill(Color.DARKGREY);
});

setOnMousePressed(event ->{
	bg.setFill(Color.DARKVIOLET);
});

setOnMouseReleased(event ->{
	if(Name.equals("STAGE ONE") || Name.equals("PLAY")){
		myGame = new StageOne();
		Scene scene = myGame.init(800, 750, stage);
        stage.setScene(scene);
        stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> myGame.step(SECOND_DELAY));
Timeline animation = new Timeline();
animation.setCycleCount(Timeline.INDEFINITE);
animation.getKeyFrames().add(frame);
animation.play();
	}
	else if(Name.equals("STAGE TWO")){
		myGame2 = new StageTwo();
		Scene scene = myGame2.init(800, 750, stage);
        stage.setScene(scene);
        stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> myGame2.step(SECOND_DELAY));
Timeline animation = new Timeline();
animation.setCycleCount(Timeline.INDEFINITE);
animation.getKeyFrames().add(frame);
animation.play();
	}
	else if(Name.equals("STAGE THREE")){
		myGame3 = new StageThree();
		Scene scene = myGame3.init(800, 750, stage);
        stage.setScene(scene);
        stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> myGame3.step(SECOND_DELAY));
Timeline animation = new Timeline();
animation.setCycleCount(Timeline.INDEFINITE);
animation.getKeyFrames().add(frame);
animation.play();
	}
		
	bg.setFill(gradient);;
		});
	}
}

public void display() {
	stage.setTitle("BlastRun");
	stage.setScene(new Scene(createContent()));
}

}
