package edu.hw4.validate;

public class HeightError extends ValidationError {

    @Override
    public int getCode() {
        return 2;
    }

    @Override
    public String getFieldName() {
        return "height";
    }
}
