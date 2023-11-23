package domain;

import java.awt.Color;

public class MortalBox extends Box {

	public MortalBox(Color color, int[] coord, int pos) {
		super(color, coord, pos,3,true);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String print() {
		return "MortalBox";
	}

}
