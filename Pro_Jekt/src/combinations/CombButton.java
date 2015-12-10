package combinations;

import java.util.ArrayList;

import javax.swing.JButton;

import die.Die;
/**
 * 
 * @author Eric
 *	A button for holding and calculating combinations in yatzy
 *	Instance it as an anonymous class
 */
public abstract class CombButton extends JButton{
	private int occurences=0;
	private boolean clickable = false;
	private Combinations Comb;
	private int points;
	public CombButton(Combinations Comb){
		this.setComb(Comb);
		this.setVisible(true);
	}
	public void reset(){
		this.setText(getComb().toString()+ " :n/a");
	}
	
	/**
	 * The method for calculating if the diceroll is eligible for the given combination
	 * The list NEEDS to be sorted from low to high
	 * 
	 * @param list the list of dice you want to calculate a combination for.
	 */
	public abstract void calculate(ArrayList <Die> list);
	public int getPoints() {
		return points;
	}
	public void setPoints(int value) {
		this.points = value;
	}
	public Combinations getComb() {
		return Comb;
	}
	void setComb(Combinations comb) {
		Comb = comb;
	}
	public boolean isClickable() {
		return clickable;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	public int getOccurences() {
		return occurences;
	}
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
}
