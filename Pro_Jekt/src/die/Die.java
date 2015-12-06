package die;

import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Die extends JButton implements Comparable<Die> {
	private int value;
	Random roll = new Random();
	public void roll(){
		
		setValue(roll.nextInt(5)+1);
		String path = "/src/assets/Sprite-000"+this.getValue();
		try {
			Image img = ImageIO.read(getClass().getResource(path));
			this.setIcon(new ImageIcon(img));
		}catch (IOException ex) {
			
		}
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
