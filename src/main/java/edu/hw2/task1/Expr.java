package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }

        @Override public String toString() {
            return String.valueOf(value);
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }

        @Override public String toString() {
            return "-(" + expr + ")";
        }
    }

    record Exponent(Expr expr, int power) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(expr.evaluate(), power);
        }

        @Override public String toString() {
            return "(" + expr + "^" + power + ")";
        }
    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }

        @Override public String toString() {
            return "(" + expr1 + "+" + expr2 + ")";
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }

        @Override public String toString() {
            return "(" + expr1 + "*" + expr2 + ")";
        }
    }
}
