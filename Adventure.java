/*
 * FILE NAME: Adventure.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: This class provides methods so that the user, who has a name, can traverse through a Harry Potter themed tree. 
 * NOTE: Joyce created this class.
 * All parts of the class are functioning properly.
 */

import java.util.*;
import java.io.*;

public class Adventure {
  //INSTANCE VARIABLES
  private String name;
  private HPDecisionTree<String> questions, choices;
  
  /*
   * Constructor to create a new Adventure object. 
   * The choices to answer each question in tree containing questions are located in the corresponding
   * children nodes of the tree containing results.
   * @param questions The decision tree holding questions to be asked to the user
   * @param choices The answers to each of the quesions.
   */
  public Adventure(HPDecisionTree<String> questions, HPDecisionTree<String> choices) {
    this.name = "no name yet";
    this.questions = questions;
    this.choices = choices;
  }
  
  /*
   * Returns the name of the user
   * @return name of the user
   */
  public String getName() {
    return this.name;
  }
  /*
   * Sets the name of the user
   * @param n name to be set
   */
  public void setName(String n) {
    this.name = n;
  }
  
  /*
   * Returns the tree containing possible results
   * @return results tree
   */
  public HPDecisionTree<String> getChoices() {
    return choices;
  }
  
  /*
   * Returns the tree containing questions
   * @return questions tree
   */
  public HPDecisionTree<String> getQuestions() {
    return questions;
  }
  
  /*
   * Read a .txt file and create an HPDecisionTree<String> from it.
   * @param textFile - the txt file to read
   * @return a tree created from the txt file
   * @throws FileNotFoundException if txt file is not found.
   */
  public static HPDecisionTree<String> fromFile(String textFile) throws FileNotFoundException {
    String[] list = loadFile(textFile);
    HPDecisionTree<String> family = new HPDecisionTree<String>(list[0]);

    for (int i = 0; i < (list.length/2)-1; i++) {
      int left = (i*2)+1;
      int right = (i*2)+2;
      if (list[left] != null & list[right] != null) {
        family.setLeft(list[i], list[left]);
        family.setRight(list[i], list[right]);
      }
      else break;
    }
    return family;
  }  
  
  /*
   * Read a .txt file and loads contents into an array by line.
   * @param inFileName - the txt file to read
   * @return a array of Strings created from the txt file
   * @throws FileNotFoundException if txt file is not found.
   */
  public static String[] loadFile(String inFileName) throws FileNotFoundException {
    try {
      String[] arr = new String[2];
      int count = 0;
      Scanner reader = new Scanner(new File(inFileName));
      while (reader.hasNext()) {
        if (count >= arr.length)
          arr = expandCapacity(arr);
        arr[count] = reader.nextLine();
        count++;
      }
      reader.close();
      return arr;
    }
    catch (FileNotFoundException ex) {
      System.out.println(ex);
      return new String[2];
    }
  }
  
  /*
   * Private helper method to expand the capacity of an array.
   * @param arr array to be scanned
   * @return array twice the size of the original, with the elements of the original copied over
   */
  private static String[] expandCapacity(String[] arr) {
    int size = arr.length;
    
    String[] larger = new String[size*2];
    for (int i = 0; i < size; i++)
      larger[i] = arr[i];
    
    return larger;
  }
  
  /*
   * Finds the index of an target result within the choices array. 
   * Returns -1 if the target is not in the array.
   * @param target the result to be found in the array
   * @return index of the target
   * 
   */
  public int indexOf(String target) {
    int index = java.util.Arrays.asList(choices.getArray()).indexOf(target);
    return index > -1 ? index : -1;
  }
  
  /*
   * Traverses one node through the question tree. 
   * If the user decides to go left, returns the contents of the left child (the next question to be asked).
   * If the user decides to go right, returns the contents of the right child (the next question to be asked).
   * @param question the current question to be asked
   * @param choice String indicating whether to go left or right
   * @return contents of desired child
   * 
   */
  public String askQuestion(String question, String choice) {
    if (choice.equals("left")) 
      return questions.getLeft(question);
    return questions.getRight(question);
  }
  
  /*
   * Traverses one node through the decision tree to return the results of a choice. 
   * If the user decides to go left, returns the contents of the left child (decision that was made).
   * If the user decides to go right, returns the contents of the right child (decision that was made).
   * @param event the current choice that was made
   * @param choice String indicating whether to go left or right
   * @return contents of desired child
   * 
   */
  public String makeDecision(String event, String choice) {
    if (choice.equals("left")) 
      return choices.getLeft(event);
    return choices.getRight(event);
  }
  
  /*
   * Returns the number of choices in the tree
   * @return size of the tree
   */
  public int size() {
    return choices.size();
  }
  
  /*
   * Determines if the current result is the end of the tree.
   * @return boolen indicating if current node is a leaf
   */
  public boolean atEnd(String current) {
    return ((this.indexOf(current)*2)+1 >= this.size());
  }
  
  /*
   * In-depth testing of this class was done using the YoureAWizardGUI, with the two Harry Potter themed question and 
   * result trees created from text files. Testing was done first on a small tree with height = 2. Gradually,
   * more levels were added to the tree until it's current state, with each of the methods tested.
   * Simple tests of some methods are done below. 
   */
  public static void main(String[] args) {
    try {
    Adventure test = new Adventure(fromFile("TestTree.txt"), fromFile("TestTree.txt"));
    System.out.println(test.getChoices());
    System.out.println("\nindex of 47 (5): " + test.indexOf("47"));
    System.out.println("size of tree (31): " + test.size());
    System.out.println("is 80 a leaf (true)? " + test.atEnd("80"));
    System.out.println("is 64 a leaf (false)? " + test.atEnd("64"));
    }
    catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }
}