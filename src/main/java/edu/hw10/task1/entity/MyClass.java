package edu.hw10.task1.entity;

import edu.hw10.task1.anotation.Max;
import edu.hw10.task1.anotation.Min;
import edu.hw10.task1.anotation.NotNull;

public class MyClass {


    @Max(value = 100)
    private Integer fieldMax;
    @Min(value = 0)
    private Integer fieldMin;

    @Min(3)
    @NotNull
    private String fieldNotNull;
    private String justField;

    public MyClass() {
    }

    public MyClass(Integer fieldMax, Integer fieldMin, String fieldNotNull, String justField) {
        this.fieldMax = fieldMax;
        this.fieldMin = fieldMin;
        this.fieldNotNull = fieldNotNull;
        this.justField = justField;
    }

    public Integer getFieldMax() {
        return fieldMax;
    }

    public void setFieldMax(Integer fieldMax) {
        this.fieldMax = fieldMax;
    }

    public Integer getFieldMin() {
        return fieldMin;
    }

    public void setFieldMin(Integer fieldMin) {
        this.fieldMin = fieldMin;
    }

    public String getFieldNotNull() {
        return fieldNotNull;
    }

    public void setFieldNotNull(String fieldNotNull) {
        this.fieldNotNull = fieldNotNull;
    }

    public String getJustField() {
        return justField;
    }

    public void setJustField(String justField) {
        this.justField = justField;
    }

    public static MyClass create(Integer fieldMax, Integer fieldMin, String fieldNotNull, String justField) {
        return new MyClass(fieldMax, fieldMin, fieldNotNull, justField);
    }

    @Override public String toString() {
        return "MyClass{"
            + "fieldMax=" + fieldMax
            + ", fieldMin=" + fieldMin
            + ", fieldNotNull='" + fieldNotNull + '\''
            + ", justField='" + justField + '\''
            + '}';
    }
}
