package domain;

import java.awt.Color;

public class DualSnakeStair extends SnakeStair {

	public DualSnakeStair(Color color, int[] coord, boolean isSnake, int nColumns, int nRows, int pos, boolean isActive) {
		super(color, coord, isSnake, nColumns, nRows, pos,isActive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		
		return (isSnake())? "DualSnake" : "DualStair";
	}
	
	
	
//	public int[] getEn() {
//		int[] end_ = this.end;
//		if(isUsed) {
//			boolean changeIsSnake = !this.isSnake;
//			this.setIsSnake(changeIsSnake);
//			
//			setEnd(this.start);
//			setStart(end_);
//		}
//		
//		return end_;
//	}
	
	public int[] power() {
		this.setActive(false);
		return this.getEnd();
		
	}

}
