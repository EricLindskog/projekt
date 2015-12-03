package die;

import java.util.Random;

public class Die {
	private int value;
	Random roll = new Random();
	public void roll(){
		
		setValue(roll.nextInt(5)+1);
	}

	private int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}
}
