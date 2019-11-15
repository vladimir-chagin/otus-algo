package task3;

import util.Performance;
import util.U;
import task3.impl.Fibonacci;
import task3.impl.GCD;

public class Program {
    private static final long MIN_A = 1_000_000L;
    private static final long MAX_A = MIN_A * 1_000_000;
    private static final long MIN_B = 1_000_000_000L;
    private static final long MAX_B = MIN_B * 1000_000;

    private static final int ITERATIONS = 1000000;


    private static final int FIB_N = 10000;
    private static final int FIB_N_MAX = 50;

    public static void main(String[] args) {
        testGCD();

//        testFibonacci(FIB_N);
    }


    private static void testGCD() {
        System.out.println("iterations: " + ITERATIONS + ";");
        System.out.println(MIN_A + " < a < " + MAX_A);
        System.out.println(MIN_B + " < b < " + MAX_B);

        Performance.measure("GCD via subtraction", () -> {
            testSubGCD();
        });
        Performance.measure("GCD via modulo", () -> {
            testModGCD();
        });
        Performance.measure("GCD via Binary GCD alg", () -> {
            testBinGCD();
        });
    }

    private static void testSubGCD() {
        for (int i = 0; i < ITERATIONS; i += 1) {
            final long a = U.randomNumber(MIN_A, MAX_A);
            final long b = U.randomNumber(MIN_B, MAX_B);

            final long subGcd = GCD.euclidSub(a, b);
        }
    }

    private static void testModGCD() {
        for (int i = 0; i < ITERATIONS; i += 1) {
            final long a = U.randomNumber(MIN_A, MAX_A);
            final long b = U.randomNumber(MIN_B, MAX_B);

            final long modGcd = GCD.euclidMod(a, b);
        }
    }

    private static void testBinGCD() {
        for (int i = 0; i < ITERATIONS; i += 1) {
            final long a = U.randomNumber(MIN_A, MAX_A);
            final long b = U.randomNumber(MIN_B, MAX_B);

            final long binGcd = GCD.binaryGcd(a, b);
        }
    }

    private static void testFibonacci(final int n) {
        final int iterations = 10000;
        int step = 5;

        for (int i = 1; i < n ; i += step) {
            final int j = i;
            System.out.println("Calculating " + i + "-th Fibonacci number");

            if (i < FIB_N_MAX) {
                Performance.measure("Recursive Fibonacci number", () -> {
                    long fib = Fibonacci.recursive(j);
                });
            } else {
                step = 1000;
            }

            Performance.measure("Recursive Fibonacci number with memo", () -> {
                for (int k = 1; k < iterations; k += 1) {
                    long fib = Fibonacci.recursiveMemo(k);
                }
            });

            Performance.measure("Iterative Fibonacci number", () -> {
                for (int k = 1; k < iterations; k += 1) {
                    long fib = Fibonacci.iterative(k);
                }
            });

            Performance.measure("Golden ratio Fibonacci number", () -> {
                for (int k = 1; k < iterations; k += 1) {
                    long fib = Fibonacci.phi(k);
                }
            });

            Performance.measure("Matrix exp Fibonacci number", () -> {
                for (int k = 1; k < iterations; k += 1) {
                    long fib = Fibonacci.matrix(k);
                }
            });
            System.out.println("----------------------");
        }
    }
}
