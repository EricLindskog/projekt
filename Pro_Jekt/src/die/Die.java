package die;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * A d6 die that extends JButton. And is selectable and rollable
 * @author eric
 *
 */
public class Die extends JButton implements Comparable<Die> {
	private int value;
	Random roll = new Random();
	//To roll or not to roll? That is the question...
	private boolean toRoll = true;
	
	/**
	 * "Rolls the die" sets the value of the die to a random number between 1 and 6
	 */
	public void roll(){
		this.setVisible(true);
		setValue(roll.nextInt(6)+1);
		
	}
	/**
	 * Gets the value of the die.
	 * 
	 * @return Returns the value of the die as an int
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Sets the value of the dice, only needed for testing purposes.
	 * @param value the value you want to set the dice to.
	 */
	public void setValue(int value) {
		if(value==0){
			this.setVisible(false);
		}
		this.value = value;
	}
	/**
	 * Compares a die to another using it's value
	 */
	public int compareTo(Die arg0) {
		
		return Integer.compare(this.getValue(), arg0.getValue());
	}
	/**
	 * 
	 * @return Returns the boolean that decides if the die should be rolled
	 */
	public boolean isToRoll() {
		return toRoll;
	}
	/**
	 * Sets if the die should be rolled or not
	 * @param toRoll true or false depending if you want it to reroll
	 */
	public void setToRoll(boolean toRoll) {
		this.toRoll = toRoll;
		setImage();
	}
	/**
	 * Sets the image depending on the value and reroll status of the die
	 */
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
