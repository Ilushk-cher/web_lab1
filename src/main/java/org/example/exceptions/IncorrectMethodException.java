package org.example.exceptions;

public class IncorrectMethodException extends Exception {
    public IncorrectMethodException() {
        super("Incorrect request method");
    }
}
