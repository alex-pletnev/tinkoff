package edu.hw4.validate;

public class WeightError extends ValidationError {
    private static final int ERROR_CODE = 3;

    @Override
    public int getCode() {
        return ERROR_CODE;
    }

    @Override
    public String getFieldName() {
        return "weight";
    }
}
