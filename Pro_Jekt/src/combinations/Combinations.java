package combinations;

public enum Combinations {
	aces,twos,threes,fours,fives,sixes,onepair,
	twopair,threeofakind,fourofakind,smallstraight,
	bigstraight,house,chance,yatzy;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
};


