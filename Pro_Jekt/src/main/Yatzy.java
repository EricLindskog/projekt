package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import participant.Player;

public class Yatzy {
	public void run(int players,int cpu){
		
		startup();
		
		
	}
	public void startup(){
			JFrame frame = new JFrame();
			int JFrameX=700;
			int JFrameY=500;
			
			
			
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			
			AbstractButton button = new JButton();
			
			frame.setVisible(true);
			frame.setSize(JFrameX, JFrameY);
			frame.setResizable(false);
			frame.setLayout(new FlowLayout());
			
			panel1.setLocation(0, 0);
			panel1.setBackground(Color.WHITE);
			panel1.setSize(350, 700);
			panel1.setLayout(new FlowLayout());
			panel1.setAlignmentX(FlowLayout.RIGHT);
			
			panel2.setLocation(350, 0);
			panel2.setBackground(Color.WHITE);
			panel2.setSize(350, 700);
			panel2.setLayout(new FlowLayout());
			panel2.setAlignmentX(FlowLayout.RIGHT);
			
			button.setVisible(true);
			button.setLocation(50,50);
			
			frame.add(panel1);
			frame.add(panel2);
			panel1.add(button);
			
			Player p1 = new Player();
			p1.printRemaningComb();
	}
}