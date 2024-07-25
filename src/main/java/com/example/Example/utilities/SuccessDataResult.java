package com.example.Example.utilities;

public class SuccessDataResult<T> extends DataResult {

    public SuccessDataResult(String message, Object data) {
        super(true, message, data);
    }

    public SuccessDataResult(Object data) {
        super(true, data);

    }
}
