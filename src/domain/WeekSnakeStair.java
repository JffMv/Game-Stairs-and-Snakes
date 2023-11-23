package domain;

import java.awt.Color;

public class WeekSnakeStair extends SnakeStair {

	public WeekSnakeStair(Color color, int[] coord, boolean isSnake, int nColumns, int nRows, int pos) {
		super(color, coord, isSnake, nColumns, nRows, pos,true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		
		return (isSnake())? "WeakSnake" : "WeakStair";
	}
	
	
}
