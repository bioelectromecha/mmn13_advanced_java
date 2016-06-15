package com.company.roman.gui;

import com.company.roman.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is responsible for updating and passing data to the GUI
 */
public class FrameHandler {

    //instance variables

    //this one holds the word with the chosen letters
    private WordPanel mWordPanel;
    // this one lets you choose/guess the letter in the word
    private LettersPanel mLettersPanel;
    //holds all the components
    private JFrame mFrame;
    // the hanging man drawing
    private HangmanPanel mHangmanPanel;
    //the results panel
    private GameOverPanel mGameOverPanel;
    //frame dimensions object
    private Dimension mDimension;

    public FrameHandler(Controller controller) {

        mDimension = new Dimension(500,500);
        mFrame = new JFrame();
        //exit program on close
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title for the exam windows
        mFrame.setTitle("Hangman Game");
        //set the frame layout
        mFrame.setLayout(new GridBagLayout());
        //set the frame size
        mFrame.setSize(mDimension);
        //don't allow resizing
        mFrame.setResizable(false);

        //instantiate the JPanels
        mLettersPanel = new LettersPanel(controller);
        mWordPanel = new WordPanel(controller.getDisplayList());
        mHangmanPanel=new HangmanPanel();
        //not visible by default
        mGameOverPanel=new GameOverPanel(controller);

        //center the frame on screen
        mFrame.setLocationRelativeTo(null);


        //layout positioning constraints
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // first row panel
        gridBagConstraints.gridy=0;
        mFrame.add(mLettersPanel,gridBagConstraints);

        //second row panel
        gridBagConstraints.gridy=1;
        mFrame.add(mWordPanel,gridBagConstraints);

        //third row panel
        gridBagConstraints.gridy=2;
        mFrame.add(mHangmanPanel,gridBagConstraints);

        //fourth row panel
        gridBagConstraints.gridy=3;
        mFrame.add(mGameOverPanel,gridBagConstraints);

        //resize to fit content
        mFrame.pack();

        //make the frame visible
        mFrame.setVisible(true);
    }

    /**
     * displays the gameoverpanel when game is lost
     */
    public void gameLost(){
        mGameOverPanel.gameLost();
        mLettersPanel.gameOver();
    }

    /**
     * displays the gameoverpanel when game is won
     */
    public void gameWon(){
        mGameOverPanel.gameWon();
        mLettersPanel.gameOver();
    }

    /**
     * updates all the views and repaints the UI
     * @param displayList the word with hidden letters to display
     * @param numOfBadGuesses number of wrong guesses made by the player
     */
    public void updateView(List<String> displayList, int numOfBadGuesses){
        mWordPanel.updateView(displayList);
        mHangmanPanel.updateView(numOfBadGuesses);
        mFrame.revalidate();
        mFrame.repaint();
    }

    /**
     * resets the UI
     */
    public void reset(){
        mLettersPanel.reset();
        mGameOverPanel.reset();

    }
}