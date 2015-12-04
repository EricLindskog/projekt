package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import combinations.Combinations;
import die.Die;
import participant.Bot;
import participant.Participant;
import participant.Player;

public class Yatzy {
	ArrayList<Die>dice =new ArrayList<Die>();
	ArrayList<Participant>parts = new ArrayList<Participant>();
	public void run(int players,int bots){
		for (int i = 0; i < players; i++) {
			parts.add(new Player());
		}
		for (int i = 0; i < bots; i++) {
			parts.add(new Bot());
		}
		for (int i = 0; i < 5; i++) {
			dice.add(new Die());
		}
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
			
			for (int i = 0; i < dice.size(); i++) {
				dice.get(i).roll();
				System.out.println(dice.get(i).getValue());
			}
			System.out.println("fdadfsdfds");
			checkCombs();
	}
	public EnumSet<Combinations> checkCombs(){
		EnumSet<Combinations> availableCombs = EnumSet.allOf(Combinations.class);
		
		Collections.sort(dice);
		for (int i = 0; i < dice.size(); i++) {
			System.out.println(dice.get(i).getValue());
		}
		for (int i = 0; i < dice.size(); i++) {
			
		}
		
		
		EnumSet<Combinations> remaningCombs = EnumSet.complementOf(availableCombs);
		return remaningCombs;
	}
}