package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
/**
 * Runs the main menu with a highscore panel.
 * Starts a game of yahaytzse with the given number of players
 * @author eric
 *
 */
public class runner {
	public static void main (String[] args){
		final JFrame frame = new JFrame();
		JPanel botPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel partsPanel = new JPanel();
		JPanel playButtonPanel = new JPanel();

		JPanel highScorepanel = new JPanel();
		JTextPane highScoreField = new JTextPane();
		int JFrameX=520;
		int JFrameY=400;
		Dimension frameDimension= new Dimension(JFrameX,JFrameY);
		Dimension botPlayerDim = new Dimension(200,100);
		Dimension spinDim = new Dimension(50,50);
		Dimension panelsDim = new Dimension(250,JFrameY-50);

		SpinnerModel playerModel = new SpinnerNumberModel(1,0,5,1);
	    final JSpinner numPlayers = new JSpinner(playerModel);
	    SpinnerModel botModel = new SpinnerNumberModel(0, 0, 5, 1);
	    final JSpinner numBots = new JSpinner(botModel);
	    JLabel bots = new JLabel("Antal bots");
	    JLabel players = new JLabel("Antal spelare");
	    JLabel highScoresLabel = new JLabel("High Scores!");
	    JButton playButton = new JButton();
	    
	    playButton.setText("Play YAHHAHAHAHATZYYEYE");

		frame.setSize(frameDimension);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setTitle("YHAHAHAHAHATZEEEEYEYEYEYEYEYEYEEEEEEEAAAAAAAAAHHHH");
	
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
		
		playButtonPanel.setPreferredSize(botPlayerDim);
		playButtonPanel.setVisible(true);
		playButtonPanel.setBackground(Color.GRAY);
		playButtonPanel.setLocation(0, 0);
		playButtonPanel.setLayout(new BorderLayout());
		
		highScoreField.setEditable(false);
		highScoreField.setSize(panelsDim);
		
		highScorepanel.setPreferredSize(panelsDim);
		highScorepanel.setVisible(true);
		highScorepanel.setBackground(Color.WHITE);
		highScorepanel.setLocation(0, 0);
		highScorepanel.setLayout(new BorderLayout());
		highScorepanel.add(highScoreField,BorderLayout.CENTER);
		
	    numBots.setEditor(new JSpinner.DefaultEditor(numBots));
	    numBots.setPreferredSize(spinDim);
	    numPlayers.setPreferredSize(spinDim);
	    numPlayers.setEditor(new JSpinner.DefaultEditor(numPlayers));
	    
	    playButtonPanel.add(playButton,BorderLayout.CENTER);
	    
	    highScorepanel.add(highScoresLabel, BorderLayout.NORTH);
	    
	    playerPanel.add(players, FlowLayout.LEFT);
		playerPanel.add(numPlayers,FlowLayout.LEFT);
		
	    botPanel.add(bots, FlowLayout.LEFT);
		botPanel.add(numBots,FlowLayout.LEFT);
		
		partsPanel.add(playerPanel);
		partsPanel.add(botPanel);
		partsPanel.add(playButtonPanel);
		
		frame.add(partsPanel);;
		frame.add(highScorepanel);
		highScoreField.setText(getHighscores());
		
		playButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				int tempB = (Integer)numBots.getValue();
				int tempP = (Integer)numPlayers.getValue();
				
				if(tempP>0 && tempB>=0){

					Yatzy game = new Yatzy();
					game.start(tempP,tempB);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(frame, "Something went wrong",null, JOptionPane.YES_OPTION);
				}
				
			}
			
		});
		
	}
	public static String getHighscores(){
		String[] temp = getHighscoreArr();
		String out = "";
		for (int i = 0; i < temp.length; i++) {
			out+=temp[i]+" \n";
		}
		
		
		return out;
	}
	public static String[] getHighscoreArr(){
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
		return temp;
	}
}
