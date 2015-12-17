package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
/**
 * 
 * @author Zydoc
 * Runs a game of yahhahahahahatzyeah
 */
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
	final JFrame frame = new JFrame();
	
	/**
	 * Initiates the game of yahtzeee
	 * @param players number of players wanted
	 * @param bots number of bots wanted
	 */
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
		create();
		
	}
	/**
	 * Creates all the gui and runs the game
	 */
	public void create(){
			
			int JFrameX=830;
			int JFrameY=520;
			
			JPanel diePanel = new JPanel();
			JPanel combinatinPanel = new JPanel();
			final JPanel scorePanel = new JPanel();
			final JPanel detailPanel = new JPanel();
			Dimension P1P2Dim = new Dimension(350,500);
			Dimension FrameDim = new Dimension(JFrameX,JFrameY);
			Dimension p2 =new Dimension(150,500);
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	/**
	 * Updates the participants scorepanel with the current score
	 * @param scorePanel the scorepanel that you want to update
	 */
	public void updateScore(JPanel scorePanel){
		for (int i = 0; i < scorePanel.getComponentCount(); i++) {
			if(scorePanel.getComponent(i) instanceof JButton){
				JButton temp = (JButton)scorePanel.getComponent(i);
				temp.setText("Player "+(i+1)+" score: "+parts.get(i).getScore());
			}
		}
	}
	/**
	 * Prints out the number of occurrences of the dice
	 */
	public void printStats(){
		for (int i = 0; i < stats.length; i++) {
			System.out.println("Number of "+(i+1)+":s : "+ stats[i]);
		}
	}
	/**
	 * calculates the points for all the current combinations
	 * based on the current values of the dice. Also checks which ones the current
	 * player can press, and if it can't press any it asks you to discard one.
	 */
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
			botTurn();
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
				if(parts.get(currentPart) instanceof Bot){
					
					if(combs.get(i).getComb().equals(((Bot)(parts.get(currentPart))).Discard())){
						combs.get(i).doClick();
						doclick=true;
					}
				}
			}
		}
	}
	/**
	 * Runs if it's a bots turn. Makes the decisions for the bot. 
	 */
	public void botTurn(){
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
	/**
	 * Resets the combinations and makes sure the bot rolls for the first time
	 */
	public void resetCombs(){
		for(CombButton butt : combs){
			butt.reset();
		}
		rollDice.doClick();
	}
	/**
	 * Resets the dice to the value 0
	 * Resulting in them not being visible
	 */
	public void resetDice(){
		for(Die die : dice){
			die.setValue(0);
			die.setToRoll(true);
		}
	}
	/**
	 * Adds points when a combination is selected. Then moves on to the next player
	 * @param points the points you want to add to the current player
	 */
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
	/**
	 * Checks if there are any rounds left.
	 * Then asks if you want to play again.
	 */
	public void end(){
		if(MAX_TURNS <= 0){
			saveHighscore();
				int reply = JOptionPane.showConfirmDialog(null, "would you like to starta new game", "New game", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		          runner jeng = new runner();
		          jeng.main(null);
		          frame.dispose();
		          
		        }
		        else {
		           //JOptionPane.showMessageDialog(null, "GOODBYE");
		           System.exit(0);
		        }
		       
		    }
	}
	/**
	 * Checks and calculates if the player is to get a bonus
	 * 
	 */
	public void calcBonus(){
		EnumSet <Combinations>bonus = EnumSet.of(
				Combinations.aces,
				Combinations.twos,
				Combinations.threes,
				Combinations.fours,
				Combinations.fives,
				Combinations.sixes);
		if(parts.get(currentPart).getKeySet().containsAll(bonus)&&!(parts.get(currentPart).getKeySet().contains(Combinations.bonus))){
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
	/**
	 * Returns a string with the info for the current player
	 * @return the string with the info
	 */
	public String updateDetails(){
		String temp="";
		int bonus = 0;
		temp+="Spelare"+(currentPart+1)+":s poäng och bonusar\n";
		for (Combinations comb : parts.get(currentPart).getKeySet()){
			temp+=comb.toString()+" : "+parts.get(currentPart).getCombPoints(comb)+"\n";
			bonus +=parts.get(currentPart).getCombPoints(comb);
		}
		if(bonus<63)temp+="Kvar till bonus: "+(63-bonus);
		else temp+="Spelare har fått bonus: ";
		return temp;
	}
	/**
	 * Saves the odds for the combinations
	 */
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
	/**
	 * saves the highscore for the player with the highest score
	 */
	public void saveHighscore(){
		int tempScore = 0;
		for(Participant p : parts){
			if(p instanceof Player){
				if(p.getScore()>tempScore){
					tempScore = p.getScore();
				}
			}
		}
		String input = JOptionPane.showInputDialog("Skriv in namn för nytt highscore");
		String newScore = input+" "+tempScore+":";
		String[] templist = runner.getHighscoreArr();
		String[] list = new String[templist.length+1];
		list[0] = newScore;
		for (int i = 0; i < templist.length; i++) {
			list[i+1] = templist[i];
		}
		
		list = sortHighscore(list);
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("highscore.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("sdadad");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < list.length; i++) {
			if(list[i].length()>0){
				writer.print(list[i]+":");
			}
		}
		writer.close();
	}
	/**
	 * sorts the list of highscores by their score
	 * @param list
	 * @return
	 */
	public String[] sortHighscore(String[] list){
		HashMap<Integer, ArrayList<String>> unsortMap = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < list.length; i++) {
			String[] temp = list[i].split(" ");
			temp[0]=temp[0].replaceAll(":", "");
			temp[1]=temp[1].replaceAll(":", "");
			int key = Integer.parseInt(temp[1]);
			if(unsortMap.containsKey(key)){
				unsortMap.get(key).add(temp[0]);
			}else{
				ArrayList<String>values = new ArrayList<String>();
				values.add(temp[0]);
				unsortMap.put(key,values);
			}
			
		}
		Map<Integer, ArrayList <String>> treeMap = new TreeMap<Integer, ArrayList <String>>(
				new Comparator<Integer>() {

				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}

		});
		treeMap.putAll(unsortMap);
		for (int key : treeMap.keySet()) {
			for(String value : treeMap.get(key)){
				System.out.println(key+" "+value);
			}
		}
		System.out.println("map");
		int i = 0;
		for(int key : treeMap.keySet()){
			for(String value : treeMap.get(key)){
				list[i] = value+" "+key;
				System.out.println(list[i]);
				i++;
			}
		}
		System.out.println("tjena");
		return list;
	}
}