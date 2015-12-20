package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import participant.Participant;
import participant.Player;

public class Highscores {
	/**
	 * saves the highscore for the player with the highest score
	 */
	public static void saveHighscore(ArrayList <Participant>parts){
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
		String[] templist = getHighscoreArr();
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
	 * @param list the list you want to sort
	 * @return returns the sorted list
	 */
	public static String[] sortHighscore(String[] list){
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
	/**
	 * Formats the string array from getHighscoreArr 
	 * into as tring that can be used in a textpane
	 * @return returns a string with all the highscores
	 */
	public static String getHighscores(){
		String[] temp = getHighscoreArr();
		String out = "";
		for (int i = 0; i < temp.length; i++) {
			out+=temp[i]+" \n";
		}
		
		
		return out;
	}
	/**
	 * Creates and reads an highscore file and splits it into a String array
	 * @return returns a string array with all the highscores currently saved
	 */
	public static String[] getHighscoreArr(){
		InputStream is=null;
		try {
			is = new FileInputStream("highscore.txt");
		} catch (FileNotFoundException e) {
			
		}
		String text = "";
		try{
			text = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
		} catch(NoSuchElementException e){
			System.out.println("Highscore is empty");
		}
		if(text.length()>0 && text.charAt(text.length()-1)==':'){
			text = text.substring(0,text.length()-1);
		}
		String[] temp = text.split(":");
		return temp;
	}
}
