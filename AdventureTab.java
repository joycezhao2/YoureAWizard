/*
 * FILE NAME: AdventureTab.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Creates the GUI tab containing the Choose Your Adventure activity.
 * Designed to be used on full binary trees.
 * NOTE: Joyce created this class.
 * All parts of the class are functioning properly.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdventureTab extends JPanel {
  //INSTANCE VARIABLES
  private Adventure w;
  private JTextField nameTextField;
  private JButton left, right, quit, again;
  private JPanel welcome, question, choice; 
  private JLabel intro, ask, result;
  
  /*
   * Constructor. Assembles main panel from component parts.
   * @param w the Adventure to be displayed on this panel
   */
  public AdventureTab(Adventure w) {
    this.w = w;
    
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);
    
    // create the welcome statement panel
    welcome = new JPanel();
    welcome.setBackground(Color.BLACK);
    intro = new JLabel("Time to embark on a journey through the wonderful world of wizarding!");
    intro.setForeground(Color.YELLOW);
    welcome.add(intro);
    
    // create the south panel containing to buttons to play again or quit
    choice = new JPanel();
    choice.setBackground(Color.BLACK);
    
    again = new JButton("Play Again");
    again.setPreferredSize(new Dimension(100, 30));
    again.setBackground(Color.green);
    again.setOpaque(true);
    
    quit = new JButton("Quit");
    quit.setPreferredSize(new Dimension(60, 30));
    quit.setBackground(Color.red);
    quit.setOpaque(true);
    
    choice.add(again);
    choice.add(quit);
    
    add(welcome, BorderLayout.NORTH);
    add(makeCenterPanel(), BorderLayout.CENTER);
    add(choice, BorderLayout.SOUTH);
    
    quit.addActionListener(new ButtonListener());
    again.addActionListener(new ButtonListener());
    left.addActionListener(new ButtonListener());
    right.addActionListener(new ButtonListener());
  }
  
  /*
   * Creates the panel holding a question and the choices to answer the question
   * @return the north panel holding the current adventure question
   */
  private JPanel makeCenterPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout(10, 10));
    centerPanel.setBackground(Color.BLACK);
    
    ask = new JLabel(w.getQuestions().getRoot());
    ask.setForeground(Color.YELLOW);
    ask.setHorizontalAlignment(JLabel.CENTER);
    centerPanel.add(ask,BorderLayout.CENTER);
    
    question = new JPanel();
    question.setBackground(Color.BLACK);
    
    ImageIcon bolt = createImageIcon("bolt.png", "a lightning bolt image");
    
    left = new JButton(w.makeDecision(w.getChoices().getRoot(), "left"), bolt);
    right = new JButton(w.makeDecision(w.getChoices().getRoot(), "right"), bolt);
    question.add(left); 
    question.add(right);
    
    centerPanel.add(question, BorderLayout.SOUTH);
    
    return centerPanel;
  }
  
  /*
   * Creates and returns an ImageIcon out of an image file.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = Adventure.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  /*
   * Private helper class determining outcome of button clicks.
   * Allows use to progress through adventure tree. When there are no more questions left to ask, a message is displayed.
   */
  private class ButtonListener implements ActionListener {
    private String name; 
    
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == quit) 
        System.exit(0); 
      if (event.getSource() == again)  
        resetGUIandAdventure();
      
      String current = "";
      String currentQ = "";
      if (event.getSource() == left && !w.atEnd(left.getText())) {
        current = left.getText();
        currentQ = ask.getText();
        intro.setText("You chose " + current);
        ask.setText(w.askQuestion(currentQ, "left"));
        String lTree = w.makeDecision(current, "left");
        String rTree = w.makeDecision(current, "right");
        
        left.setText(lTree);
        right.setText(rTree);
      }
      else if (event.getSource() == right && !w.atEnd(right.getText())) {
        
        current = right.getText();
        currentQ = ask.getText();
        intro.setText("You chose " + current);
        ask.setText(w.askQuestion(currentQ, "right"));
        String lTree = w.makeDecision(current, "left");
        String rTree = w.makeDecision(current, "right");
        
        left.setText(lTree);
        right.setText(rTree);
      }
      else if (w.atEnd(right.getText()) || w.atEnd(left.getText())) {
        
        ask.setText("Congrats, you have reached the end of the adventure!");
        intro.setText(" ");
      }
    }
   /*
   * Private helper method to reset the GUI if the player wants to play again.
   */
    private void resetGUIandAdventure() {
      intro.setText("Time to embark on a journey through the wonderful world of wizarding!");
      ask.setText(w.getQuestions().getRoot());
      left.setText(w.makeDecision(w.getChoices().getRoot(), "left"));
      right.setText(w.makeDecision(w.getChoices().getRoot(), "right")); 
    }
  }
}