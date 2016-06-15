package com.company.roman;

import com.company.roman.gui.FrameHandler;
import com.company.roman.data.DataHandler;

import javax.swing.*;
import java.util.List;

/**
 * This is the main class
 * reponsible for mediating between the com.company.roman.data model and the GUI
 */
public class Controller {

    //data and logic stuff
    private DataHandler mDataHandler;
    // GUI stuff
    private FrameHandler mFrameHandler;

    /**
     * The main method of the hangman game
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller();
            }
        });
    }


    /**
     * main and only constructor
     */
    public Controller(){
        mDataHandler = new DataHandler();
        mFrameHandler = new FrameHandler(this);
    }

    /**
     * method is called when one of the letters is pressed
     * @param btnText the letter on pressed jbutton
     */
    public void onButtonClicked(String btnText){
        //add the guessed letter to the list in the data class
        mDataHandler.addGuessedCharacter(btnText);
        //check for game lost or won conditions and respond accordingly
        if(mDataHandler.isGameLost()){
            mFrameHandler.gameLost();
        }
        if(mDataHandler.isGameWon()){
            mFrameHandler.gameWon();
        }
        //update the gui
        mFrameHandler.updateView(mDataHandler.getDisplayList(), mDataHandler.getNumOfBadGuesses());
    }

    /**
     * get the word to be displayed as a list of letters
     * @return List<String> word to display
     */
    public List<String> getDisplayList(){
        return mDataHandler.getDisplayList();
    }

    /**
     *  called when RETRY button was clicked
     */
    public void onRetryClicked(){
        //reset the data and the gui
        mDataHandler.reset();
        mFrameHandler.reset();
        //update the gui
        mFrameHandler.updateView(mDataHandler.getDisplayList(), mDataHandler.getNumOfBadGuesses());
    }
}