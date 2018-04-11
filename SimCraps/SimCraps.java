/**
 * Assignment #2 Part (2 of 2)
 * This program calculate the probability of winning in craps with a given
 * amount of games.
 * 
 * Author: David Trang (datrang@ucsc.edu)
 */

import java.util.*;

public class SimCraps {
	public static void main(String args[]){
		Dice die1 = new Dice();
		Dice die2 = new Dice();
		int numRounds = Integer.parseInt(args[0]);
		double numWins = 0;
		for(int i = 0; i < numRounds; i++){
			numWins += craps(die1,die2);
		} //adds up the number of wins
		double probWin = (numWins/numRounds);
		//find the average of wins which is the probability
		System.out.print("Prob of Winning = ");
		System.out.println(probWin*100 + "%");
	}
	
	public static int craps(Dice die1, Dice die2){
		if(dieRoller(die1, die2, true, 0) == true){
			return 1;
		}
		else{
			return 0;
		}
	}
	//returns a 1 if you win or returns a 0 if you lose

	
	public static boolean dieRoller(Dice die1, Dice die2, boolean first, int point){
		die1.roll();
		die2.roll();
		int total = die1.getValue()+die2.getValue();
		if(first){
			if(total == 7 || total == 11 ){
				return true;
			}
			else if(total == 2 || total == 3 || total == 12){
				return false;
			}
			else{
				return dieRoller(die1, die2, false, total);
			}
		}
		else{
			if(total == 7){
				return false;
			}
			else if(total == point){
				return true;
			}
			else{
				return dieRoller(die1, die2, false, point);
			}
		}
	}
	//dieRoller does the rolling of the dice and tells if you win or lose
}

	
class Dice{
	private int diceValue;
	private Random roller;
	Dice(){
		roller = new Random();
	}	
	void roll(){
		diceValue = roller.nextInt(6)+1;
	}	
	int getValue(){
		return diceValue;
	}
}
//Dice is a class for a dice with methods for rolling it and its value
