package com.company.roman.gui;

import com.company.roman.ExamActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is responsible for drawing the top half of the ui:
 * the question text, the answers and their buttons, and the exam result at the top
 */
public class MultChoicePanel extends JPanel{

    // all ui components hold a reference to ExamActivity for easier communiation
    private ExamActivity mExamActivity;
    // the question display text area component
    private JTextArea mQuestionTextArea;
    // the exam result label component
    private JLabel mResultLabel;
    // arrayList of the radio buttons and their text
    private ArrayList<JRadioButton> mJRadioButtonsArrayList;
    // button group - so only one radiobutton can be seleceted at a time
    private ButtonGroup mButtonGroup;
    // toggle to enable the after-finish functionallity
    private boolean mFinished = false;
    // default color of text, used to redraw colors because we're reusing components
    private Color mDefaultColor;

    /**
     * main and only constructor
     * @param examActivity ExamActivity object
     */
    public MultChoicePanel(ExamActivity examActivity){
        super();

        //save the default background color
        mDefaultColor = this.getBackground();

        mExamActivity = examActivity;

        // box layouts allows for vertical allignment of the buttons
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        mJRadioButtonsArrayList= new ArrayList<JRadioButton>();

        //create a button for every answer text
        for(String answerFromList : mExamActivity.getCurrentQuestion().getAnswerList()){
            mJRadioButtonsArrayList.add(new JRadioButton(answerFromList));
        }


        // create a buttons group - so only a single button could be selected at a time
        mButtonGroup = new ButtonGroup();

        // iterate over the the jradiobutton
        for(final JRadioButton jRadioButton : mJRadioButtonsArrayList){
            //add each button to the button group
            mButtonGroup.add(jRadioButton);
            //add action (i.e click) listener to each button
            jRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //if exam is not finished
                    if(!mFinished){
                        //return the answer chosen by the user
                        mExamActivity.setUserAnswer(jRadioButton.getText());
                    }
                }
            });
        }

        //create the text area with the relevant question
        mQuestionTextArea = new JTextArea(mExamActivity.getCurrentQuestion().getQuestion());
        mResultLabel = new JLabel("");

        this.add(mResultLabel);
        this.add(mQuestionTextArea);

        //add the buttons to jpanel
        for(JRadioButton jRadioButton: mJRadioButtonsArrayList){
            this.add(jRadioButton);
        }
        this.setVisible(true);
    }

    /**
     * update the textareas and buttons with new data
     * containts after-finish functionality such as answer coloring
     */
    public void update(){
        mQuestionTextArea.setText(mExamActivity.getCurrentQuestion().getQuestion());
        for(int i=0;i<mJRadioButtonsArrayList.size();i++){
            mJRadioButtonsArrayList.get(i).setText(mExamActivity.getCurrentQuestion().getAnswerList().get(i));
        }
        //this is a toggle to enable after-finish functionality
        if(mFinished == true){
            for(JRadioButton jRadioButton: mJRadioButtonsArrayList){
                //reset button color - avoid color 'leaks' from other questions on same component
                jRadioButton.setBackground(mDefaultColor);
                //color the users answer in red
                if(jRadioButton.getText().equals(mExamActivity.getUserAnswer())){
                    jRadioButton.setBackground(Color.RED);
                }
                //color the correct answer in magenta
                if(jRadioButton.getText().equals(mExamActivity.getCurrentQuestion().getCorrectAnswer())){
                    jRadioButton.setBackground(Color.MAGENTA);
                }
                // if the user chose correctly or didn't choose at all- only magenta will show
                // this functionality is independent of result generation
            }
        }
    }

    /**
     * called when user finishes an exam
     * @param result a string with number of correct answers, some text, etc
     */
    public void onFinish(String result){
        //display the exam result
        mResultLabel.setText(result);
        //enlarge the font by 50%
        mResultLabel.setFont(mResultLabel.getFont().deriveFont((float)1.5*mResultLabel.getFont().getSize()));
        // change text color
        mResultLabel.setForeground(Color.MAGENTA);
        //enabled after-finish functionality
        mFinished = true;
        update();
    }
}
