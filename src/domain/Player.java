package domain;
import java.io.Serializable;
import java.awt.Color;

public abstract class Player implements Serializable{
	//Attributes
	private static final long serialVersionUID = 1L;
	private String name;
	private int nStairsTake;
	private int nSnakeTakes;
	private int nBoxesEspecialActive;
	private Piece piece;
	private boolean multiply;
	
	public Player(String name, Color color) {
		setMultiply(true);
		setName(name);
		setnStairsTake(0);
		setnSnakeTakes(0);
		setnBoxesEspecialActive(0);
		piece = new Piece(color);
	
	}
	
	//Metodos abstractos

	
	
	//Metodos
	public void print() {
		System.out.println("Pos piece " + piece.getPosition());
		System.out.println("# StairsTake" + nStairsTake);
		System.out.println("# SnakesTake " + nSnakeTakes);
		System.out.println("# SpecialBoxTake " + nBoxesEspecialActive);
	}
	
	public void endGame() {
		
	}
	
	
	
	
	//Getters & setters
	public int getPos() {
		return piece.getPosition();
	}
	
	public Color getColor() {
		return piece.getColor();
	}
	
	public int[] getInformation() {
		int[] info = {piece.getPosition(), nSnakeTakes, nStairsTake, nBoxesEspecialActive, getMaxPos()};
		return info;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getnStairsTake() {
		return nStairsTake;
	}
	
	public void setnStairsTake(int nSairsTake) {
		this.nStairsTake = nSairsTake;
	}


	public int getMaxPos() {
		return piece.getMaxPos();
	}

	

	public int getnBoxesEspecialActive() {
		return nBoxesEspecialActive;
	}

	public void setnBoxesEspecialActive(int nBoxesEspecialActive) {
		this.nBoxesEspecialActive = nBoxesEspecialActive;
	}

	public int getnSnakeTakes() {
		return nSnakeTakes;
	}

	public void setnSnakeTakes(int nSnakeTakes) {
		this.nSnakeTakes = nSnakeTakes;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece p) {
		this.piece = p;
	}

	public boolean isMultiply() {
		return this.multiply;
	}
	
	public void setMultiply(boolean boleano) {
		this.multiply = boleano;
	}
	
}
