package com.company.roman.gui;

import com.company.roman.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 05/05/16.
 */

/**
 * This class is a UI panel which displays
 * the capital alphabet as clickable button-letters
 */
public class LettersPanel extends JPanel {
    //this is the object which communicates between the com.company.roman.gui and the com.company.roman.data
    Controller mController;
    //a list of all the buttons
    private List<JButton> mJButtonList;

    /**
     * main and only constructor
     * @param controller an instance of Controller class
     */
    public LettersPanel(Controller controller){

        //set the controller
        mController = controller;
        
        //instantiate the lists
        mJButtonList = new ArrayList<>();
        this.setLayout(new GridLayout(2,13));
        // 'A' is ascii 65, so 65+26 will be 'Z'
        for (char c='A'; c<='Z';c++){
            //cast to char, then cast char to a string
            JButton tempButton = new JButton(String.valueOf(c));
            tempButton.addActionListener(new ActionListener() {
                @Override
                //a button in the alphabet was clicked
                public void actionPerformed(ActionEvent e) {
                    //check if its a button
                    if(e.getSource() instanceof JButton){
                        JButton callBackButton = (JButton) e.getSource();
                        //disable the button
                        callBackButton.setEnabled(false);
                        //tell controller that button was clicked
                        mController.onButtonClicked(callBackButton.getText());
                    }
                }
            });
            mJButtonList.add(tempButton);
            //add button to the jpanel
            this.add(tempButton);
        }
        //set the panel to be visible
        this.setVisible(true);
    }

    /**
     * reset all the buttons back to enabled
     */
    public void reset(){
        for(JButton jButton: mJButtonList){
            jButton.setEnabled(true);
        }
    }

    /**
     * set all the button to be disable at
     */
    public void gameOver(){
        for(JButton jButton: mJButtonList){
            jButton.setEnabled(false);
        }
    }

}
