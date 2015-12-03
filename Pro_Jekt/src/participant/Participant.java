package participant;

import java.util.EnumSet;

import combinations.Combinations;
import die.Die;

public abstract class Participant {
	private int score;
	private EnumSet<Combinations> remaningCombs = EnumSet.allOf(Combinations.class);

	int getScore() {
		return score;
	}

	void setScore(int score) {
		this.score = score;
	}
	public void printRemaningComb(){
		for (Combinations combination : remaningCombs) {
			System.out.println(combination);
		}
			
	}
	public void removeComb(Combinations comb){
		remaningCombs.remove(comb);
	}
	public abstract Die[] dicesToReroll(Die[] dice);
}
