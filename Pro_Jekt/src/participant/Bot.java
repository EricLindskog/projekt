package participant;

import java.util.ArrayList;
import java.util.Collections;

import combinations.CombButton;
import combinations.Combinations;
import die.Die;
/**
 * A computer controlled participant.
 * @author eric
 *
 */
public class Bot extends Participant {

	private Die[] list;

	public Combinations bottPlaying(ArrayList<Die> list, ArrayList<CombButton> buttons){
		
		if(yatzyTrue(list) && !(this.getKeySet().contains(Combinations.yatzy))){
			return Combinations.yatzy;
		}
		if(houseTrue(list) && !(this.getKeySet().contains(Combinations.yatzy))){
			return Combinations.yatzy;
		}
			
		
		reRollingDices(list);
		
		
		return null;
	}
	
	
	public void reRollingDices(ArrayList <Die> list) {
		for (int i = 0; i < list.size(); i++) {
				list.get(i).roll();
		}
	}
	public boolean yatzyTrue (ArrayList <Die> list){
		Collections.sort(list);
		if(list.get(0).getValue()==list.get(4).getValue())
		return true;
		else
		return false;
	}
	public boolean houseTrue (ArrayList <Die> list){
		Collections.sort(list);
		if(list.get(0).getValue()==list.get(2).getValue()&&list.get(3).getValue()==list.get(4).getValue()){
			return true;
		}
		if(list.get(0).getValue()==list.get(1).getValue()&&list.get(2).getValue()==list.get(4).getValue()){
			return true;
		}
		
		return false;
	}
	public boolean smallStraightTrue (ArrayList <Die> list){
		int counter=0;
		for(int i=0; i<list.size();i++){
			if(list.get(i).getValue()==i+1){
				counter++;
			}
		}
		if(counter==list.size()){
			return true;
		}
		return false;
	}
	
	public boolean largeStraightTrue (ArrayList <Die> list){
		int counter=0;
		for(int i=0; i<list.size();i++){
			if(list.get(i).getValue()==i+2){
				counter++;
			}
		}
		if(counter==list.size())
			return true;
			
		else
		return false;
	}
	
	
	public boolean fourOfAKindTrue (ArrayList <Die> list){
		if(list.get(0)==list.get(4))
			return true;
		if(list.get(1)==list.get(5))
			return true;
		else
			return false;
	}
	
	public boolean threeOfAKindTrue (ArrayList <Die> list){
		if(list.get(0)==list.get(3))
			return true;
		if(list.get(1)==list.get(4))
			return true;
		if(list.get(2)==list.get(5))
			return true;
		else
			return false;
	}
	
	public boolean twoPairTrue (ArrayList <Die> list){
		
	}
	
	public boolean onePairTrue (ArrayList <Die> list){
		
	}
	
	
	
	
	
	
	

}
