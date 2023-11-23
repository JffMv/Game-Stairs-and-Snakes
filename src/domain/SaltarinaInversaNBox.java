package domain;

import java.awt.Color;
import java.util.Random;

public class SaltarinaInversaNBox extends Box {
	private int jump;
	public SaltarinaInversaNBox(Color color, int[] coord, int pos) {
		super(color, coord, pos,2,true);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "SaltarinaInversaNBox";
	}
	
	public int getJump() {
		return this.jump;
	}
	
	public void setJump(int jump) {
		Random r = new Random();
		
		this.jump = r.nextInt(jump)+1;
	}
}
