/**
 * Assignment #2
 * This program finds the solutions for a given anagram from a word list.
 * 
 * Author: David Trang (datrang@ucsc.edu)
 * 
 * Citations
 * Starting code - Charlie McDowell (minor mods Deam Bailey 08/23/07)
 */

import java.util.*;
import java.io.*;

public class FindingAnagram {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner list = new Scanner(new FileInputStream(args[0]));
		Scanner in = new Scanner(System.in); 
		String[] wordList = new String[list.nextInt()];//stores the words
		int[] wordNumericalValue = new int[wordList.length];
		
		for(int i=0; i < wordList.length; i++){
			wordList[i] = list.next();
			wordNumericalValue[i] = Numerizer(wordList[i]);
		}
		/**adds the words from text file into a array and the product of all
		their letters hashCode Value*/
				
		anagramSolver(wordList,wordNumericalValue);
		list.close();
		in.close();
	}
	
	public static void anagramSolver(String[] wordList,int[] wordValue){
		Scanner in = new Scanner(System.in);
		System.out.println("Please type in your Anagram.");
		String choice = in.next(); //Takes in an input String from the user
		int choiceNum = Numerizer(choice);
		for(int index = 0; index < wordList.length; index++){
			if(choiceNum==wordValue[index] && !choice.equals(wordList[index])){
				System.out.println(wordList[index]);
			}
		}
		recursion(wordList, wordValue);
		in.close();
	}
	//solves the Anagram given 
	
	public static void recursion(String[] wordList, int[] wordValue){
		Scanner in = new Scanner(System.in);
		System.out.println("Solve another Anagram(y/n)?");
		String temp = in.next();
		if(temp.equals("y")){ //want to solve another anagram 
			anagramSolver(wordList, wordValue);
		}
		else if(temp.equals("n")){ //don't want to solve another anagram
			System.out.println("Thank you");
		}
		else{ // if you don't the write responses
			System.out.println("You typed incorrectly.");
			System.out.println("Please redo.");
			recursion(wordList, wordValue);
		}
		in.close();
	}
	//Asks if you want to do another anagram or to stop.
	
	public static int Numerizer(String word){
		int temp = 1;
		for(int index = 0; index < word.length(); index++){
			temp = temp * word.substring(index,index+1).hashCode();
		}
		return temp;
	}
	//returns the product of the word's letter hashCode
}