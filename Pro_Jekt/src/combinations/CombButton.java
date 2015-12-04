package combinations;

import javax.swing.JButton;

public abstract class CombButton extends JButton{
	Combinations Comb;
	public CombButton(Combinations Comb){
		this.Comb = Comb;
	}
	public abstract void calculate();
}
