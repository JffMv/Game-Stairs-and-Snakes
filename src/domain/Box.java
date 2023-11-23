/**
 * 
 */
package domain;

import java.awt.Color;
import java.io.Serializable;
/**
 * @author julia
 *
 */
public abstract class Box implements Serializable{
	
	//Attribute
	private static final long serialVersionUID = 1L;
	private int id;
	private int[] coordenate;
	private Color color;
	private int position;
	private boolean active;
	
	/**
	 * 
	 */
	public Box(Color color, int[] coord, int pos, int id, boolean isActive) {
		setActive(isActive);
		setColor(color);
		setCoordenate(coord);
		setPosition(pos);
		setId(id);
	}
	
	//Metodos abstractos
	public abstract String print();
	
	
	//Getters & setters
	public int[] getCoordenate() {
		return coordenate;
	}

	public void setCoordenate(int[] coordenate) {
		this.coordenate = coordenate;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
