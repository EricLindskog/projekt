package participant;

import java.util.ArrayList;

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

	public Combinations bottPlaying(ArrayList<CombButton> buttons){
		for (int i = 0; i < buttons.size(); i++) {
			if(Combinations.yatzy==buttons.get(i).getComb()){
				if(buttons.get(i).isClickable()){
					return Combinations.yatzy;
				}
					
				
			}
		}
		
		return null;
	}
	
	
	public void reRollingDices(ArrayList <Die> list) {
		for (int i = 0; i < list.size(); i++) {
				list.get(i).roll();
		}
		
	}
	
	
	
	
	
	

}
