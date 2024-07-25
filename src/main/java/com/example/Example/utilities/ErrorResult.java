package com.example.Example.utilities;

public class ErrorResult extends Result {
    public ErrorResult(String message) {
        super(false, message);
    }
}
