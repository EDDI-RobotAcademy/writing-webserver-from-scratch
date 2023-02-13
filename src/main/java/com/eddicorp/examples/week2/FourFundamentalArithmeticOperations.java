package com.eddicorp.examples.week2;

public class FourFundamentalArithmeticOperations {

    public long add(long a, long b) {
        return a + b;
    }

    public long sub(long a, long b) {
        return a - b;
    }

    public long mul(long a, long b) {
        return a * b;
    }

    public double div(long a, long b) {
        if (b == 0) {
            throw new IllegalArgumentException("b cannot be null.");
        }
        return (double) a / (double) b;
    }
}
