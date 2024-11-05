package org.example.exceptions;

public class ParamsValidationException extends Exception {
    public ParamsValidationException() {
        super("Invalid params");
    }
}
