package edu.hw10.task2.entity;

import edu.hw10.task2.annotation.Cache;

public interface BasicCalculator {

    @Cache(persist = true)
    int add(int a, int b);

    @Cache(persist = true)
    int subtract(int a, int b);

    @Cache(persist = true)
    int multiply(int a, int b);

    @Cache(persist = true)
    int divide(int a, int b);
}
