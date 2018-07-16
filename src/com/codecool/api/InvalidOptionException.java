package com.codecool.api;

public class InvalidOptionException extends Exception{
    public InvalidOptionException(String message) {
        System.out.println(message);
    }
}
