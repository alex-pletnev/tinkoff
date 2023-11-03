package edu.hw4.validate;

public class HeightError extends ValidationError {
    private static final int ERROR_CODE = 2;


    @Override
    public int getCode() {
        return ERROR_CODE;
    }

    @Override
    public String getFieldName() {
        return "height";
    }
}
