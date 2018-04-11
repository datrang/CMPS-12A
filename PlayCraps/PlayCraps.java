/**
 * Assignment #2 Part (1 of 2)
 * This program plays the game of craps. 
 * 
 * Author: David Trang (datrang@ucsc.edu)
 */

import java.util.*;

public class PlayCraps {
	public static void main(String args[]){
		int seed = intInputter("Please enter the seed");
		Dice die1 = new Dice(seed);
		Dice die2 = new Dice(seed * seed);
		int chips = intInputter("How many chips do you want");//Starting chip
		craps(die1, die2, chips);
	}
	
	public static void craps(Dice die1, Dice die2, int chips){
		Scanner input = new Scanner(System.in);
		int totalChips = chips;
		int bet = betManager(chips);
		if(dieRoller(die1, die2, true, 0) == true){
			totalChips += bet;
			System.out.println("You've won. You now have " + (totalChips));
			craps(die1, die2,totalChips);
		}
		else{
			totalChips -= bet;
			System.out.println("You've lost. You now have " + totalChips);
			if(totalChips > 0){
				craps(die1,die2, totalChips);
			}
			else{
				System.out.println("You have lost");
				playAgain(die1,die2);
			}
		}
		input.close();
	}
	//craps put all the methods below together to create a full game of craps
	//It tells you win or not after each round of rolls
	//It tells how much you gain or lose
	
	public static int betManager(int chips){
		if(chips == 0){
			System.out.println("No Money.");
			return 0;
		}
		int bet = intInputter("Please enter your bet");
		if(bet > chips){
			System.out.println("Insufficient Funds");
			return betManager(chips);
		}
		else{
			return bet;
		}
	}
	//betManager manages each bet given for a round
	//It takes in account for illegal inputs or betting more than you have
	
	public static boolean dieRoller(Dice die1, Dice die2, boolean firstRoll, int point){
		if(point == 0){
			System.out.println("Hit return to roll.");
		}
		Scanner input = new Scanner(System.in);
		input.nextLine();
		die1.roll();
		die2.roll();
		displayDiceImage(die1.getValue(),die2.getValue());
		System.out.println("Your roll is " + die1.getValue() + ", " + die2.getValue());
		int total = die1.getValue()+die2.getValue();
		if(firstRoll){
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

	public static void playAgain(Dice die1, Dice die2){
		System.out.println("Play Again? (y/n)");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		if(answer.equals("y")){
			System.out.println("How many chips do you want");
			int chips = input.nextInt();
			craps(die1, die2, chips);
			input.close();
		}
		else if(answer.equals("n")){
			System.out.println("Thank you for playing.");
			input.close();
		}
		else{
			System.out.println("Illegal Response");
			playAgain(die1,die2);
		}
	}
	//	If you lose, playAgain will ask if you want to play again or not
	//	It takes in account for incorrect responses.
	
	public static void displayDiceImage(int die1, int die2){
		String[][] diceImage= {
				{" _______ ","|       |","|       |","|   *   |","|       |","|_______|"},
				{" _______ ","|       |","|     * |","|       |","| *     |","|_______|"},
				{" _______ ","|       |","| *     |","|   *   |","|     * |","|_______|"},
				{" _______ ","|       |","| *   * |","|       |","| *   * |","|_______|"},
				{" _______ ","|       |","| *   * |","|   *   |","| *   * |","|_______|"},
				{" _______ ","|       |","| *   * |","| *   * |","| *   * |","|_______|"}
		};
		for(int i =0; i < diceImage[0].length; i++){
			System.out.print(diceImage[die1-1][i]);
			System.out.print("     ");
			System.out.println(diceImage[die2-1][i]);
		}
	}
	//	displayDiceImage uses a 2d array to store the image for a dice row by row
	//	It then displays the dice side by side.
	
	public static boolean isInt(String input){
		String[] values = {"1","2","3","4","5","6","7","8","9","0"};
		boolean allNums = false;
		for(int i = 0; i < input.length(); i++){
			for(int j = 0; j < values.length; j++){
				if(input.substring(i,i+1).equals(values[j])){
					allNums = true;
					break;
				}
				else if(!(input.substring(i,i+1).equals(values[j]))){
					allNums = false;
				}
			}
			if(allNums == false){break;}
		}
		return allNums;
	}
	// checks if the input is a legal input
	
	public static int intInputter(String prompt){
		System.out.println(prompt);
		Scanner in = new Scanner(System.in);
		String input = in.next();
		if(!(isInt(input))){
			System.out.println("Illegal Input");
			return intInputter(prompt);
		}
		else{
			int returnValue = Integer.parseInt(input);
			return returnValue;
		}
	}
}

class Dice{
	private int diceValue;
	private Random roller;
	Dice(int seed){
		roller = new Random(seed);
	}
	
	void roll(){
		diceValue = roller.nextInt(6)+1;
	}
	
	int getValue(){
		return diceValue;
	}
}
//Dice is a class for a dice with methods for rolling it and its value