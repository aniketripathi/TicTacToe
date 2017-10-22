package application;


public abstract class Player {

    protected String name = "Player";
	protected final GameLogic gameLogic;
	public final Painter.SYMBOL symbol;
	
	public Player(Painter.SYMBOL symbol, GameLogic gameLogic){
		this.gameLogic = gameLogic;
		this.symbol = symbol;
	}
    

	
	public String getName(){ return name; }
	
	public void setName(String name){	this.name = name; }
	
	public boolean isWinner(){
		return (gameLogic.isGameFinished() && gameLogic.getWinner() == this);
		
	}
	
	
	@Override
	public String toString(){
		return name;
	}
	
}
