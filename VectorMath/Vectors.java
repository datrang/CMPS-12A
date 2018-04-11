/**
 * Assignment #5
 * This program calculate the different equations related to vectors.
 * 
 * Author: David Trang (datrang@ucsc.edu)
 */

import java.util.*;

public class Vectors {
  private static Scanner in = new Scanner(System.in);
  private static Vector vector1 = new Vector();
  private static Vector vector2 = new Vector();
  private static double scalar;
  
  public static void main(String[] args) {
    boolean inProgress = true;
    while(inProgress){
      userPrompt();
      int choice = Integer.parseInt(userInput(0));
      if(choice == 1){
        System.out.println("Vector Addition");
        vectorChange(2);
        vector1.sum(vector2).printVector();
      }
      else if(choice == 2){
        System.out.println("Vector Subtraction");
        vectorChange(2);
        vector1.difference(vector2).printVector();
      }
      else if(choice == 3){
        System.out.println("Vector's Magnitude");
        vectorChange(1);
        System.out.println(vector1.magnitude());
      }
      else if(choice == 4){
        System.out.println("Scalar Multipication");
        vectorChange(3);
        vector1.scalarProduct(scalar).printVector();
      }
      else if(choice == 5){
        System.out.println("Dot Product");
        vectorChange(2);
        System.out.println(vector1.dotProduct(vector2));
      }
      else if(choice == 6){
        System.out.println("Angle Between Vectors");
        vectorChange(2);
        System.out.println(vector1.angleBetweenVectors(vector2));
      }
      else if(choice == 7){
        System.out.println("Thank you for your time.");
        inProgress = false;
      }
   }
    in.close();
  }
  
  public static void userPrompt(){
    vector1 = new Vector();
    vector2 = new Vector();
    System.out.println("Welome to Basic Vector Math. What do you want to do?");
    System.out.println();
    System.out.println("1. Sum");
    System.out.println("2. Difference");
    System.out.println("3. Magnitude");
    System.out.println("4. Scalar Multipication");
    System.out.println("5. Dot Product");
    System.out.println("6. The Angle Between Two Vectors");
    System.out.println("7. Exit");
  }

  public static void vectorChange(int numVectors){
    double tempX1,tempY1, tempX2, tempY2;
    if(numVectors == 1){
      System.out.println("Enter Vector 1 x-value");
      tempX1 = Double.parseDouble(userInput(1));
      System.out.println(tempX1);
      System.out.println("Enter Vector 1 y-value");
      tempY1 = Double.parseDouble(userInput(1));
      vector1 = new Vector(tempX1, tempY1);
    }
    else if(numVectors ==2){
      System.out.println("Enter Vector 1 x-value");
      tempX1 = Double.parseDouble(userInput(1));
      System.out.println("Enter Vector 1 y-value");
      tempY1 = Double.parseDouble(userInput(1));
      System.out.println("Enter Vector 2 x-value");
      tempX2 = Double.parseDouble(userInput(1));
      System.out.println("Enter Vector 2 y-value");
      tempY2 = Double.parseDouble(userInput(1));
      vector2 = new Vector(tempX2, tempY2);
      vector1 = new Vector(tempX1, tempY1);
    }
    else{
      System.out.println("Enter Vector 1 x-value");
      tempX1 = Double.parseDouble(userInput(1));
      System.out.println("Enter Vector 1 y-value");
      tempY1 = Double.parseDouble(userInput(1));
      vector1 = new Vector(tempX1, tempY1);
      System.out.println("Enter your scalar");
      scalar = Double.parseDouble(userInput(1));
    }
  }
  
  public static String userInput(int mode){
    boolean correct = false;
    if(mode == 0){
      String[] values = {"1","2","3","4","5","6","7"};
      String input = in.next();
      if(input.length() > 1){
        System.out.println("Incorrect Input");
        return userInput(0);
      }
      for(int i = 0; i < values.length; i++){
        if(input.equals(values[i])){
          correct = true;
          break;
        }
      }
      if(correct){return input;}
      else{
        System.out.println("Incorrect Input");
        return userInput(0);
        }
    }
    else if(mode == 1){
      String[] values = {"0","1","2","3","4","5","6","7","8","9","-",};
      String input = in.next();
      for(int i = 0; i < input.length(); i++){
        for(int j = 0; j < values.length; j++){
          if(input.substring(i,i+1).equals(values[j])){
            correct = true;
            break;
          }
          else if(!(input.substring(i,i+1).equals(values[j]))){
            correct = false;
          }
        }
      }
      if(correct){return input;}
      else{
        System.out.println("Incorrect Input");
        return userInput(1);}
    }
    return "0";
  }
}

class Vector{
  private double xValue;
  private double yValue;
  public Vector(){}
  
  public Vector(double x, double y){
    this.xValue = x;
    this.yValue = y;
  }
  
  public Vector sum(Vector vector2){
    printVector();
    vector2.printVector();
    return (new Vector(xValue + vector2.getX(), yValue + vector2.getY()));
  }
  
  public Vector difference(Vector vector2){
    return (new Vector(xValue - vector2.getX(), yValue - vector2.getY()));
  }
  
  public double magnitude(){
    return Math.abs(Math.sqrt(((xValue)*(xValue)) + ((yValue)*(yValue))));
  }
  
  
  public Vector scalarProduct(double scalar){
    return new Vector(scalar * xValue, scalar * yValue);
  }
  
  public double dotProduct(Vector vector2){
    return (xValue * vector2.getX()) + (yValue * vector2.getY());
  }
  
  public double angleBetweenVectors(Vector vector2){
    return Math.acos((dotProduct(vector2)/(magnitude()*vector2.magnitude())));
  }
  
  public void printVector(){
    System.out.println("(" + (xValue) + ", " + (yValue) + ")");
  }
  
  public double getX(){
    return xValue;
  }
  
  public double getY(){
    return yValue;
  }
}
