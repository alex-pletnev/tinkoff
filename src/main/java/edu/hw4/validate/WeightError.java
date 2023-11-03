package edu.hw4.validate;

public class WeightError extends ValidationError {

    @Override
    public int getCode() {
        return 3;
    }

    @Override
    public String getFieldName() {
        return "weight";
    }
}
