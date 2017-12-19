/**
 * FILE NAME: WandQuiz.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Implements the Quiz interface to create a Harry Potter wand-matching quiz.
 * Based on input from the GUI, the user will be matched with one of four wands.
 * NOTE: Minhal created this class.
 * All parts of the class are functioning properly.
 */

import java.util.Hashtable;
import java.util.Vector;
import java.util.Scanner;
import java.io.*;

public class WandQuiz implements Quiz{
  //INSTANCE VARIABLES
  private Hashtable<String, Integer> ranks;
  private Vector<QuizQuestion> questions;
  
  /*
   * Constructor. Initializes rank hashtable and
   * creates quiz questions from a file.
   * @param  fileName  the name of the .txt file from which the questions will be formed
   */
  public WandQuiz(String fileName){
    ranks = new Hashtable<String, Integer>();
    ranks.put("Holly wood, 11\", with Phoenix Feather core", 0);
    ranks.put("Vine wood, 10.75\", with Dragon Heartstring core", 0);
    ranks.put("Ash wood, 12\", with Unicorn Hair core", 0);
    ranks.put("Cherry wood, 13\", with Unicorn Hair core", 0);
    
    questions = new Vector<QuizQuestion>();
    try{
      Scanner scan = new Scanner(new File(fileName));
      while(scan.hasNext()){
        String[] strs = scan.nextLine().split("#");
        QuizQuestion tempQ = new QuizQuestion(strs[0], strs[1], strs[2], strs[3], strs[4]);
        questions.add(tempQ);
      }
    }
    catch(FileNotFoundException e){
    }
  }
  
  /*
   * A string corresponding to one of the four wands is passed from GUI and that wands's rank is
   * incremented in the hashtable.
   * @param  s  the wands whose rank is incremented
   */
  public void answer(String s){
    ranks.put(s, ranks.get(s) + 1); 
  }
  
  /*
   * Returns the key containing the max value from the wand hashtable. 
   * Corresponds to the wand the user was matched with.
   * @return  the description of the wand the user was sorted into
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
    WandQuiz quiz = new WandQuiz("wandQuiz.txt");
    Scanner scan = new Scanner(System.in);
    
    while(!quiz.getQuestions().isEmpty()){
      System.out.println(quiz.getQuestions().firstElement());
      int choice = scan.nextInt();
      if(choice == 0)
        quiz.answer("Holly wood, 11\", with Phoenix Feather core");
      if(choice == 1)
        quiz.answer("Vine wood, 10.75\", with Dragon Heartstring core");
      if(choice == 2)
        quiz.answer("Ash wood, 12\", with Unicorn Hair core");
      if(choice == 3)
        quiz.answer("Cherry wood, 13\", with Unicorn Hair core");
      quiz.getQuestions().remove(0);
    }
    
    System.out.println(quiz.getResult());
  }
}