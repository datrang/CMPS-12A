/**
 * Assignment #1
 * This program generates an anagram of a random word from a word list.
 * 
 * Author: David Trang (datrang@ucsc.edu)
 * 
 * Citations
 * Starting code - Charlie McDowell (minor mods Deam Bailey 08/23/07)
 */

import java.util.*;
import java.io.*;

public class AnagramPuzzleGenerator{
	public static void main(String[] args) throws FileNotFoundException{
		Random Randomizer = new Random(); //random number generation
		Scanner in = new Scanner(new FileInputStream(args[0]));
		int wordListLength = in.nextInt(); //first item is number of words
		StringBuffer anagramOutput = new StringBuffer(); //the anagram
		StringBuffer word; //choice of word
		String wordChoice = new String(); //word placeholder
				
		int index = 0;
		while (index < Randomizer.nextInt(wordListLength)){ 
			wordChoice = (in.next());
			index++;
		}
		//Randomnly stops on a index which be the word chosen

		word = new StringBuffer(wordChoice); //sets stringBuffer to word
		index = 0; //resets index 
		while(index < wordChoice.length()){
			int temp = Randomizer.nextInt(word.length());
			anagramOutput.append(word.charAt(temp));
			word.deleteCharAt(temp);
			index++;
		}
		//put the letters of the word in a random order
		
		System.out.println(anagramOutput.toString());
	}
}