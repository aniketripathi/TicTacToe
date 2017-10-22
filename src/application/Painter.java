package application;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Painter extends AnimationTimer{
	
	
	public static enum SYMBOL { 
		CROSS(1, Color.GREEN) ,
		CIRCLE(-1, Color.RED);
		
		public final int number;
		public final Color color;
		
		SYMBOL(int number, Color color){
			this.number = number;
			this.color = color;
		}
	}
	
	private GraphicsContext graphics;
	private static final double PADDING = 0.13;
	private static final double LINE_RATIO = 1 - 2 * PADDING;
	
	
	
	private static class Region {
		static double x, y, width, height, blockWidth, blockHeight;
	}
	
	
	public Painter(GraphicsContext graphics, double x, double y, double width, double height){
		
		this.graphics = graphics;
		Region.x = x;
		Region.y = y;
		Region.width = width;
		Region.height = height;
		Region.blockWidth = width/3;
		Region.blockHeight = height/3;
	}
	
	
	/**
	 * X axis change corresponds to columns
	 * Y axis change corresponds to rows 
	 * 
	 */
	
	public void drawGrid(){	
		// draw box
		graphics.setStroke(Color.BLACK);
		graphics.setLineWidth(10);
		graphics.strokeRect(Region.x, Region.y, Region.width, Region.height);
		
		//draw 4 lines
		graphics.setLineWidth(5); 
		graphics.strokeLine(Region.x + Region.blockWidth, Region.y, Region.x + Region.blockWidth, Region.y + Region.height);
		graphics.strokeLine(Region.x + 2 * Region.blockWidth, Region.y, Region.x + 2* Region.blockWidth, Region.y + Region.height);
		graphics.strokeLine(Region.x, Region.y + Region.blockHeight, Region.x + Region.width, Region.y + Region.blockHeight);
		graphics.strokeLine(Region.x , Region.y + 2 * Region.blockHeight, Region.x + Region.width, Region.y + 2 * Region.blockHeight);
	}
	
	
	public void drawCross(int blockNumber, Color crossColor){
		
		graphics.setStroke(crossColor);
		graphics.setLineWidth(15);
		
		double x = Region.x + (blockNumber % 10) * Region.blockWidth;
		double y = Region.y + (blockNumber/10) * Region.blockHeight;
		
		// A little below where the block starts;
		x += Region.blockWidth * PADDING;
		y += Region.blockHeight * PADDING;
		graphics.strokeLine(x, y, x + LINE_RATIO * Region.blockWidth, y + LINE_RATIO * Region.blockHeight);
		
		x += Region.blockWidth * LINE_RATIO;
		
		graphics.strokeLine(x, y, x - LINE_RATIO * Region.blockWidth, y + LINE_RATIO * Region.blockHeight);
		
	}
	
	
	public void drawCircle(int blockNumber, Color circleColor){
		
		graphics.setStroke(circleColor);
		graphics.setLineWidth(15);
		
		double x = Region.x + (blockNumber % 10) * Region.blockWidth;
		double y = Region.y + (blockNumber/10) * Region.blockHeight;
		
		x += (Region.blockWidth/2);
		y += (Region.blockHeight/2);
		
	    double diameter = (Region.blockWidth < Region.blockHeight)? Region.blockWidth : Region.blockHeight;
	    diameter = diameter * LINE_RATIO;
	    
	    graphics.strokeOval(x - diameter/2 ,y - diameter/2 , diameter , diameter);
		
	}
	
	public void drawWinningLine(int startBlockNumber, int endBlockNumber, Color winnerColor){
		
		double sx = Region.x + (startBlockNumber % 10) * Region.blockWidth, sy = Region.y + (startBlockNumber/10) * Region.blockHeight;
		double ex = Region.x + (endBlockNumber % 10 ) * Region.blockWidth, ey = Region.y + (endBlockNumber/10)* Region.blockHeight;
		
		sx += Region.blockWidth/2;
		sy += Region.blockHeight/2;
		ex += Region.blockWidth/2;
		ey += Region.blockHeight/2;
	
		graphics.setStroke(winnerColor);
		graphics.setLineWidth(7);
		graphics.strokeLine(sx, sy, ex, ey);
	}
	
	
	public void clear(double x, double y, double width, double height){
		graphics.clearRect(x, y, width, height);
	}
	
	
	
	
	public void clearBlock(int blockNumber){
		double x = Region.x + (blockNumber % 10) * Region.blockWidth;
		double y = Region.y + (blockNumber/10) * Region.blockHeight;
		
		x += Region.blockWidth * PADDING;
		y += Region.blockHeight * PADDING;
		
		graphics.clearRect(x, y, Region.blockWidth * LINE_RATIO, Region.blockHeight * LINE_RATIO);
		
	}



	@Override
	public void handle(long arg0) {
		
		
	}
	
	
}
