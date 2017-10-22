package application;

import java.util.Arrays;

public class GameLogic {
	
    private int[][] gameArea;		// 0 default
	private Player winner;
	private boolean gameFinished;
    private int countMoves = 0;
    private Painter painter;
    public static final int ARRAY_DEFAULT_VALUE = 0;
   
   	public GameLogic(Painter painter){
   		gameArea = new int[3][3];
   		winner = null;
   		gameFinished = false;
   		this.painter = painter;
   		
   	}
   
   	
   	public boolean isGameFinished(){
   		return gameFinished;
   	}
   	
   	public Player getWinner(){
   		return winner;
   	}
   	
   
   	public static int getBlockNumber(int row, int col){
   		return (row * 10 + col);
   	}
   	
    public void move(Player player, int blockNumber){
    	
    	if(getValueOfGameArea(blockNumber) != ARRAY_DEFAULT_VALUE){
    		// do if player moves where it is not possible
    	}
    	else {
    		gameArea[blockNumber/10][blockNumber%10] = player.symbol.number;
    		++countMoves;
    		
    		for(int row[]: gameArea)	System.out.println(Arrays.toString(row));
    		System.out.println();
    		
    		if(player.symbol == Painter.SYMBOL.CROSS)
    				painter.drawCross(blockNumber, player.symbol.color);
    		
    		else 	painter.drawCircle(blockNumber, player.symbol.color);
    		
    		// check winner
    		boolean win = false;
    		int startBlock = 0, endBlock = 0;
    		boolean ldiag = true, rdiag = true;
    		for(int i = 0,j; i <= 2; i++){
    				boolean vertical = true, horizontal = true; 
    			for(j = 0; j <= 2; j++){
    				
    			if(gameArea[i][j] != player.symbol.number)	{
    				horizontal = false;
    				
    				if(ldiag &&  i == j)
    					ldiag = false;
    				
    				if(rdiag &&  (i+j) == 2)
    					rdiag = false;
    			}
    			
    			if(gameArea[j][i] != player.symbol.number)
    				vertical = false;
    		}	
    		
    		if(horizontal){
    				win = true;
    				startBlock = getBlockNumber(i, 0);
    				endBlock = getBlockNumber(i, 2);
    				break;
    			}
    		if(vertical){
    			win = true;
    			startBlock = getBlockNumber(0, i);
    			endBlock = getBlockNumber(2, i);
    			break;
    			}
    		}
    		
    	if(!win){
    		if(ldiag){
    				win = true;
    				startBlock = getBlockNumber(0,0);
    				endBlock = getBlockNumber(2,2);
    		}
    		
    		if(rdiag){
    			win = true;
    			startBlock = getBlockNumber(0,2);
    			endBlock = getBlockNumber(2,0);
    		}
    	}
    	
    	if(win){
    		gameFinished = true;
    		winner = player;
    		painter.drawWinningLine(startBlock, endBlock, winner.symbol.color);
    		
    		}
    	else if(countMoves == 9){
    		gameFinished = true;
    		}
    	}
    	
    }
    
    
    public int getValueOfGameArea(int blockNumber){
    	return gameArea[blockNumber/10][blockNumber%10];
    }
   
}
