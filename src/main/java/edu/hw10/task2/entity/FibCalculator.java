package edu.hw10.task2.entity;

import edu.hw10.task2.annotation.Cache;

public interface FibCalculator {

    @Cache(persist = true)
    int fib(int number);
}
