package blastRun;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


class StageTwo {
    public static final String TITLE = "BlastRun Stage One";
    public static final int KEY_INPUT_SPEED = 5;
    private static final double GROWTH_RATE = 1.1;
    private static final int BOUNCER_SPEED = 30;
    
    private Scene myScene;
    private ImageView rocket;
    private ImageView obstacle;
    private ImageView WinStar;
    private Rectangle myTopBlock;
    private Rectangle myBottomBlock;
    boolean finished = false;
    private ArrayList<ImageView> obstacles = new ArrayList<>();
    private int stageWidth;
    private int stageHeight;
    
    Group root = new Group();
    Stage stage;
    /**
     * Returns name of the game.
     */
    public String getTitle () {
        return TITLE;
    }
    public void createObstacle(Image obs1,double x, double y){
    	ImageView asteroid = new ImageView(obs1);
    	asteroid.setX(x);
    	asteroid.setY(y);
    	root.getChildren().add(asteroid);
    	obstacles.add(asteroid);
    }
    /**
     * Create the game's scene
     */
    public Scene init (int width, int height, Stage s) {
    	 stageWidth = width;
    	 stageHeight = height;
    	 stage = s;
 
        myScene = new Scene(root, width, height, Color.WHITE);
        Image is = new Image(getClass().getClassLoader().getResourceAsStream("space.jpg")); 
		ImageView img = new ImageView(is);
		img.setFitWidth(width);
		img.setFitHeight(height);
		root.getChildren().add(img);
		
		Image winIcon = new Image(getClass().getClassLoader().getResourceAsStream("WinIcon.png"));
		WinStar = new ImageView(winIcon);		
		WinStar.setX(width/2 - 25);
		WinStar.setY(0);
		WinStar.setFitWidth(75);
		WinStar.setFitHeight(75);
        root.getChildren().add(WinStar);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("rocket.png"));
        rocket = new ImageView(image);
        rocket.setX(width/2 - 100);
        rocket.setY(height-100);
        rocket.setFitWidth(100);
        rocket.setFitHeight(100);
        root.getChildren().add(rocket);
        
        Image obs1 = new Image(getClass().getClassLoader().getResourceAsStream("asteroid.jpg"));
        
        //Obstacle Layer 1 (From bottom)
        for(int i = 50; i<1000; i+=200){
        createObstacle(obs1,i,550);}
        
        //Obstacle Layer 2
        for(int i = 100; i<100000; i+=200){
            createObstacle(obs1,i,350);}
        
      //Obstacle Layer 3
        for(int i = -100000; i<1000; i+=250){
            createObstacle(obs1,i,150);}
    
               
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    /**
     * Change properties of shapes to animate them
     * 
     * Note, there are more sophisticated ways to animate shapes,
     * but these simple ways work too.
     */
    public void step (double elapsedTime) {
    	if(rocket.getBoundsInLocal().intersects(WinStar.getBoundsInLocal())){
    		win();
    	}
    	 for(ImageView o: obstacles){
    	    	if(o.getY() == 150){  	
    	    		o.setX(o.getX() + 5*elapsedTime);	    	
    	    }
    	    	if(o.getY() == 350)
    	    	{  	
    	    		o.setX(o.getX() - 5*elapsedTime);	    	
    	    }
    if(rocket.getBoundsInLocal().intersects(o.getBoundsInLocal())) {
            gameover();  
           }}

    
    }
 
    public void win(){
    	Rectangle rect = new Rectangle(stageWidth, stageHeight);
		rect.setFill(Color.YELLOW);
		root.getChildren().add(rect);
		rect.setOpacity(0);
		FadeTransition fade = new FadeTransition(Duration.millis(3500), rect);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setAutoReverse(false);
		fade.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				new WinMenu(stage).display();				
			}
		});
		fade.play();
    }
    
    public void gameover(){
    	Rectangle rect = new Rectangle(stageWidth, stageHeight);
		rect.setFill(Color.BLACK);
		root.getChildren().add(rect);
		rect.setOpacity(0);
		FadeTransition fade = new FadeTransition(Duration.millis(3500), rect);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setAutoReverse(false);
		fade.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				new GameOverMenu(stage).display();				
			}
		});
		fade.play();
			}
		
		

    

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        switch (code) {
            case RIGHT:
                rocket.setX(rocket.getX() + KEY_INPUT_SPEED);
                break;
            case LEFT:
            	rocket.setX(rocket.getX() - KEY_INPUT_SPEED);
                break;
            case UP:
            	rocket.setY(rocket.getY() - KEY_INPUT_SPEED);
                break;
            case DOWN:
            	rocket.setY(rocket.getY() + KEY_INPUT_SPEED);
                break;
            default:
                // do nothing
        }
    }

}
