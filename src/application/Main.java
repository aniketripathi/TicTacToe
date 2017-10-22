package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class Main extends Application implements Runnable{
	
	public static final String GAME_NAME = "Tic Tac Toe";
	public static final double CANVAS_WIDTH = 600;
	public static final double CANVAS_HEIGHT = 400;
	private boolean userTurn = false;
	private UserPlayer userPlayer;
	private GameLogic gameLogic;
	private CpuPlayer cpuPlayer;
	private Label label;
	
	@Override
	public void start(Stage primaryStage) {
		
			primaryStage.setTitle(GAME_NAME);
			primaryStage.setResizable(false);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
			Painter painter = new Painter(canvas.getGraphicsContext2D(), 0, 0, canvas.getWidth(), canvas.getHeight());
			
		
			
			gameLogic = new GameLogic(painter);
			cpuPlayer = new CpuPlayer(Painter.SYMBOL.CIRCLE, gameLogic);
			userPlayer = new UserPlayer(Painter.SYMBOL.CROSS, gameLogic);
			label = new Label("Your turn");
			label.setPrefSize(scene.getWidth(), 30);
			label.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 24));
			
			root.setCenter(canvas);
			root.setBottom(label);
			
			
			canvas.setOnMouseClicked((EventHandler<? super MouseEvent>) userPlayer);
			primaryStage.show();
			painter.drawGrid();
			
			
			Thread gameThread = new Thread(this);
				gameThread.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	



	@Override
	public void run() {
		while(!gameLogic.isGameFinished()){
			
			if(userTurn){
				userPlayer.setEventListenerDisabled(false);
				while(true){
					if(userPlayer.isMoved())
						break;
					 try {
						Thread.sleep(400);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				userTurn = false;
				userPlayer.updateMoved();
				userPlayer.setEventListenerDisabled(true);
				Platform.runLater(() ->	label.setText("Cpu Turn"));
			}
			
			else {
				cpuPlayer.move();
				userTurn = true;
				Platform.runLater(() ->	label.setText("Your turn"));
			}
		}
		
		if(userPlayer.isWinner())
			Platform.runLater(() ->	label.setText("You Win"));
		
		else if(cpuPlayer.isWinner())
			Platform.runLater(() ->	label.setText("You Lose"));
		
		else
			Platform.runLater(() ->	label.setText("Its a Tie"));
			
		
	}
}