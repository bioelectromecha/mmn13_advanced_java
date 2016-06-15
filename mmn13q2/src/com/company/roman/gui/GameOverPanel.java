package com.company.roman.gui;

import com.company.roman.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel that shows up at the end of a game
 */
public class GameOverPanel extends JPanel {

    //the label that tells the player whether they lost or won
    private JLabel mResultLabel;
    //the retry button that lets you play again
    private JButton mRetryButton;
    //Controller instance reference to call reset()
    private Controller mController;

    /**
     * main and only constructor
     * @param controller Controller class instance
     */
    public GameOverPanel(Controller controller){
        //instiate components
        mController = controller;
        mResultLabel=new JLabel();
        mRetryButton = new JButton("RETRY");
        mRetryButton.addActionListener(new ActionListener() {
            //call onRetryClick() controller method to reset the game
            @Override
            public void actionPerformed(ActionEvent e) {
                mController.onRetryClicked();
            }
        });
        //add components to the panel and make it NOT VISIBLE
        this.add(mResultLabel);
        this.add(mRetryButton);
        this.setVisible(false);
    }

    /**
     * reset the panel
     */
    public void reset(){
        mResultLabel.setText("");
        this.setVisible(false);
    }

    /**
     * makes the panel visible with the game won text
     */
    public void gameWon(){
        mResultLabel.setText("YOU'VE WON! GOOD JOB!");
        this.setVisible(true);
    }

    /**
     * makes the panel visible with the game lost text
     */
    public void gameLost(){
        mResultLabel.setText("YOU'VE LOST! TOO BAD");
        this.setVisible(true);
    }
}
