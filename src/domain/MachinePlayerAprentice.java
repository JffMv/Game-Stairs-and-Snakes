package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class MachinePlayerAprentice extends MachinePlayer {

	public MachinePlayerAprentice(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	public int playSelection(ArrayList<int[]> seletions) {
		int size = seletions.size();
		Random random = new Random();
		return random.nextInt(size) +1;
	}
	public int answerQuestions() {
		Random random = new Random();
		return random.nextInt(1) +1;
	}

	

}
