/*
 * FILE NAME: Quiz.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: An interface for a quiz. Requires all Quiz classes to create a method 
 * for answering questions and for determining a result.
 * NOTE: Minhal created this class.
 * All parts of the class are functioning properly.
 */

public interface Quiz{
  
  /*
   * Alters the state of the Quiz to reflect a user's response
   * to a question.
   * @param s  the String representing the user's response to a question
   */
  public void answer(String s);
  
  /*
   * Determines and returns the result of the quiz.
   * @return the result of the quiz
   */
  public String getResult();
}