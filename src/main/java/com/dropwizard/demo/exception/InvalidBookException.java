package com.dropwizard.demo.exception;

public class InvalidBookException extends Exception {

    private static final long serialVersionUID = -1054459959807597105L;

    public InvalidBookException(String message) {
            super(message);
        }
}


