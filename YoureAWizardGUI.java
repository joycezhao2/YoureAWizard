/*
 * FILE NAME: WizardGUI.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Assembles the GUI for the You're a Wizard program.
 * NOTE: Joyce and Minhal both created this class. 
 * All parts of the class are functioning properly.
 */

import javax.swing.*; 
import java.awt.*;
import java.io.*;

public class YoureAWizardGUI {
  
  /*
   * Method to test the class and put all the different tabs in one pane. 
   * The user can switch between the tabs.
   */
  public static void main (String[] args) 
  { 
    try {
    JFrame frame = new JFrame ("You're A Wizard!"); 
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
    frame.getContentPane().setBackground(Color.BLACK);
    
    HouseQuiz hQuiz = new HouseQuiz("houseQuiz.txt");
    WandQuiz wQuiz = new WandQuiz("wandQuiz.txt");
    
    Adventure harryPotter = new Adventure(Adventure.fromFile("HPQuestions.txt"), Adventure.fromFile("HPResults.txt"));
    JTabbedPane tp = new JTabbedPane(); 
    tp.addTab ("About", new WelcomeTab(harryPotter)); 
    tp.addTab("Wand Quiz", new WandQuizTab(wQuiz));
    tp.addTab("House Quiz", new HouseQuizTab(hQuiz));
    tp.addTab ("Adventure", new AdventureTab(harryPotter)); 
    
    frame.setSize(1000, 300);
    frame.getContentPane().add(tp); 
    frame.setVisible(true); 
    }
    catch (FileNotFoundException e) {
      System.out.println(e);
    }
  } 
}