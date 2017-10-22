package application;

import java.util.Random;

import application.Painter.SYMBOL;


public class CpuPlayer extends Player {
	
	private Random randomRow, randomCol;
	int lastBlocknumber = -1;
	
	public CpuPlayer(SYMBOL symbol, GameLogic gameLogic) {
		super(symbol, gameLogic);
		this.setName("CPU");
		randomRow = new Random(System.currentTimeMillis());
		randomCol = new Random(System.nanoTime());
	}
	
	
	public void move() {
		while(true){
			int blockNumber = GameLogic.getBlockNumber(randomRow.nextInt(3), randomCol.nextInt(3));
			if(gameLogic.getValueOfGameArea(blockNumber) != GameLogic.ARRAY_DEFAULT_VALUE)
				continue;
			gameLogic.move(this, blockNumber);
				break;
		}
	}
	
	
}
