package domain;

import java.awt.Color;

public class NormalBox extends Box {
	
	public NormalBox(Color color, int[] coord, int pos) {
		super(color, coord, pos, 0,true);
		
	}

	

	@Override
	public String print() {
		return "NormalBox";
	}

}
