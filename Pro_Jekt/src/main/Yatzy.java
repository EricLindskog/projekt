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
			
			
			
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			AbstractButton button = new JButton();
			
			frame.setVisible(true);
			frame.setSize(JFrameX, JFrameY);
			frame.setResizable(false);
			frame.add(panel);
			
			panel.setBackground(Color.WHITE);
			panel.setSize(250, JFrameY);
			
			button.setVisible(true);
			button.setLocation(10,panel.getWidth()-10);
			panel.add(button);
			
			Player p1 = new Player();
			p1.printRemaningComb();
	}
}