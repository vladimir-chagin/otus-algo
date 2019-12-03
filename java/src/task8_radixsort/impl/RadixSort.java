package task8_radixsort.impl;

import task3.impl.Pow;

public class RadixSort {
    public static void sort(final int[] a) {
        final int[] digits = new int[10];
        final int[] b = new int[a.length];

        for (byte digitPos = 0; digitPos < digits.length; digitPos += 1) {
            for (int n: a) {
                byte d = digitAt(n, digitPos);
                digits[d] += 1;
            }

            for (byte i = 1; i < digits.length; i += 1) {
                digits[i] += digits[i - 1];
            }

            for (int i = a.length - 1; i >= 0; i -= 1) {
                byte d = digitAt(a[i], digitPos);
                digits[d]--;
                b[digits[d]] = a[i];
            }
            for(byte i = 0; i < digits.length; i += 1) {
                digits[i] = 0;
            }

            System.arraycopy(b, 0, a, 0, a.length);
        }

    }

    private static byte digitAt(int number, int i) {
        return (byte)((number / (int) Pow.iterative(10, i)) % 10);
    }

}
