/**
 * Assignment #4
 * This program plays 3D Tic Tac Toe against a computer
 * 
 * Author: David Trang (datrang@ucsc.edu)
 */
import java.util.*;

public class TTT3D {
	private static String[][][] board;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		playTicTacToe();
		in.close();
	}
	
	private static void playTicTacToe(){
		System.out.println("Welcome to 3D Tic Tac Toe");
		board = boardDeclare();
		while(checkWin()){
			drawBoard();
			playerTurn();
			if(checkWin() == false){
				drawBoard();
				System.out.println("Player Wins! Congratulations.");
				break;
			}
			computerAi();
			if(declareDraw() == true){
				drawBoard();
				System.out.println("It's a draw");
				break;
			}
			else if(checkWin() == false){
				drawBoard();
				System.out.println("Computer Wins!");
				break;
			}
		}
		playAgain();
	}
	//puts all the method together to play a full game of 3D tic tac toe.
	
	private static void playAgain(){
		System.out.println("Play Again? (y/n)");
		String answer = in.next();
		if(answer.equals("y")){
			playTicTacToe();
		}
		else if(answer.equals("n")){
			System.out.println("Thank you for playing.");
		}
		else{
			System.out.println("Illegal Response");
			playAgain();
		}
	}
	//Asks the player if they want to play again after winning or losing
	
	private static boolean checkWin(){
		if(declareDraw()){
			return false;
		}
		for(int i = 0; i < lineValues().length;i++){
			if(lineValues()[i] == 4){
				return false;
			}
			else if(lineValues()[i] == 40){
				return false;
			}
		}
		return true;
	}
	//Check if there is any winning combination or a draw on the board
	
	private static String[][][] boardDeclare(){
		String[][][] temp = new String[4][4][4];
		for(int i = 0; i < temp.length; i++){
			for(int j = 0; j < temp[i].length; j++){
				for(int k = 0; k < temp[i][j].length; k++){
					temp[i][j][k] = "_";
				}
			}
		}
		return temp;
	}
	//Declare the board with all _ or being blank
	
	private static void drawBoard(){
		for(int i = board.length -1 ; i >= 0; i--){
			for(int j = board[i].length-1; j >= 0; j--){
				for(int temp = j; temp > 0; temp--){
					System.out.print(" ");
				}
				System.out.print("" + i + "" + j + " ");
				for(int k = 0; k < board[i][j].length; k ++){
					System.out.print(board[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("  0 1 2 3");
	}
	//Prints the board for the player to view
	
	private static void playerTurn(){
		String temp = valueInputter();
		int line = Integer.parseInt(temp.substring(0,1));
		int row = Integer.parseInt(temp.substring(1,2));
		int column = Integer.parseInt(temp.substring(2,3));
		board[line][row][column] = "X";
	}
	//Puts the players move on the board
	
	private static String valueInputter(){
		System.out.println("Type your move as one three digit number(lrc)");
		String input = in.next();
		if(!(correctInput(input))|| input.length() >3 || input.length() < 3){
			System.out.println("illegal Input");
			return valueInputter();
		}
		int line = Integer.parseInt(input.substring(0,1));
		int row = Integer.parseInt(input.substring(1,2));
		int column = Integer.parseInt(input.substring(2,3));
		if(!board[line][row][column].equals("_")){
			System.out.println("That spot is taken");
			return valueInputter();
		}
			return input;
	}
	//Takes input, checks the position is taken, & the input is correct length
	
	private static boolean correctInput(String input){
		String[] values = {"1","2","3","0"};
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
	//checks if the input uses the correct values
	
	private static final int[][][] lines = {
		{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0   rows in each level     
		{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
		{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
		{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
		{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
		{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
		{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2    
		{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
		{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
		{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
		{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
		{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
		{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
		{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
		{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
		{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
		{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0   columns in each level  
		{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1   
		{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
		{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
		{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
		{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
		{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
		{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
		{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
		{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
		{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
		{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
		{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
		{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
		{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
		{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
		{{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //cols in vert plane in front
		{{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
		{{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
		{{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
		{{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols in vert plane one back
		{{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
		{{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
		{{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
		{{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane two back
		{{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
		{{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
		{{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
		{{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
		{{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
		{{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
		{{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
		{{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
		{{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
		{{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
		{{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
		{{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
		{{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
		{{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
		{{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
		{{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane in front
		{{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
		{{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane one back
		{{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
		{{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane two back
		{{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
		{{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane in rear
		{{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
		{{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
		{{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
		{{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
		{{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
		{{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right      
		{{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
		{{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
		{{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
		{{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
		{{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
		{{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
		{{3,3,0},{2,2,1},{1,1,2},{0,0,3}}        
	};
	//an array of all the possible combinations
	
	private static void computerAi(){
		int index = computerWinSit();
		if(index == 0){index = stopPlayerWinSit();}
		if(index == 0){index = createForkSit();}
		if(index == 0){index = stopForkSit();}
		if(index == 0){defaultSit();}
	}
	//puts together all the computers move in response to the situation
	
	private static int[] lineValues(){
		int[] temp = new int[lines.length];
		for(int i = 0; i < lines.length; i++){
			for(int j = 0; j < lines[i].length;j++){
				int l = lines[i][j][0];
				int r = lines[i][j][1];
				int c = lines[i][j][2];
				if(board[l][r][c].equals("X")){
					temp[i] += 1;
				}
				else if(board[l][r][c].equals("O")){
					temp[i] += 10;
				}
			}
		}
		return temp;
	}
	//an array of values of all the combination based on if it O or X
	
	private static int computerWinSit(){
		int temp = -1;
		for(int i = 0; i < lineValues().length ;i++){
			if(lineValues()[i] == 30){
				temp = i;
				break;
			}
		}
		if(!(temp == -1)){
			for(int i = 0; i < lines[temp].length; i++){
				int l = lines[temp][i][0];
				int r = lines[temp][i][1];
				int c = lines[temp][i][2];
				if(board[l][r][c].equals("_")){
					board[l][r][c] = "O";
					return 1;
				}
			}
		}
		return 0;
	}
	//plays winning position if the computer has a winning play
	
	private static int stopPlayerWinSit(){
		int temp = -1;
		for(int i  = 0; i < lineValues().length; i++){
			if(lineValues()[i] == 3){
				temp = i;
				break;
			}
		}
		if(!(temp == -1)){
			for(int i = 0; i < lines[temp].length; i++){
				int l = lines[temp][i][0];
				int r = lines[temp][i][1];
				int c = lines[temp][i][2];
				if((board[l][r][c].equals("_"))){
					board[l][r][c] = "O";
					return 1;
				}
			}
		}
		return 0;
	}
	//blocks the player winning position if the player has a winning play
	
	private static int createForkSit(){
		int numSits = 0;
		for(int i = 0; i < lineValues().length;i++){
			if(lineValues()[i] == 20){numSits++;}
		}
		if(numSits == 0){return 0;}
		int[] forkSits = new int[numSits];
		int temp = 0;
		for(int i = 0; i < lineValues().length;i++){
			if(lineValues()[i] == 20){
				forkSits[temp] = i;
				temp++;
			}
		}
		
		for(int i = 0; i < forkSits.length-1; i++){
			for(int j = i+1;j < forkSits.length; j++){
				for(int k = 0; k < lines[forkSits[i]].length;k++){
					for(int m = 0; m < lines[forkSits[j]].length; m++){
						if(Arrays.equals(lines[forkSits[i]][k],lines[forkSits[j]][m])){
							int l = lines[forkSits[i]][k][0];
							int r = lines[forkSits[i]][k][1];
							int c = lines[forkSits[i]][k][2];
							if((board[l][r][c]).equals("_")){
								board[l][r][c] = "O";
								return 1;
							}
						}
					}
				}
			}
		}
		
		return 0;
	}
	//plays forking position if the computer has a possible fork
	
	private static int stopForkSit(){
		int numTwos = 0;
		for(int i = 0; i < lineValues().length;i++){
			if(lineValues()[i] == 2){numTwos++;}
		}
		if(numTwos == 0){return 0;}
		int[] valuesofTwo = new int[numTwos];
		int temp = 0;
		for(int i = 0; i < lineValues().length;i++){
			if(lineValues()[i] == 2){
				valuesofTwo[temp] = i;
				temp++;
			}
		}
		
		for(int i = 0; i < valuesofTwo.length-1; i++){
			for(int j = i+1;j < valuesofTwo.length; j++){
				for(int k = 0; k < lines[valuesofTwo[i]].length;k++){
					for(int m = 0; m < lines[valuesofTwo[j]].length; m++){
						if(Arrays.equals(lines[valuesofTwo[i]][k],lines[valuesofTwo[j]][m])){
							int l = lines[valuesofTwo[i]][k][0];
							int r = lines[valuesofTwo[i]][k][1];
							int c = lines[valuesofTwo[i]][k][2];
							if((board[l][r][c]).equals("_")){
								board[l][r][c] = "O";
								return 1;
							}
						}
					}
				}
			}
		}
		
		return 0;
	}
	//blocks player forking position if the player has a possible fork
	
	private static boolean declareDraw(){
		boolean draw = true;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				for(int k = 0; k < board[i][j].length; k++){
					if(board[i][j][k].equals("_")){
						draw = false;
					}
				}
			}
		}
		return draw;
	}
	//if board is full, the games declares a draw
	
	private static void defaultSit(){
		Random random = new Random();
		int l = random.nextInt(4);
		int r = random.nextInt(4);
		int c = random.nextInt(4);
		if(board[l][r][c].equals("_")){
			board[l][r][c] = "O";
		}
		else{
		  defaultSit();
		}
	}
	//computers plays a random position if there's no other possible plays
}