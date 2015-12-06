package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import combinations.CombButton;
import combinations.CombButtons;
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
			Dimension P1P2Dim = new Dimension(350,500);
			Dimension FrameDim = new Dimension(700,500);
			
			ArrayList <CombButton>combs = new ArrayList<CombButton>();
			combs = CombButtons.getButtons();
			
			AbstractButton button = new JButton();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setSize(FrameDim);
			frame.setResizable(false);
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			
			
			
			panel1.setLocation(0, 0);
			panel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			panel1.setBackground(Color.CYAN);
			panel1.setPreferredSize(P1P2Dim);
			
			panel2.setLocation(350, 0);
			panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			panel2.setBackground(Color.WHITE);
			panel2.setPreferredSize(P1P2Dim);
			
			button.setVisible(true);
			button.setLocation(50,50);
			
			frame.add(panel1);
			frame.add(panel2);
			for(int i =0; i<combs.size(); i++){
				combs.get(i).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						/*
						 * The general action when a combButton is pressed
						 * Maybe it should call another function to do the addition of
						 * score and bonusscore?
						 */
					}
				});
				combs.get(i).setLocation(50, 50*(i+1));
				combs.get(i).setVisible(true);
				panel1.add(combs.get(i));
			}
			panel1.add(button);
			
			for (int i = 0; i < dice.size(); i++) {
				dice.get(i).roll();
				dice.get(i).setValue(1);
				System.out.println(dice.get(i).getValue());
			}
			
			//test stuff
			dice.get(0).setValue(6);
			dice.get(1).setValue(4);
			dice.get(2).setValue(2);
			dice.get(3).setValue(3);
			dice.get(4).setValue(5);
			System.out.println("fdadfsdfds");
			checkCombs();
			combs.get(11).calculate(dice);
			//End of test stuff
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