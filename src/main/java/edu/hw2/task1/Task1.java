package edu.hw2.task1;

import org.apache.logging.log4j.Logger;
import static edu.hw2.task1.Expr.Addition;
import static edu.hw2.task1.Expr.Constant;
import static edu.hw2.task1.Expr.Exponent;
import static edu.hw2.task1.Expr.Multiplication;
import static edu.hw2.task1.Expr.Negate;

public class Task1 {
    private static final int TWO = 2;

    private static final int FOUR = 4;
    private final Logger logger;

    public Task1(Logger logger) {
        this.logger = logger;
    }

    public void calculate() {
        var two = new Expr.Constant(TWO);
        var four = new Expr.Constant(FOUR);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        logger.info(res + " = " + res.evaluate());
    }
}
