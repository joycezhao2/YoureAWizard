/*
 * FILE NAME: WelcomeTab.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Creates the GUI tab containing the welcome screen. Here, the user can enter their name.
 * NOTE: Joyce created this class.
 * All parts of the class are functioning properly.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeTab extends JPanel {
  //INSTANCE VARIABLES
  private Adventure a;
  private JTextField nameTextField;
  private JButton nextButton;
  private JPanel north, proceed, leftPics, rightPics; 
  private JLabel instruct, message, result, leftBolt, rightBolt, leftMinhal, rightJoyce;
  
  /*
   * Constructor. Assembles main panel from component parts.
   * @param a the Adventure to be displayed on this panel
   */
  public WelcomeTab(Adventure a) { 
    this.a = a;
    
    setLayout(new BorderLayout());
    
    nameTextField = new JTextField();
   
    proceed = new JPanel(); 
    proceed.setBackground(Color.BLACK);
    message = new JLabel("");
    message.setForeground(Color.YELLOW);
    proceed.add(message);
    nextButton = new JButton("Enter Name");
    proceed.add(nextButton);
   
    north = makeNorthPanel();
    add(north, BorderLayout.NORTH);
    add(proceed, BorderLayout.CENTER);
    
    nextButton.addActionListener(new ButtonListener());
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
   * Creates the panel welcoming the user and details of the project
   * @return the north panel holding the current adventure question
   */
  private JPanel makeNorthPanel() {
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout(10, 10));
    centerPanel.setBackground(Color.BLACK);
    
    String instructText = "<html><center>Bring Your Harry Potter Adventure to Life!<br>"
                                    + "Designed by Minhal Gardezi (sgardezi) and Joyce Zhao (jzhao2) for CS 230."
                                    + "<br>-------<br>To embark on your journey, please enter your name.</center><html>";
    
    ImageIcon bolt = createImageIcon("bolt.png", "a lightning bolt image");
    ImageIcon boltFlip = createImageIcon("boltflip.png", "a lightning bolt image");
    ImageIcon minhal = createImageIcon("minhal.png", "a photo of Minhal");
    ImageIcon joyce = createImageIcon("joyce.png", "a photo of Joyce");

    instruct = new JLabel(instructText);
    instruct.setHorizontalAlignment(JLabel.CENTER);
    instruct.setForeground(Color.YELLOW);
    
    leftBolt = new JLabel(bolt);
    rightBolt = new JLabel(boltFlip);
    leftMinhal = new JLabel(minhal);
    rightJoyce = new JLabel(joyce);
    
    leftPics = new JPanel();
    leftPics.setLayout(new BorderLayout(100, 100));
    leftPics.setBackground(Color.BLACK);
    leftPics.add(leftBolt, BorderLayout.CENTER); 
    leftPics.add(leftMinhal, BorderLayout.EAST);
    
    rightPics = new JPanel();
    rightPics.setLayout(new BorderLayout(100, 100));
    rightPics.setBackground(Color.BLACK);
    rightPics.add(rightJoyce, BorderLayout.CENTER); 
    rightPics.add(rightBolt, BorderLayout.EAST);
    
    centerPanel.add(leftPics, BorderLayout.WEST);
    centerPanel.add(instruct, BorderLayout.CENTER);
    centerPanel.add(rightPics,BorderLayout.EAST);
    centerPanel.add(nameTextField,BorderLayout.SOUTH);
    
    return centerPanel;
  }
  
  /*
   * Private helper class determining outcome after entering a name.
   * When the user sucessfully enters a name, a message with further instructions is displayed.
   */
  private class ButtonListener implements ActionListener {
    private String name; 
    
    public void actionPerformed (ActionEvent event)
    {
      this.name = nameTextField.getText();
      
      if (name.isEmpty())
        message.setText("Please enter your name to continue!");
      
      else {
        a.setName(name);
        north.remove(nameTextField);
        message.setText("<html><br><h1><center><strong>Welcome, " + a.getName() + "! You're a Wizard!<strong><h1><br>"
                       +"To continue, click on the tabs above to choose a wand, Hogwarts house,<br>and to adventure through"
                       +" the wonderful world of wizarding!</center><html>");   
        proceed.remove(nextButton);
      } 
    }
  }
  
}
