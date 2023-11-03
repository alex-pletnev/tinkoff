package edu.hw4.validate;

public class AgeError extends ValidationError {
    private static final int ERROR_CODE = 1;


    @Override
    public int getCode() {
        return ERROR_CODE;
    }

    @Override
    public String getFieldName() {
        return "age";
    }
}
