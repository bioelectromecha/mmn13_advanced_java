package com.company.roman.data;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * This class holds the data and is responsible
 * for the logic of the hangman game
 */
public class DataHandler {

    //instance variables

    //list of all the words from the file
    private List<String> mWordList;
    //current selected word
    private String mCurrentWord;
    //number of bad guesses
    private int mBadGuessNum;
    //list of all the guessed letters
    private List<String> mGuessedCharacters;

    //constants

    //the seperating character between words in the text file
    private final String DELIMITER = ",";
    //the "hidden" letter which the player needs to guess
    private final String HIDDEN_LETTER = "_";
    //maximum number of wrong guesses - after which the player losses the game
    private final int MAX_GUESS_NUM=10;

    /**
     * main and only constructor
     */
    public DataHandler(){
        //instantiate instance variables
        mBadGuessNum=0;
        mWordList = new ArrayList<>();
        mGuessedCharacters = new ArrayList<>();

        // scan the file for words separated by DELIMITER
        try {
            Scanner scanner = new Scanner(new File("words.txt"));
            scanner.useDelimiter(DELIMITER);
            while (scanner.hasNext()){
                //make sure all words are only uppercase and there are not blank spaces
                mWordList.add(scanner.next().toUpperCase().replaceAll(" ",""));
            }
            //close the file stream
            scanner.close();
            //if file can't be found, opened, read, etc...
        }catch (Exception e){
            //print error and exit
            System.out.println("ERROR READING OR OPENNING FILE");
            System.out.println("Terminating program...");
            exit(0);
        }
        //if no words were read
        if(mWordList.size()==0){
            //print error and exit
            System.out.println("ERROR NO WORDS FOUND IN FILE");
            System.out.println("Terminating program...");
            exit(0);
        }
        generateRandomWord();
    }

    /**
     * generates a random word from the list of words
     * sets it as the current word
     */
    private void generateRandomWord(){
        Random random = new Random();
        //a random number within the list index range
        mCurrentWord = mWordList.get(random.nextInt(mWordList.size()-1));
    }

    /**
     * generates and returns the display list
     * @return List<String> a list of String characters
     */
    public List<String> getDisplayList(){
        List<String> wordDisplayList = new ArrayList<>();
        //go over all letters in the current word
        for ( char c: mCurrentWord.toCharArray()) {
            //if the letter was guessed, add it to the display list
            if(mGuessedCharacters.contains(String.valueOf(c))){
                wordDisplayList.add(String.valueOf(c));
            }else{
                //if the letter wasn't guessed, hide it with a HIDDEN_LETTER
                wordDisplayList.add(HIDDEN_LETTER);
            }
        }
        return wordDisplayList;
    }

    /**
     * reset the data and generates a random word
     */
    public void reset(){
        generateRandomWord();
        mGuessedCharacters.clear();
        mBadGuessNum=0;
    }

    /**
     * called when a guess was made
     * @param c a guessed letter in string format
     */
    public void addGuessedCharacter(String c){
        if(!mCurrentWord.contains(c)){
            mBadGuessNum++;
        }
        mGuessedCharacters.add(c);
    }

    /**
     * returns the number of bad guesses made
     * @return int the number of bad guesses made
     */
    public int getNumOfBadGuesses(){
        return mBadGuessNum;
    }

    /**
     * gets the list of letteres that were already guessed
     * @return List<String> list of guessed letters
     */
    public List<String> getGuessedLetterList(){
        return mGuessedCharacters;
    }

    /**
     * is the game lost?
     * @return boolean lost or not lose
     */
    public boolean isGameLost(){
        if(mBadGuessNum==MAX_GUESS_NUM){
            return true;
        }
        return false;
    }

    /**
     * is the game won?
     * @return boolean won or not won
     */
    public boolean isGameWon(){
        //if there is not even on HIDDEN_LETTER then the game is won
        if(getDisplayList().contains(HIDDEN_LETTER)){
            return false;
        }
        return true;
    }
}