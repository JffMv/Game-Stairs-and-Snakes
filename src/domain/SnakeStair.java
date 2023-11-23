package domain;

import java.awt.Color;

public abstract class SnakeStair extends Box {
	
	protected int[] start;
	protected int[] end;
	protected boolean isSnake;
	protected boolean isUsed;
	private int nColumns;
	private int nRows;
	
	
	
	public SnakeStair(Color color, int[] coord, boolean isSnake,int nColumns, int nRows, int pos, boolean isActive) {
		super(color, coord, pos,0,isActive);
		this.nColumns = nColumns;
		this.nRows = nRows;
		setIsSnake(isSnake);
		setStart(coord);
		setEnd();
		
	}
	
	//Metodos abstractos
	
	
	
	//Getters & setters
	
	public int[] getStart() {
		return start;
	}

	public void setStart(int[] start) {
		this.start = start;
	}

	public int[] getEnd() {
		return end;
	}
	
	public void setEnd(int[] newEnd) {
		this.end = newEnd;
	}

	public void setEnd() {
		if(isSnake()) {
			int x =(int) Math.floor(Math.random()*(this.nRows-start[0]-1)) + this.start[0]+1;
			int y = (int )Math.floor(Math.random()*this.nColumns);
			int[] end_ = {x,y};
			this.end = end_;
		}else {
			int x = (int)Math.floor(Math.random()*(this.start[0]));
			int y = (int)Math.floor(Math.random()*this.nColumns);
			int[] end_ = {x,y};
			this.end = end_;
		}
	}
	
	

	public boolean isSnake() {
		return isSnake;
	}
	
	public void setIsSnake(boolean isSnakee) {
		this.isSnake = isSnakee;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	

}
