package edu.hw10.task2.entity;

public class FibCalculatorImpl implements FibCalculator {

    @Override
    public long fib(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }

        long result;
        if (number == 0 || number == 1) {
            result = number;
        } else {
            result = fib(number - 1) + fib(number - 2);
        }

        return result;
    }
}
