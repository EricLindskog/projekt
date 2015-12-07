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
	static final int MAX_ROLLS_PER_ROUND = 3;
	public int rounds = 15;
	public int currentPart;
	public int currentRolls=0;
	public boolean roundRun=true;
	ArrayList<Die>dice =new ArrayList<Die>();
	ArrayList<Participant>parts = new ArrayList<Participant>();
	ArrayList <CombButton>combs = new ArrayList<CombButton>();
	public void start(int players,int bots){
		for (int i = 0; i < players; i++) {
			parts.add(new Player());
		}
		for (int i = 0; i < bots; i++) {
			parts.add(new Bot());
		}
		for (int i = 0; i < 5; i++) {
			dice.add(new Die());
		}
		create();
		run();
		
	}
	public void create(){
			JFrame frame = new JFrame();
			int JFrameX=700;
			int JFrameY=500;
			
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			Dimension P1P2Dim = new Dimension(350,500);
			Dimension FrameDim = new Dimension(800,500);
			Dimension p2 =new Dimension(150,500);
			
			//ArrayList <CombButton>combs = new ArrayList<CombButton>();
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
			panel2.setBackground(Color.CYAN);
			panel2.setPreferredSize(p2);
			
			button.setVisible(true);
			button.setLocation(50,50);
			
			frame.add(panel1);
			frame.add(panel2);
			for(int i =0; i<combs.size(); i++){
				combs.get(i).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Object source = e.getSource();
				        if (source instanceof CombButton) {
				            CombButton butt = (CombButton)source;
				            if(butt.isClickable()){
				            	calcPoints(butt.getPoints());
				            }
				        }
					}
				});
				combs.get(i).setLocation(50, 50*(i+1));
				combs.get(i).setVisible(true);
				combs.get(i).reset();
				panel2.add(combs.get(i));
			}
			panel1.add(button);
			button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(currentRolls<MAX_ROLLS_PER_ROUND){
						for (int i = 0; i < dice.size(); i++) {
							dice.get(i).roll();
						}
						currentRolls++;
						calculateCombs();
					}
					else{
						/*
						 * Någon slags dialogruta som sägar att du inte får kasta igen
						 */
					}
				}
				
			});
			for (int i = 0; i < dice.size(); i++) {
				//dice.get(i).roll();
				dice.get(i).setValue(0);
				panel1.add(dice.get(i));
				dice.get(i).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						Object source = e.getSource();
				        if (source instanceof JButton) {
				            Die die = (Die)source;
				            System.out.println(die.getValue());
				        }
					}
				});
			}
			calculateCombs();
			resetCombs();
	}
	public void run(){
		System.out.println(parts.size()+" spelare");
		//boolean turn= true;
		for(int r= 0; r<rounds;r++){
			for (int i = 0; i < parts.size(); i++) {
				currentPart=i;
				System.out.println("Spelare; "+(currentPart+1)+" tur");
				while(roundRun){
					//Fastnar tills falskt
					//System.out.println("stuck?");
				}
				System.out.println("Jag Borde komma hit");
				currentRolls=0;
				roundRun=true;
			}
		}
	}
	public void calculateCombs(){
		Collections.sort(dice);
		for(CombButton butt : combs){
			
			if(parts.get(currentPart).getCombs().contains(butt.getComb())){
				butt.calculate(dice);
				butt.setClickable(true);
			}
			else{butt.reset();}
			
		}
	}
	public void resetCombs(){
		for(CombButton butt : combs){
			butt.reset();
		}
	}
	public void resetDice(){
		for(Die die : dice){
			die.setValue(0);
		}
	}
	public void calcPoints(int points){
		parts.get(currentPart).addScore(points);
		System.out.println("hej");
		roundRun =false;
		resetCombs();
		resetDice();
		
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