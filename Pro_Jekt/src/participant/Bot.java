package participant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import combinations.CombButton;
import combinations.Combinations;
import die.Die;
/**
 * A computer controlled participant.
 * @author eric
 *
 */
public class Bot extends Participant {

	public Combinations bottPlaying(ArrayList<CombButton> buttons){
		ArrayList<Combinations> Comb = new ArrayList<Combinations>();
		Comb=buttonsToCombs(buttons);
		
				if(Comb.contains(Combinations.yatzy)){
					return Combinations.yatzy;
				}
				if(Comb.contains(Combinations.house)){
					return Combinations.house;
				}
				if(Comb.contains(Combinations.bigstraight)){
					return Combinations.bigstraight;
				}
				if(Comb.contains(Combinations.smallstraight)){
					return Combinations.smallstraight;
				}
				if(Comb.contains(Combinations.fourofakind)){
					return Combinations.fourofakind;
				}
				if(Comb.contains(Combinations.threeofakind)){
					return Combinations.threeofakind;
				}
				if(Comb.contains(Combinations.twopair)){
					return Combinations.twopair;
				}
				if(Comb.contains(Combinations.onepair)){
					System.out.println("chance");
					return Combinations.onepair;
				}
				if(Comb.contains(Combinations.sixes)){
					return Combinations.sixes;
				}
				if(Comb.contains(Combinations.fives)){
					return Combinations.fives;
				}
				if(Comb.contains(Combinations.fours)){
					return Combinations.fours;
				}
				if(Comb.contains(Combinations.threes)){
					return Combinations.threes;
				}
				if(Comb.contains(Combinations.twos)){
					return Combinations.twos;
				}
				if(Comb.contains(Combinations.aces)){
					return Combinations.aces;
				}
				if(Comb.contains(Combinations.chance)){
					return Combinations.chance;
				}
				
				
		
		
		return null;
	}
	
	
	public void reRollingDices(ArrayList <Die> list) {
		for (int i = 0; i < list.size(); i++) {
				list.get(i).roll();
		}
		
	}
	public ArrayList<Combinations> buttonsToCombs (ArrayList<CombButton>Button){
		ArrayList<Combinations> combs = new ArrayList<Combinations>();
		for (int i = 0; i < Button.size(); i++) {
			combs.add(Button.get(i).getComb());
			
		}
		
		return combs;
		
	}
	//kommer aldrig returnera null
	public Combinations Discard(){
		Collection<Combinations> combs = new ArrayList<Combinations>();
		combs = this.getKeySet();
		if(!combs.contains(Combinations.chance))
			return Combinations.chance;
		if(!combs.contains(Combinations.aces))
			return Combinations.aces;
		if(!combs.contains(Combinations.twos))
			return Combinations.twos;
		if(!combs.contains(Combinations.threes))
			return Combinations.threes;
		if(!combs.contains(Combinations.fours))
			return Combinations.fours;
		if(!combs.contains(Combinations.fives))
			return Combinations.fives;
		if(!combs.contains(Combinations.sixes))
			return Combinations.sixes;
		if(!combs.contains(Combinations.onepair))
			return Combinations.onepair;
		if(!combs.contains(Combinations.twopair))
			return Combinations.twopair;
		if(!combs.contains(Combinations.threeofakind))
			return Combinations.threeofakind;
		if(!combs.contains(Combinations.fourofakind))
			return Combinations.fourofakind;
		if(!combs.contains(Combinations.smallstraight))
			return Combinations.smallstraight;
		if(!combs.contains(Combinations.bigstraight))
			return Combinations.bigstraight;
		if(!combs.contains(Combinations.house))
			return Combinations.house;
		if(!combs.contains(Combinations.yatzy))
			return Combinations.yatzy;
		
		return null;	
		
	}
	
	
	
	
	
	

}
