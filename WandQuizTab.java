/*
 * FILE NAME: WandQuizTab.java
 * @author Minhal Gardezi and Joyce Zhao
 * WHAT: Creates the GUI tab containing the Wand Quiz.
 * Designed for quiz questions with four possible choices.
 * NOTE: Minhal created this class.
 * All parts of the class are functioning properly.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class WandQuizTab extends JPanel{
  //INSTANCE VARIABLES
  private JLabel question; //current quiz question
  private JButton[] choiceButtons; //multiple choice answers
  private JPanel north, center;
  private WandQuiz quiz; //instance of the wand quiz
  private Vector<QuizQuestion> questions; //the quiz's questions
  
  /*
   * Constructor. Assembles main panel from component parts.
   * @param  q the quiz to be displayed on this panel
   */
  public WandQuizTab(WandQuiz q){
    quiz = q;
    questions = quiz.getQuestions();
    setLayout(new BorderLayout(10, 10));
    setBackground(Color.black);

    //add north question panel
    north = northPanel();
    add(north, BorderLayout.NORTH);
    
    //add center choices panel
    center = centerPanel();
    add(center, BorderLayout.CENTER);
  }
  
  /*
   * Creates the panel holding the current quiz question.
   * @return  the north panel holding the current quiz question
   */
  public JPanel northPanel(){
    JPanel north = new JPanel();
    north.setBackground(Color.black);
    question = new JLabel("<html><strong>" + questions.firstElement().getQuestion() + "</strong></html>");
    question.setForeground(Color.yellow);
    north.add(question);
    return north;
  } 
  
  /*
   * Creates the panel holding the multiple choice buttons.
   * @return  the center panel holding the multiple choice buttons
   */
  public JPanel centerPanel(){
    JPanel center = new JPanel();
    center.setBackground(Color.black);
    center.setLayout(new GridLayout(2,2));
    
    choiceButtons = new JButton[4];
    Vector<String> choices = questions.firstElement().getChoices();
    for(int i = 0; i < 4; i++){
      choiceButtons[i] = new JButton(choices.get(i));
      choiceButtons[i].addActionListener(new ButtonListener());
      center.add(choiceButtons[i]);
    }
    return center;
  }
  
  /*
   * Private helper class determining outcome of button clicks.
   * Allows use to progress through questions. When there are
   * no more questions, the user's chosen wand is displayed.
   */
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      if(event.getSource() == choiceButtons[0]){
        quiz.answer("Holly wood, 11\", with Phoenix Feather core");
      }
      if(event.getSource() == choiceButtons[1]){
        quiz.answer("Vine wood, 10.75\", with Dragon Heartstring core");
      }
      if(event.getSource() == choiceButtons[2]){
        quiz.answer("Ash wood, 12\", with Unicorn Hair core");
      }
      if(event.getSource() == choiceButtons[3]){
        quiz.answer("Cherry wood, 13\", with Unicorn Hair core");
      }
      
      questions.remove(0);
      if(!questions.isEmpty()){
        question.setText("<html><strong>" + questions.firstElement().getQuestion() + "</strong></html>");
        Vector<String> choices = questions.firstElement().getChoices();
          for(int i = 0; i < 4; i++){
            choiceButtons[i].setText(choices.get(i));
          }
      }
      else{
        center.removeAll();
        question.setText("<html><center><h1><h2><h3><strong><big>Your wand is:<br>" + quiz.getResult() 
                           + "</big></strong></h3></h2></h1></center></html>");
      }
    }
  }
  
}