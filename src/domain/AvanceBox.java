package domain;

import java.awt.Color;

public class AvanceBox extends Box {

	public AvanceBox(Color color, int[] coord, int pos) {
		super(color, coord, pos,4,true);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public String print() {
		return "AvanceBox";
	}

}
