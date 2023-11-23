/**
 * 
 */
package domain;

import java.awt.Color;

/**
 * @author julia
 *
 */
public abstract class MachinePlayer extends Player {

	/**
	 * @param name
	 */
	public MachinePlayer(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	
	public abstract int answerQuestions();


}
