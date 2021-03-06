package combinations;

import java.util.ArrayList;

import die.Die;
/**
 * Creates instances of combButtons for the needed combinations.
 * Each just holds the calculation for the given combination, and the number of occurences.
 * @author eric
 *
 */
public class CombButtons {
	static ArrayList <CombButton> list = new ArrayList<CombButton>();
	static CombButton aces = new CombButton(Combinations.aces){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==1){
					score++;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton twos = new CombButton(Combinations.twos){

		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==2){
					score+=2;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton threes = new CombButton(Combinations.threes){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==3){
					score+=3;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton fours = new CombButton(Combinations.fours){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==4){
					score+=4;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton fives = new CombButton(Combinations.fives){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==5){
					score+=5;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton sixes = new CombButton(Combinations.sixes){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(Die die : list){
				if(die.getValue()==6){
					score+=6;
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton onepair = new CombButton(Combinations.onepair){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(int i=0; i<list.size();i++){
				if(i<list.size()-1){
					if(list.get(i).getValue()==list.get(i+1).getValue()){
						score = list.get(i).getValue()*2;
						if(score>0)this.setOccurences(this.getOccurences()+1);
					}
				}
			}
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);

		}
		
	};
	static CombButton twopair = new CombButton(Combinations.twopair){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			int count = 0;
			for(int i=0; i<list.size();i++){
				if(i<list.size()-1){
					if(list.get(i).getValue()==list.get(i+1).getValue()){
						score += list.get(i).getValue()*2;
						i+=1;
						count++;
					}
				}
			}
			if(count!=2){
				score=0;
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton threeOfAKind = new CombButton(Combinations.threeofakind){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(int i=0; i<list.size();i++){
				if(i<list.size()-2){
					if(list.get(i).getValue()==list.get(i+2).getValue()){
						score = list.get(i).getValue()*3;
					}
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton fourOfAKind = new CombButton(Combinations.fourofakind){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for(int i=0; i<list.size();i++){
				if(i<list.size()-3){
					if(list.get(i).getValue()==list.get(i+3).getValue()){
						score = list.get(i).getValue()*4;
					}
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
			
		}
		
	};
	static CombButton smallStraight = new CombButton(Combinations.smallstraight){
		@Override
		public void calculate(ArrayList<Die> list) {
			int counter=0;
			int score=0;
			for(int i=0; i<list.size();i++){
				if(list.get(i).getValue()==i+1){
					counter++;
				}
			}
			if(counter==list.size()){
				for(int i=0; i<list.size();i++){
					score+=list.get(i).getValue();
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton bigStraight = new CombButton(Combinations.bigstraight){
		@Override
		public void calculate(ArrayList<Die> list) {
			int counter=0;
			int score=0;
			for(int i=0; i<list.size();i++){
				if(list.get(i).getValue()==i+2){
					counter++;
				}
			}
			if(counter==list.size()){
				for(int i=0; i<list.size();i++){
					score+=list.get(i).getValue();
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton  house= new CombButton(Combinations.house){
		@Override
		public void calculate(ArrayList<Die> list) {
			boolean isHouse = false;
			int score = 0;
			if(list.get(0).getValue()==list.get(2).getValue()&&list.get(3).getValue()==list.get(4).getValue()){
				isHouse=true;
			}
			if(list.get(0).getValue()==list.get(1).getValue()&&list.get(2).getValue()==list.get(4).getValue()){
				isHouse=true;
			}
			if(isHouse){
				for (int i = 0; i <list.size(); i++) {
					score+=list.get(i).getValue();
				}
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton chance = new CombButton(Combinations.chance){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score = 0;
			for (int i = 0; i <list.size(); i++) {
				score+=list.get(i).getValue();
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
		
	};
	static CombButton yatzy = new CombButton(Combinations.yatzy){
		@Override
		public void calculate(ArrayList<Die> list) {
			int score=0;
			if(list.get(0).getValue()==list.get(4).getValue()){
				score=50;
			}
			if(score>0)this.setOccurences(this.getOccurences()+1);
			this.setPoints(score);
			this.setText(this.getComb().toString()+" : "+score);
		}
	};
	/**
	 * Adds all the combination buttons to an array and returns it for ease of use
	 * @return Returns an array with all the combination buttons
	 */
	public static ArrayList<CombButton> getButtons(){
		list.add(aces);
		list.add(twos);
		list.add(threes);
		list.add(fours);
		list.add(fives);
		list.add(sixes);
		list.add(onepair);
		list.add(twopair);
		list.add(threeOfAKind);
		list.add(fourOfAKind);
		list.add(smallStraight);
		list.add(bigStraight);
		list.add(house);
		list.add(chance);
		list.add(yatzy);
		
		return list;
	}
}
