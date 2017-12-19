/*
 * FILE NAME: HouseQuiz.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Implements the Quiz interface to create a Harry Potter house-sorting quiz.
 * Based on input from the GUI, the user will be sorted into one of four houses.
 * NOTE: Minhal created this class.
 * All parts of the class are functioning properly.
 */

import java.util.Hashtable;
import java.util.Vector;
import java.util.Scanner;
import java.io.*;

public class HouseQuiz implements Quiz{
  //INSTANCE VARIABLES
  private Hashtable<String, Integer> ranks;
  private Vector<QuizQuestion> questions;
  
  /*
   * Constructor. Initializes rank hashtable and
   * creates quiz questions from a file.
   * @param  fileName  the name of the .txt file from which the questions will be formed
   */
  public HouseQuiz(String fileName){
    ranks = new Hashtable<String, Integer>();
    ranks.put("Gryffindor", 0); //put four houses into hashtable
    ranks.put("Hufflepuff", 0);
    ranks.put("Ravenclaw", 0);
    ranks.put("Slytherin", 0);
    
    questions = new Vector<QuizQuestion>();
    try{ //create questions from file and put in vector
      Scanner scan = new Scanner(new File(fileName));
      while(scan.hasNext()){
        String[] strs = scan.nextLine().split("#");
        QuizQuestion tempQ = new QuizQuestion(strs[0], strs[1], strs[2], strs[3], strs[4]);
        questions.add(tempQ);
      }
    }
    catch(FileNotFoundException e){ //if file does not exist
      System.out.println(e);
    }
  }
  
  /*
   * A string corresponding to one of the four houses
   * is passed from GUI and that house's rank is
   * incremented in the hashtable.
   * @param  s  the house whose rank is incremented
   */
  public void answer(String s){
    ranks.put(s, ranks.get(s) + 1); 
  }
  
  /*
   * Returns the key containing the max value
   * from the house hashtable. Corresponds to the house
   * the user was sorted into.
   * @return  the name of the house the user was sorted into
   */
  public String getResult(){
    int max = 0;
    String result = "";
    for (String key : ranks.keySet()) {
      if (ranks.get(key) >= max) {
        max = ranks.get(key);
        result = key;
      }
    }
    return result;
  }
  
  /*
   * Returns the Vector containing this quiz's questions.
   * @return  the Vector containing this quiz's questions
   */
  public Vector<QuizQuestion> getQuestions(){
    return questions;
  }
 
  /*
   * Main method to test the class.
   */
  public static void main(String[] args){
    HouseQuiz quiz = new HouseQuiz("houseQuiz.txt");
    Scanner scan = new Scanner(System.in);
    
    while(!quiz.getQuestions().isEmpty()){
      System.out.println(quiz.getQuestions().firstElement());
      int choice = scan.nextInt();
      if(choice == 0)
        quiz.answer("Gryffindor");
      if(choice == 1)
        quiz.answer("Hufflepuff");
      if(choice == 2)
        quiz.answer("Ravenclaw");
      if(choice == 3)
        quiz.answer("Slytherin");
      quiz.getQuestions().remove(0);
    }
    
    System.out.println(quiz.getResult());
  }
}