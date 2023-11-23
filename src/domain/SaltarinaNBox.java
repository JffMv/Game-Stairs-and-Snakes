package domain;
import java.awt.Color;
import java.util.Random;
public class SaltarinaNBox extends Box {
	
	private int jump;
	
	public SaltarinaNBox(Color color, int[] coord, int pos) {
		super(color, coord, pos,1,true);
		determinateN();
	}

	private void determinateN() {
		int n = (int)(Math.floor(Math.random()*10+1));
		setJump(n);
	}

	

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "SaltarinaNBox";
	}

	/**
	 * @return the jump
	 */
	public int getJump() {
		return jump;
	}

	/**
	 * @param jump the jump to set
	 */
	public void setJump(int jump) {
		Random r = new Random();
		int intReal = r.nextInt(jump);
		this.jump = intReal  < 1 ? 1:intReal;
	}

}
