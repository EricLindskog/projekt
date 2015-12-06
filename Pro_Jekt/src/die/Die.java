package die;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Die extends JButton implements Comparable<Die> {
	private int value;
	Random roll = new Random();
	public void roll(){
		
		setValue(roll.nextInt(5)+1);
		String path = "/assets/die"+this.getValue()+".png";
		System.out.println(path);
		try {
			URL imageUrl = this.getClass().getResource(path);
			this.setIcon(new javax.swing.ImageIcon(imageUrl));
		}catch (Exception ex) {
			System.out.println("n‰‰");
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
