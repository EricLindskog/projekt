package die;

import java.util.Random;

public class Die implements Comparable<Die> {
	private int value;
	Random roll = new Random();
	public void roll(){
		
		setValue(roll.nextInt(5)+1);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int compareTo(Die arg0) {
		
		return Integer.compare(getValue(), arg0.getValue());
	}
}
