package domain;
import java.io.Serializable;
import java.awt.Color;

public class Piece implements Serializable{
	//Attributes
	private static final long serialVersionUID = 1L;
	private Color color;
	private int position;
	private int maxPos;
	
	//Constructor
	public Piece(Color color) {
		setColor(color);
		this.maxPos = 0;
		setPosition(0);
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public int getPosition() {
		return position;
	}
	
	public int getMaxPos() {
		return this.maxPos;
	}


	public void setPosition(int position) {
		this.maxPos = (position>this.position)?position :this.position;
		this.position = position;
		
	}

}
