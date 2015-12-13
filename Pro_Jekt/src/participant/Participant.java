package participant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

import combinations.Combinations;
import die.Die;
/**
 * Abstract class for a participant in yatzy.
 * @author eric
 *
 */
public abstract class Participant {
	private int score;
	//Keeps track of the amount of points gained for a combination
	private HashMap <Combinations,Integer> map = new HashMap<Combinations,Integer>();

	//Getters and setters
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int score){
		this.score+=score;
	}
	
	public void setCombValue(Combinations key,int value){
		map.put(key, value);
	}
	public abstract Die[] dicesToReroll(Die[] dice);

	public HashMap <Combinations,Integer> getMap() {
		return map;
	}
	public Set<Combinations> getKeySet(){
		return map.keySet();
	}
	public int getCombPoints(Combinations key){
		return map.get(key);
	}

}
