package task3.impl;

public class GCD {
    /*
    * calc GCD via subtraction
    * */
    public static long euclidSub(long a, long b) {
        while(a != 0 && b != 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a == 0 ? b : a;
    }

    /*
     * calc GCD via modulo
     * */
    public static long euclidMod(long a, long b) {
        while(a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a == 0 ? b : a;
    }

    /*
     * binary GCD implementation
     * */
    public static long binaryGcd(long a, long b) {
        long g = 1;

        while(a % 2 == 0 && b % 2 == 0) {
            a >>= 1;
            b >>= 1;
            g <<= 1;
        }

        while(a > 0) {
            if (a % 2 == 0) {
                a >>= 1;
            } else if (b % 2 == 0) {
                b >>= 1;
            } else {
                long t = Math.abs(a - b) >> 1;
                if (a < b) {
                    b = t;
                } else {
                    a = t;
                }
            }
        }

        return b * g;
    }
}
