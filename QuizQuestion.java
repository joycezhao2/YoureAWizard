/*
 * FILE NAME: QuizQuestion.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: A class representing a quiz question with a question andand set of choices.
 * NOTE: Minhal created this class.
 * All parts of the class are functioning properly.
 */

import java.util.*;
import java.io.*;

public class QuizQuestion{
  //INSTANCE VARIABLES
  private String question;
  private Vector<String> choices;
  
  /*
   * Constructor 1. Creates a QuizQuestion with a given question and
   * an empty set of choices.
   * @param q the prompt for the question
   */
  public QuizQuestion(String q){
    question = q;
    choices = new Vector<String>();
  }
  
  /*
   * Constructor 2. Creates a QuizQuestion with a given question and
   * a given set of choices.
   * @param q the prompt for the question
   * @param c the Vector holding the choices for this question
   */
  public QuizQuestion(String q, Vector<String> c){
    question = q;
    choices = c;
  }
  
  /*
   * Constructor 3. Creates a QuizQuestion with a given question and
   * four possible choices.
   * @param q  the prompt for the question
   * @param c1 the first possible choice
   * @param c1 the second possible choice
   * @param c1 the third possible choice
   * @param c1 the fourth possible choice
   */
  public QuizQuestion(String q, String c1, String c2, String c3, String c4){
    question = q;
    choices = new Vector<String>();
    choices.add(c1);
    choices.add(c2);
    choices.add(c3);
    choices.add(c4);
  }
  
  /*
   * Adds a choice to this QuizQuestion's set of choices.
   * @param choice  the choice to be added to this QuizQuestion
   */
  public void addChoice(String choice){
    choices.add(choice);
  }
  
  /*
   * Returns the question of this QuizQuestion
   * @return the question of this QuizQuestion
   */
  public String getQuestion(){
    return question;
  }
  
  /**
   * Returns the choices of this QuizQuestion
   * 
   * @return the choices of this QuizQuestion
   */
  public Vector<String> getChoices(){
    return choices;
  }
  
  /*
   * Returns this QuizQuestion as a formatted string.
   * @return this QuizQuestion as a formatted string
   */
  public String toString(){
    String s = question;
    for(int i=0; i<choices.size(); i++){
      s+= "\n" + i + ": " + choices.get(i);
    }
    return s;
  }
  
  /*
   * Main method to test the class.
   */
  public static void main(String[] args){
    //test toString
    String q = "What is your favorite color?";
    Vector<String> choices = new Vector<String>();
    choices.add("Red");
    choices.add("Yellow");
    choices.add("Blue");
    choices.add("Green");
    
    QuizQuestion q1 = new QuizQuestion(q, choices);
    System.out.println(q1);
    
    //test reading from file
    Vector<QuizQuestion> quiz = new Vector<QuizQuestion>();
    try{
      Scanner scan = new Scanner(new File("houseQuiz.txt"));
      while(scan.hasNext()){
        String[] strs = scan.nextLine().split("#");
        QuizQuestion tempQ = new QuizQuestion(strs[0], strs[1], strs[2], strs[3], strs[4]);
        quiz.add(tempQ);
      }
    }
    catch(FileNotFoundException e){
    }
    
    for(int i = 0; i<quiz.size(); i++){
      System.out.println(quiz.get(i) + "\n");
    }
    
  }
}