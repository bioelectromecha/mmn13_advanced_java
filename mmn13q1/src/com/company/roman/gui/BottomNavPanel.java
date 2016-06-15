package com.company.roman.gui;

import com.company.roman.ExamActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The bottom navigation panel UI component
 */
public class BottomNavPanel extends JPanel{

    // buttons
    JButton mBackButton;
    JButton mNextButton;
    JButton mFinishButton;
    JButton mRetryButton;

    //names for buttons
    public static final String BACK ="BACK";
    public static final String NEXT = "NEXT";
    public static final String FINISH = "FINISH";
    public static final String RETRY = "RETRY";

    // all ui components hold a reference to ExamActivity for easier communiation
    private ExamActivity mExamActivity;

    /**
     * main and only constructor
     * @param examActivity ExamActivity object
     */
    public BottomNavPanel(ExamActivity examActivity){
        super();

        mExamActivity = examActivity;

        mBackButton = new JButton(BACK);
        //back button should be disabled on start - because it's the first question
        mBackButton.setEnabled(false);
        //calls relevant method on examActivity
        mBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExamActivity.prevQuestion();
            }
        });

        mNextButton = new JButton(NEXT);
        mNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExamActivity.nextQuestion();
            }
        });
        //finishes the current exam session and shows results, etc...
        mFinishButton = new JButton(FINISH);
        mFinishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExamActivity.finishExam();
            }
        });
        //kills current session and start a new one with same data
        mRetryButton = new JButton(RETRY);
        //available only after finishExam is called
        mRetryButton.setEnabled(false);
        mRetryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mExamActivity.retry();
            }
        });

        //add buttons to jpanel
        this.add(mBackButton);
        this.add(mNextButton);
        this.add(mFinishButton);
        this.add(mRetryButton);

        this.setVisible(true);
    }

    /**
     * updates the .
     * basically toggles the buttons before repainting the JFrame
     */
    public void update(){
        //don't show the back button on first question
        if(mExamActivity.isFirst()){
            mBackButton.setEnabled(false);
            mNextButton.setEnabled(true);
            return;
        }
        // don't show the next button on last question
        if(mExamActivity.isLast()){
            mBackButton.setEnabled(true);
            mNextButton.setEnabled(false);
            return;
        }
        //show both buttons enabled when in middle
        mBackButton.setEnabled(true);
        mNextButton.setEnabled(true);
    }

    /**
     * when user finished exam - show the retry button and disable the finish button
     */
    public void onFinish(){
        mFinishButton.setText("Done!");
        mFinishButton.setEnabled(false);
        mRetryButton.setEnabled(true);
        update();
    }


}
