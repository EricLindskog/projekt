package main;

import java.util.Scanner;

public class runner {
	public static void main (String[] args){
		
		System.out.println("Hello world");
		Scanner s = new Scanner(System.in);
		Yatzy game = new Yatzy();
		int players= -1;
		int cpu = -1;
		do{
			System.out.println("Skriv in antal spelare");
			if(s.hasNextInt()){
				players = s.nextInt();
			}
			else{s.nextLine();}
		}while(players==-1);

		do{
			System.out.println("Skriv in antal datorer");
			if(s.hasNextInt()){
				cpu = s.nextInt();
			}
			else{s.nextLine();}
		}while(cpu==-1);
		game.start(players, cpu);
	}
}
