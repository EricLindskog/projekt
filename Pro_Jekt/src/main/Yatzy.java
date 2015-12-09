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
import javax.swing.JOptionPane;
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
	static final int MAX_ROUNDS = 15;
	public int currentPart=0;
	public int currentRolls=0;

	int[]stats = new int[6];
	ArrayList<Die>dice =new ArrayList<Die>();
	ArrayList<Participant>parts = new ArrayList<Participant>();
	ArrayList <CombButton>combs = new ArrayList<CombButton>();
	
	public void start(int players,int bots){
		for (int i = 0; i < stats.length; i++) {
			stats[i]=0;
		}
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
		
	}
	public void create(){
			final JFrame frame = new JFrame();
			int JFrameX=700;
			int JFrameY=500;
			
			JPanel diePanel = new JPanel();
			JPanel combinatinPanel = new JPanel();
			final JPanel scorePanel = new JPanel();
			Dimension P1P2Dim = new Dimension(350,500);
			Dimension FrameDim = new Dimension(800,500);
			Dimension p2 =new Dimension(150,500);
			
			combs = CombButtons.getButtons();
			
			AbstractButton rollDice = new JButton();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setSize(FrameDim);
			frame.setResizable(false);
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			diePanel.setLocation(0, 0);
			diePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			diePanel.setBackground(Color.CYAN);
			diePanel.setPreferredSize(P1P2Dim);
			
			scorePanel.setLocation(350, 0);
			scorePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			scorePanel.setBackground(Color.MAGENTA);
			scorePanel.setPreferredSize(p2);
			
			for(Participant part: parts){
				String temp = Integer.toString(part.getScore());
				scorePanel.add(new JButton(temp));
			}
			combinatinPanel.setLocation(350, 0);
			combinatinPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			combinatinPanel.setBackground(Color.MAGENTA);
			combinatinPanel.setPreferredSize(p2);
			
			rollDice.setVisible(true);
			rollDice.setLocation(50,50);
			
			frame.add(diePanel);
			frame.add(combinatinPanel);
			frame.add(scorePanel);
			for(int i =0; i<combs.size(); i++){
				combs.get(i).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						
						Object source = e.getSource();
				        if (source instanceof CombButton) {
				            CombButton butt = (CombButton)source;
				            if(butt.isClickable()){
				            	parts.get(currentPart).setCombValue(butt.getComb(), butt.getPoints());
				            	
				            	System.out.println(parts.get(currentPart).getMap());
				            	calcPoints(butt.getPoints());
				            }
				        }
				        updateScore(scorePanel);
					}
				});
				combs.get(i).setLocation(50, 50*(i+1));
				combs.get(i).setVisible(true);
				combs.get(i).reset();
				combinatinPanel.add(combs.get(i));
			}

			diePanel.add(rollDice);
			rollDice.setText("ROLL ROLL ROLL");
			rollDice.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					boolean canRoll=false;
					printStats();
					for(Die die : dice){
						if(die.isToRoll()){
							canRoll=true;
						}
					}
					if(currentRolls<MAX_ROLLS_PER_ROUND&&canRoll){
						for (int i = 0; i < dice.size(); i++) {
							if(dice.get(i).isToRoll()){
								dice.get(i).roll();
								stats[dice.get(i).getValue()-1] +=1;
								dice.get(i).setToRoll(false);
							}
						}
						currentRolls++;
						calculateCombs();
						updateScore(scorePanel);
					}
					
					else if(canRoll){
						 //Någon slags dialogruta som sägar att du inte får kasta igen
						JOptionPane.showMessageDialog(frame, "You can't roll again",null, JOptionPane.YES_OPTION);
					}
					else{
						JOptionPane.showMessageDialog(frame, "Select dice to roll",null, JOptionPane.YES_OPTION);
					}
					canRoll=true;
				}
			});
			for (int i = 0; i < dice.size(); i++) {
				dice.get(i).setValue(0);
				diePanel.add(dice.get(i));
				dice.get(i).addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						updateScore(scorePanel);
						Object source = e.getSource();
				        if (source instanceof JButton) {
				            Die die = (Die)source;
				            die.setToRoll(!die.isToRoll());
				        }
					}
				});
			}
			resetCombs();
	}
	public void updateScore(JPanel scorePanel){
		for (int i = 0; i < scorePanel.getComponentCount(); i++) {
			if(scorePanel.getComponent(i) instanceof JButton){
				JButton temp = (JButton)scorePanel.getComponent(i);
				temp.setText("Player "+i+" score: "+parts.get(i).getScore());
			}
		}
	}
	public void printStats(){
		for (int i = 0; i < stats.length; i++) {
			System.out.println("Number of "+(i+1)+":s : "+ stats[i]);
		}
	}
	public void calculateCombs(){
		Collections.sort(dice);
		for(CombButton butt : combs){
			butt.setClickable(false);
			if(!(parts.get(currentPart).getKeySet().contains(butt.getComb()))){
				
				butt.calculate(dice);
				if(butt.getPoints()>0){
					butt.setClickable(true);
				}
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
			die.setToRoll(true);
		}
	}
	public void calcPoints(int points){
		currentRolls=0;
		parts.get(currentPart).addScore(points);
		
		currentPart++;
		if(currentPart>=parts.size()){
			currentPart=0;
		}
		System.out.println("Spelare: "+(currentPart+1)+" tur");
		System.out.println("Har poäng: "+parts.get(currentPart).getScore());
		resetCombs();
		resetDice();
	}
}