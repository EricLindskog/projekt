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
	//To roll or not to roll? That is the question...
	private boolean toRoll = true;
	public void roll(){
		this.setVisible(true);
		setValue(roll.nextInt(6)+1);
		
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if(value==0){
			this.setVisible(false);
		}
		this.value = value;
	}
	
	public int compareTo(Die arg0) {
		
		return Integer.compare(this.getValue(), arg0.getValue());
	}

	public boolean isToRoll() {
		return toRoll;
	}

	public void setToRoll(boolean toRoll) {
		this.toRoll = toRoll;
		setImage();
	}
	private void setImage(){
		if(this.toRoll){
			String path = "/assets/dieSel"+this.getValue()+".png";
			try {
				URL imageUrl = this.getClass().getResource(path);
				this.setIcon(new javax.swing.ImageIcon(imageUrl));
			}catch (Exception ex) {
			}
		}
		else{
			String path = "/assets/die"+this.getValue()+".png";
			try {
				URL imageUrl = this.getClass().getResource(path);
				this.setIcon(new javax.swing.ImageIcon(imageUrl));
			}catch (Exception ex) {
			}
		}
	}
}
