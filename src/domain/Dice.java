/**
 * 
 */
package domain;
import java.util.Arrays;
import java.io.Serializable;
import java.util.Random;
/**
 * @author julia
 *
 */
public class Dice implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int[] modifieresNumber = {1,2,3,4};
	private int probabilityModifier;
	private int[] listModifier;
	
	
	public Dice() {
		
	}
	
	public Dice(int probabilituModifier) {
		setProbabilityModifier(probabilituModifier);
		listModifier = new int[100];
		setListModifier(this.probabilityModifier);
	}
	
	
	//Metodos
	public int playDice(){
		Random random = new Random();
		return random.nextInt(6) +1;
	}
	
	public int[] playDiceWithModifier(){
		Random random = new Random();
		int n = random.nextInt(6) +1;
		int modifier = listModifier[random.nextInt(100)];
		int[] returnn = {n,modifier};
		return returnn;
	}
	
	private int getModifierAletory() {
		Random random = new Random();
		return random.nextInt(this.modifieresNumber.length) +1;
	}
	
	private void setListModifier(int p) {
		for (int i = 0; i < 100; i++) {
			if(i<p) {
				listModifier[i] = getModifierAletory();
			}else {
				listModifier[i] = 0;
			}
		}
	}
	
	//Getters & setters
	public int getProbabilityModifier() {
		return probabilityModifier;
	}
	public void setProbabilityModifier(int probabilityModifier) {
		this.probabilityModifier = probabilityModifier;
	}

	
	
}
