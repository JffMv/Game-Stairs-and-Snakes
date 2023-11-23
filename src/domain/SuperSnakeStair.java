package domain;

import java.awt.Color;

public class SuperSnakeStair extends SnakeStair {

	public SuperSnakeStair(Color color, int[] coord, boolean isSnake, int nColumns, int nRows, int pos) {
		super(color, coord, isSnake, nColumns, nRows, pos,true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "SuperSnakeStair";
	}

}
