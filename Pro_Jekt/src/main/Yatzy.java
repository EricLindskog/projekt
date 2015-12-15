package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
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
	static int MAX_TURNS=0;
	public int currentPart=0;
	public int currentRolls=0;

	AbstractButton rollDice = new JButton();
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
		MAX_TURNS = (players+bots)*15;
		MAX_TURNS = 2;
		create();
		
	}
	public void create(){
			final JFrame frame = new JFrame();
			int JFrameX=820;
			int JFrameY=500;
			
			JPanel diePanel = new JPanel();
			JPanel combinatinPanel = new JPanel();
			final JPanel scorePanel = new JPanel();
			final JPanel detailPanel = new JPanel();
			Dimension P1P2Dim = new Dimension(350,500);
			Dimension FrameDim = new Dimension(JFrameX,JFrameY);
			Dimension p2 =new Dimension(150,500);
			
			
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
			
			detailPanel.setLocation(350, 0);
			detailPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			detailPanel.setBackground(Color.PINK);
			detailPanel.setPreferredSize(p2);
			
			combinatinPanel.setLocation(350, 0);
			combinatinPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			combinatinPanel.setBackground(Color.MAGENTA);
			combinatinPanel.setPreferredSize(p2);
			
			rollDice.setVisible(true);
			rollDice.setLocation(50,50);
			
			frame.add(diePanel);
			frame.add(combinatinPanel);
			frame.add(scorePanel);
			frame.add(detailPanel);
			final JTextPane scoreDetail = new JTextPane();
			scoreDetail.setPreferredSize(p2);
			scoreDetail.setEditable(false);
			detailPanel.add(scoreDetail);
			
			for(int i =0;i<parts.size();i++){
				String temp = "Player "+(i+1)+" : "+parts.get(i).getScore();
				JButton score = new JButton(temp);
				scorePanel.add(score);
				
			}
			
			combs = CombButtons.getButtons();
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
				        scoreDetail.setText(updateDetails());
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
					scoreDetail.setText(updateDetails());
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
				temp.setText("Player "+(i+1)+" score: "+parts.get(i).getScore());
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
		int count=0;
		for(CombButton butt : combs){
			butt.setClickable(false);
			butt.calculate(dice);
			if(!(parts.get(currentPart).getKeySet().contains(butt.getComb()))){
				
				if(butt.getPoints()>0){
					butt.setClickable(true);
				}
				else count++;
			}
			else{
				butt.reset();
				count++;
			}
		}
		
		if(parts.get(currentPart) instanceof Bot){
			
			
			ArrayList<CombButton> buttontemp = new ArrayList<CombButton>();
			for (int i = 0; i < combs.size(); i++) {
				if(combs.get(i).isClickable() && !(parts.get(currentPart).getKeySet().contains(combs.get(i)))){
					buttontemp.add(combs.get(i));
				}
			}
			boolean clickComb = false;
			while(currentRolls<MAX_ROLLS_PER_ROUND && clickComb==false){
				
			
			 Combinations combtemp = ((Bot)(parts.get(currentPart))).bottPlaying(buttontemp);
			 
			if(combtemp==null){
				for (Die die : dice) {
					die.roll();
					currentRolls++;
				}
				
			}
			
				else{
					for (int i = 0; i < combs.size(); i++) {
						if(combs.get(i).getComb().equals(combtemp)){
							combs.get(i).doClick();
							clickComb=true;
							
						}
							
					}
				}
			}
			}
		
		if(count>=combs.size()&&currentRolls>=3){
			if(!(parts.get(currentPart) instanceof Bot)){
				JOptionPane.showMessageDialog(null, "Out of combination, "
					+ "select one of your remaining to discard"
					,null, JOptionPane.YES_OPTION);
			}
			for(CombButton butt : combs){
				butt.setClickable(false);
				if(!(parts.get(currentPart).getKeySet().contains(butt.getComb()))){
					butt.setClickable(true);
				}
			}
			boolean doclick=false;
			if(doclick==false)
			for (int i = 0; i < combs.size(); i++) {
				if(combs.get(i).getComb().equals(((Bot)(parts.get(currentPart))).Discard())){
					combs.get(i).doClick();
					doclick=true;
				}
			}
		}
	}
	public void resetCombs(){
		for(CombButton butt : combs){
			butt.reset();
		}
		rollDice.doClick();
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
		MAX_TURNS--;
		saveCombOdds();
		calcBonus();
		end();
		currentPart++;
		if(currentPart>=parts.size()){
			currentPart=0;
		}
		System.out.println("Spelare: "+(currentPart+1)+" tur");
		System.out.println("Har poäng: "+parts.get(currentPart).getScore());
		
		resetDice();
		resetCombs();
	}
	public void end(){
		if(MAX_TURNS == 0){
			
		}
	}
	public void calcBonus(){
		EnumSet <Combinations>bonus = EnumSet.of(
				Combinations.aces,
				Combinations.twos,
				Combinations.threes,
				Combinations.fours,
				Combinations.fives,
				Combinations.sixes);
		if(parts.get(currentPart).getKeySet().containsAll(bonus)){
			int points=0;
			for (Combinations key : bonus) {
				points+=parts.get(currentPart).getCombPoints(key);
				
			}
			if(points>=63){
				parts.get(currentPart).addScore(50);
				parts.get(currentPart).setCombValue(Combinations.bonus, 50);
			}
		}
	}
	public String updateDetails(){
		String temp="";
		int bonus = 0;
		temp+="Spelare"+(currentPart+1)+":s poäng och bonusar\n";
		for (Combinations comb : parts.get(currentPart).getKeySet()){
			temp+=comb.toString()+" : "+parts.get(currentPart).getCombPoints(comb)+"\n";
			bonus +=parts.get(currentPart).getCombPoints(comb);
		}
		temp+="Kvar till bonus: "+(63-bonus);
		return temp;
	}
	public void saveCombOdds(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("odds.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("sdadad");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(CombButton butt : combs){
			writer.println(butt.getComb()+" : "+butt.getOccurences());
		}
		writer.close();
	}
}