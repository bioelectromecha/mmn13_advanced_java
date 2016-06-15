package com.company.roman.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mostly a data holder class.
 * holds a single question and has some additional functionality
 */
public class QuestionDataWrapper {

    //question
    private  String mQuestion;
    private String mCorrectAnswer;
    //all answers string array (including the correct one)
    private ArrayList<String> mAnswerList;

    /**
     * main and only constructor
     */
    public QuestionDataWrapper(){
        // initialize some empty stuff
        mQuestion ="";
        mCorrectAnswer ="";
        mAnswerList = new ArrayList<String>();
    }

    /**
     * retun the question string
     * @return
     */
    public String getQuestion() {
        return mQuestion;
    }

    /**
     * set new question instead of existing
     * @param question String question
     */
    public void setQuestion(String question) {
        mQuestion = question;
    }

    /**
     * return the answer list
     * @return Arralist<String> with the answer strings
     */
    public ArrayList<String> getAnswerList() {
        return mAnswerList;
    }

    /**
     * set the answer list. randomizes the input arraylist.
     * @param answerList set the answers ArraList
     *
     */
    public void setAnswerList(ArrayList<String> answerList) {
        mAnswerList = answerList;
        randomizeAnswersList();
    }

    /**
     * randomize the answersArrayList
     */
    public void randomizeAnswersList(){
        Random random = new Random();
        //create temp list
        ArrayList<String> randomList = new ArrayList<>();
        //randomly remove element while makign the range smaller
        for(int r = mAnswerList.size(); r>0; r--){
            //arralist closes 'gaps' automatically
            randomList.add(mAnswerList.remove(random.nextInt(r)));
        }
        //alias the list to the temp list
        mAnswerList =randomList;
    }

    /**
     * return the correct answer string
     * @return
     */
    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    /**
     * sets the correct answer string
     * @param correctAnswer String
     */
    public void setCorrectAnswer(String correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }
}
