package edu.hw4.validate;

public class AgeError extends ValidationError {

    @Override
    public int getCode() {
        return 1;
    }

    @Override
    public String getFieldName() {
        return "age";
    }
}
