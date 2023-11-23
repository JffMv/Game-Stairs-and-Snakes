package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class MachinePlayerBegginer extends MachinePlayer {

	public MachinePlayerBegginer(String name, Color color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	public int playSelection(ArrayList<int[]> seletions) {
		if (seletions.size()==2)return seletions.get(1)[0];
		int max =0;
		for (int[] i : seletions) {
			if(i[0]>max) {
				max = i[0];
			}
		}
		return max;
	}
	public int answerQuestions() {
		return 1;
	}

	

}
