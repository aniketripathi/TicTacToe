package application;

import application.Painter.SYMBOL;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class UserPlayer extends Player implements EventHandler<MouseEvent>{

	private boolean eventListenerDisabled = false;
	private boolean moved = false;
	
	public UserPlayer(SYMBOL symbol, GameLogic gameLogic) {
		super(symbol, gameLogic);
		
	}

	
	public boolean isEventListenerDisabled() {
		
		return eventListenerDisabled;
	}

	
	public void setEventListenerDisabled(boolean eventListenerDisabled) {
		
		this.eventListenerDisabled = eventListenerDisabled;
	}

	
	
	
	public boolean isMoved(){
		return moved;
	}
	
	public void updateMoved(){
		moved = false;
	}
	
	
	@Override
	public void handle(MouseEvent event) {
		
		if(!eventListenerDisabled){
			double x = event.getX(), y = event.getY();
			double blockWidth = Main.CANVAS_WIDTH/3, blockHeight = Main.CANVAS_HEIGHT/3;
			int blockNumber =  ((int)(y/blockHeight)) * 10 + ((int)(x/blockWidth));
			
		if(gameLogic.getValueOfGameArea(blockNumber) == GameLogic.ARRAY_DEFAULT_VALUE){	
			gameLogic.move(this, blockNumber);
			moved = true;
			}
		}
	}
	
	
	
}
