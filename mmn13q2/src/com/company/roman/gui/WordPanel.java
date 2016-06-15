package com.company.roman.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class displays the word with the hidden letters
 */
public class WordPanel extends JPanel {

    private String mWord;
    private List<JLabel> mJLabelList;


    /**
     * main and only constructor
     * @param displayList List of strings of the word to be displayed
     */
    public WordPanel(List<String> displayList){
        mJLabelList = new ArrayList<>();
        //iterate over all the string in the list of letters
        for (String s : displayList) {
            JLabel tempLabel = new JLabel(s);
            mJLabelList.add(tempLabel);
            //add all the labels to the panel
            this.add(tempLabel);
        }
        //set some styling and make visible
        this.setOpaque(true);
        this.setBorder( new EmptyBorder(30,30,30,30));
        this.setVisible(true);
    }

    /**
     * updates the display
     * @param displayList the new list of letter to display
     */
    public void updateView(List<String> displayList){
        for(JLabel jLabel: mJLabelList){
            //in case the list is empty
            if(displayList.size()==0){
                return;
            }
            //set the text and remove the string from the list
            jLabel.setText(displayList.remove(0));
        }
    }

}
