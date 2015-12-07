package participant;

import java.util.EnumSet;

import combinations.Combinations;
import die.Die;

public abstract class Participant {
	private int score;
	private EnumSet<Combinations> remainingCombs = EnumSet.allOf(Combinations.class);

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int score){
		this.score+=score;
	}
	public void printRemaningComb(){
		for (Combinations combination : remainingCombs) {
			System.out.println(combination);
		}
			
	}
	public void removeComb(Combinations comb){
		remainingCombs.remove(comb);
	}
	public EnumSet<Combinations>getCombs(){
		return remainingCombs;
	}
	public abstract Die[] dicesToReroll(Die[] dice);
}
