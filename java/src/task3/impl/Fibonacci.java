package task3.impl;

public class Fibonacci {
    private static final double PHI = 0.5 * (1 + Math.sqrt(5));
    private static final double SQRT5 = Math.sqrt(5);

    /*
    * calculates n-th fibonacci number recursively
    * */
    public static long recursive(final int n) {
        if (n < 2) {
            return 1;
        }

        return recursive(n - 2) + recursive(n - 1);
    }

    /*
    * calculates n-th fibonacci number recursively with memoization
    * */
    public static long recursiveMemo(final int n) {
        final long[] memo = new long[n + 1];
        memo[0] = memo[1] = 1;
        return recursive(n, memo);
    }

    private static long recursive(final int n, final long[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }

        long f = recursive(n-1, memo) + recursive(n-2, memo);
        memo[n] = f;
        return f;
    }

    /*
    * calculates n-th fibonacci number iteratively
    **/
    public static long iterative(final int n) {
        if (n < 2) {
            return 1;
        }

        long f1 = 1;
        long f2 = 1;
        long fn = 2;

        for (int i = 1; i < n; i += 1) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }

        return fn;
    }

    /*
    * calculate n-th fibonacci number by formula with golden ratio
    * */
    public static long phi(long n) {
        return (long) (Math.pow(PHI, n+1) / SQRT5 + 0.5) ;
    }


    /*
    * calculate n-th fibonacci number by 2x2 matrix exponentiation
    * */
    private static final long F00 = 1;
    private static final long F01 = 1;
    private static final long F10 = 1;
    private static final long F11 = 0;

    public static long matrix(long n) {
        long f00 = F00;
        long f01 = F01;
        long f10 = F10;
        long f11 = F11;


        for (int i = 0; i < n; i += 1) {
            long tmpF00 = F00 * f00 + F01 * f10;
            long tmpF01 = F00 * f01 + F01 * f11;
            long tmpF10 = F10 * f00 + F11 * f10;
            long tmpF11 = F10 * f01 + F11 * f11;

            f00 = tmpF00;
            f01 = tmpF01;
            f10 = tmpF10;
            f11 = tmpF11;
        }

//        System.out.println(f00 + " " + f01);
//        System.out.println(f10 + " " + f11);

        return f10;
    }

}
