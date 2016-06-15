package com.company.roman.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to load the data from .txt file into the java object container
 */
public class DataLoader {

    // windows .txt line break delimiters
    // change to \r\r if creating files on mac, or \n\n on linux
    private static final String WINDOWS_BLOCK_DELIMITER ="\r\n\r\n";
    private static final String WINDOWS_LINE_DELIMITER ="\r\n";

    /**
     * Parse the .txt file and load the data object into an arraylist
     * @return  ArrayList<QuestionDataWrapper>
     * @throws BadDataFormatException problem with the file or text format in it
     */
    public static ArrayList<QuestionDataWrapper> loadExamData() throws BadDataFormatException {
        ArrayList<QuestionDataWrapper> questionDataWrapperArrayList = new ArrayList<QuestionDataWrapper>();
        try {
            // create blocks of text seperated by a double newline
            Scanner scanner = new Scanner(new File("exam.txt"));
            scanner.useDelimiter(WINDOWS_BLOCK_DELIMITER);
            while (scanner.hasNext()) {
                //seperate block of text into lines seperated by a signle newline
                questionDataWrapperArrayList.add(loadQuestionData(scanner.next()));
            }
            scanner.close();
        }catch (Exception e){
            throw new BadDataFormatException("something went wrong with the exam build");
        }
        return questionDataWrapperArrayList;
    }

    /**
     * private method that parses a question textblock and returns a QuestionDataWrapper object
     * @param textBlock a block of text to create a question from
     * @return QuestionDataWrapper object
     * @throws BadDataFormatException will be thrown if textblock is not constructed right
     */
    private static QuestionDataWrapper loadQuestionData(String textBlock) throws BadDataFormatException{
        //parse the textblock into individual lines
        ArrayList<String> textLines = new ArrayList<String>(Arrays.asList(textBlock.split(WINDOWS_LINE_DELIMITER)));

        //if the number of lines is not correct - throw excpetion
        if(textLines.size() > 5 || textLines.size() < 5){
            throw new BadDataFormatException("QuestionDataWrapper must consist of 5 lines; has: "+textLines.size()+" lines");
        }
        //QuestionDataWrapper object
        QuestionDataWrapper questionDataWrapper = new QuestionDataWrapper();

        //add the questionDataWrapper text to the QuestionDataWrapper object, while removing it from the arraylist
        questionDataWrapper.setQuestion(textLines.remove(0));
        questionDataWrapper.setCorrectAnswer(textLines.get(0));
        //set the answers list as the ArrayList
        questionDataWrapper.setAnswerList(textLines);

        return questionDataWrapper;
    }

}
