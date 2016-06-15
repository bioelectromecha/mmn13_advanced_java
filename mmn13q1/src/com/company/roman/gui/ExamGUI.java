package com.company.roman.gui;


import com.company.roman.ExamActivity;

import javax.swing.*;
import java.awt.*;

/**
 * the main UI class.
 * Assembles the JPanel subcomponents and displays JFrame
 */
public class ExamGUI extends JFrame {

    // all ui components hold a reference to ExamActivity for easier communiation
    private ExamActivity mExamActivity;
    // this panel holds the questions text, answer buttons and text, and the result text
    private MultChoicePanel mMultChoicePanel;
    // navigation panel with all the buttons
    private BottomNavPanel mBottomPanel;

    /**
     * main and only constructor
     * @param examActivity ExamActivity object
     */
    public ExamGUI(ExamActivity examActivity){
        //set the exam com.company.roman.data
        mExamActivity = examActivity;

        //set the layout for this windows
        this.setLayout( new GridBagLayout());

        //center the frame on screen
        this.setLocationRelativeTo(null);

        //exit program on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title for the exam windows
        this.setTitle("Multiple Choice Exam");

        //instantiate the panels
        mMultChoicePanel = new MultChoicePanel(mExamActivity);
        mBottomPanel = new BottomNavPanel(mExamActivity);

        //constraints to place panels in frame
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // add multchoice panel to frame
        gridBagConstraints.gridy=0;
        this.add(mMultChoicePanel, gridBagConstraints);

        //add navigation to bottom
        gridBagConstraints.gridy=1;
        this.add(mBottomPanel,gridBagConstraints);

        // repacks components so they show up nicely together
        this.pack();

        // set this window to be visible
        this.setVisible(true);
    }

    /**
     * tells sub-panels to update data and then repaints the UI
     */
    public void update(){
        //rebind data to components and make changes as needed
        mMultChoicePanel.update();
        mBottomPanel.update();
        //recalculate component layout - for components changes
        revalidate();
        //redraw ui
        repaint();
    }

    /**
     * called when by examActivity when user finished the exam
     * @param results Strings with the exam results
     */
    public void onFinish(String results){
        //display the exam result
        mMultChoicePanel.onFinish(results);
        mBottomPanel.onFinish();
        //had to repack because extra components caused ui resizing issues
        pack();
        //we don't call update methods because panels call update onFinish
        revalidate();
        repaint();
    }


}
