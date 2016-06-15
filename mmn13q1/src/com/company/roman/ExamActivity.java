package com.company.roman;

import com.company.roman.data.DataLoader;
import com.company.roman.data.QuestionDataWrapper;
import com.company.roman.gui.ExamGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.exit;

/**
 * This is the main class for the multiple choice exam program
 * This class is responsible for mediating between the UI and the data model
 * @author Roman Smirnov 312914443
 * @since  04/05/2016
 *
 */
public class ExamActivity{

    //The main method for the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExamActivity();
            }
        });

    }
    //private variable declarations

    //this is an arraylist that holds all the question data
    private ArrayList<QuestionDataWrapper> mQuestionDataWrapperArrayList;
    // UI view reference
    private ExamGUI mExamGUI;
    // index of the current viewed question
    private int mCurrentQuestionIndex;
    //key-value map to store the user's answers as strings, uses question index as key
    private HashMap<Integer,String> mUserAnswerList;

    /**
     * Main (and only) constructor
     */
    public ExamActivity(){

        /* the try catch here will exit the program if
            there's a problem with either the input file
            or the text itself
        */
        try {
            mQuestionDataWrapperArrayList = DataLoader.loadExamData();
        }catch (Exception e){
            //exit program
            System.out.println(e.getMessage());
            System.out.println("couldn't create exam activity... Exiting...");
            exit(0);
        }
        mCurrentQuestionIndex=0;
        // create the UI
        mExamGUI = new ExamGUI(this);
        mUserAnswerList = new HashMap<Integer,String>();
    }
    /**
     * get the currently displayed question
     * @return mQuestionDataWrapper object
     */
    public QuestionDataWrapper getCurrentQuestion(){
        return mQuestionDataWrapperArrayList.get(mCurrentQuestionIndex);
    }

    /**
     *  get the last question in the array
     * @return boolan last or not last
     */
    public boolean isLast(){
        if(mCurrentQuestionIndex==mQuestionDataWrapperArrayList.size()-1) {
            return false;
        }
        return true;
    }

    /**
     *  get the first question in the array
     * @return boolan first or not first
     */
    public boolean isFirst(){
        if(mCurrentQuestionIndex==0){
            return true;
        }
        return false;
    }

    /**
     * advance to next question - increment index and update the ui with new data
     */
    public void nextQuestion(){
        if(isLast()){
            return;
        }
        mCurrentQuestionIndex++;
        //i.e we update the data in each UI component and then revalidate and repaint
        mExamGUI.update();
    }

    /**
     * advance to previous question - increment back the index and update the ui with new data
     */
    public void prevQuestion(){
        if(isFirst()){
            return;
        }
        mCurrentQuestionIndex--;
        mExamGUI.update();
    }

    /**
     * save the users answer as a keyvalue pair in hash
     * @param answer the string with the user's answer-choice
     */
    public void setUserAnswer(String answer){
        //we use put and not add because its a hash
        mUserAnswerList.put(mCurrentQuestionIndex,answer);
    }

    /**
     * The user has pressed the 'finish exam' button
     * calculate the results and display to user
     * see the private calcExamResuts() method
     */
    public void finishExam(){
        //see the camExamResultsMethod
        mExamGUI.onFinish(calcExamResults());
    }

    /**
     * returns the total number and number of correct answers in friendly string
     * @return  string with exam results
     */
    private String calcExamResults(){
        //number of correct answers
        double correctAnsNum=0;
        //compare to find how many correct answers
        for(int i=0; i<mQuestionDataWrapperArrayList.size();i++){
            if(mQuestionDataWrapperArrayList.get(i).getCorrectAnswer().equals(mUserAnswerList.get(i))){
                correctAnsNum++;
            }
        }
        //concatenate to get the result string
        return "You have answered "
                +correctAnsNum+" out of "
                + mQuestionDataWrapperArrayList.size()+" correctly. Your score is "
                // used (int) Math.round to get rounding instead of truncating
                + (int) Math.round(correctAnsNum/mQuestionDataWrapperArrayList.size()*100);
    }

    /**
     * return the user's answer to the currently displayed question
     * @return  String answer
     */
    public String getUserAnswer(){
        return mUserAnswerList.get(mCurrentQuestionIndex);
    }

    /**
     * Reset everything and restart
     */
    public void retry(){
        // close the current GUI
        mExamGUI.setVisible(false);
        mExamGUI.dispose();
        //clear the answers hash
        mUserAnswerList.clear();
        //re-randomize the answers to each question
        for(QuestionDataWrapper q: mQuestionDataWrapperArrayList) {
            q.randomizeAnswersList();
        }
        //reset question index
        mCurrentQuestionIndex=0;
        //create new GUI
        mExamGUI = new ExamGUI(this);
    }
}
