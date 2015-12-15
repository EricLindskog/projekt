package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class runner {
	public static void main (String[] args){
		final JFrame frame = new JFrame();
		JPanel botPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel partsPanel = new JPanel();

		JPanel highScorepanel = new JPanel();
		JTextPane highScore = new JTextPane();
		int JFrameX=800;
		int JFrameY=400;
		Dimension d = new Dimension(JFrameX,JFrameY);
		Dimension botPlayerDim = new Dimension(200,125);
		Dimension spinDim = new Dimension(50,50);
		Dimension panelsDim = new Dimension(250,JFrameY-50);

		SpinnerModel playerModel = new SpinnerNumberModel();
	    JSpinner numPlayers = new JSpinner(playerModel);
	    SpinnerModel botModel = new SpinnerNumberModel();
	    JSpinner numBots = new JSpinner(botModel);
	    JLabel bots = new JLabel("Antal bots");
	    JLabel players = new JLabel("Antal spelare");
	    JLabel highScoresLabel = new JLabel("High Scores!");
	    
	    JButton play = new JButton();

		frame.setSize(d);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
	
		botPanel.setPreferredSize(botPlayerDim);
		botPanel.setVisible(true);
		botPanel.setBackground(Color.GRAY);
		botPanel.setLocation(0, 0);
		botPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		partsPanel.setPreferredSize(panelsDim);
		partsPanel.setVisible(true);
		partsPanel.setBackground(Color.GRAY);
		partsPanel.setLocation(0, 0);
		//partsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		playerPanel.setPreferredSize(botPlayerDim);
		playerPanel.setVisible(true);
		playerPanel.setBackground(Color.GRAY);
		playerPanel.setLocation(0, 0);
		playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		highScore.setEditable(false);
		highScore.setSize(panelsDim);
		
		highScorepanel.setPreferredSize(panelsDim);
		highScorepanel.setVisible(true);
		highScorepanel.setBackground(Color.WHITE);
		highScorepanel.setLocation(0, 0);
		highScorepanel.setLayout(new BorderLayout());
		highScorepanel.add(highScore,BorderLayout.CENTER);
		
	    numBots.setEditor(new JSpinner.DefaultEditor(numBots));
	    numBots.setPreferredSize(spinDim);
	    numPlayers.setPreferredSize(spinDim);
	    numPlayers.setEditor(new JSpinner.DefaultEditor(numPlayers));
	    
	    highScorepanel.add(highScoresLabel, BorderLayout.NORTH);
	    playerPanel.add(players, FlowLayout.LEFT);
	    botPanel.add(bots, FlowLayout.LEFT);
		botPanel.add(numBots,FlowLayout.LEFT);
		playerPanel.add(numPlayers,FlowLayout.LEFT);
		partsPanel.add(playerPanel);
		partsPanel.add(botPanel);
		
		frame.add(partsPanel);;
		frame.add(highScorepanel);
		highScore.setText(getHighscores());
		
	
		
		System.out.println("Hello world");
		Scanner s = new Scanner(System.in);
		Yatzy game = new Yatzy();
		int players1= -1;
		int cpu = -1;
		do{
			System.out.println("Skriv in antal spelare");
			if(s.hasNextInt()){
				players1 = s.nextInt();
			}
			else{s.nextLine();}
		}while(players1<0||players1>6);

		do{
			System.out.println("Skriv in antal datorer");
			if(s.hasNextInt()){
				cpu = s.nextInt();
			}
			else{s.nextLine();}
		}while(cpu<0 || cpu>6);
		
		game.start(players1, cpu);
		
	}
	public static String getHighscores(){
		InputStream is=null;
		try {
			is = new FileInputStream("highscore.txt");
		} catch (FileNotFoundException e) {
			
		}
		String text = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
		if(text.charAt(text.length()-1)==':'){
			text = text.substring(0,text.length()-1);
		}
		String[] temp = text.split(":");
		String out = "";
		for (int i = 0; i < temp.length; i++) {
			out+=temp[i]+" \n";
		}
		
		return out;
	}
}
