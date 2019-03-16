package com.nekitoss;

/**
 * Created by mpochuka on 3/16/19.
 */
public class MyMath {
    public static double abs(double a) {
        return ((a < 0) ? a * (-1) : a);
    }

    public static double div_d(double a, double b)
            throws java.lang.ArithmeticException {
        if (b == 0) {
            throw new java.lang.ArithmeticException("ERROR");
        }
        double absa = MyMath.abs(a);
        double absb = MyMath.abs(b);

        double product = 0;
        int x = 0;
        while (product + absb <= absa) {
            product += absb;
            x++;
        }

        if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
            return x;
        } else {
            return ((-1) * x);
        }
    }

    public static double sqrt(double number) {
        if (number == 0)
            return 0;
        double t;

        double squareRoot = number / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    public static double div(double a, double b) {
        return (a * (1 / b));
    }
}
