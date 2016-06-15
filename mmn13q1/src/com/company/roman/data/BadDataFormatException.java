package com.company.roman.data;

/**
 * custom Exception for parsing .txt data
 */
public class BadDataFormatException extends Exception {
    public BadDataFormatException() { super(); }
    public BadDataFormatException(String message) {
        super(message);
    }
}
