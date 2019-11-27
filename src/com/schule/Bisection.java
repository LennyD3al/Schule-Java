package com.schule;

public class Bisection {

    public static double bisect(double a, double b) {
        double fa = f1(a), fb = f1(b);
        while ((b - a) > 1e-15) {
            double c = (a + b) / 2, fc = f1(c);
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
            if (Math.abs(fc) < 1e-12) return c;
        }

        return a;
    }

    private static double f1(double x) {
        return Math.pow(x, 2) - 2;
    }
}
