package util;

import java.util.Arrays;

public final class IntU {

    public static int[] increaseArray(final int[] array, final int increaseCapacityBy) {
        final int[] newArr = new int[array.length + increaseCapacityBy];

        if (array.length > 0) {
            System.arraycopy(array, 0, newArr, 0, array.length);
        }

        return newArr;
    }

    public static int[] increaseArray(final int[] array, final int increaseCapacityBy, final int emptyIdx) {
        final int[] newArray = new int[array.length + increaseCapacityBy];

        if (array.length > 0) {
            if (emptyIdx == 0) {
                System.arraycopy(array, 0, newArray, 1, array.length);
            } else if (emptyIdx == array.length) {
                System.arraycopy(array, 0, newArray, 0, array.length);
            } else {
                System.arraycopy(array, 0, newArray, 0, emptyIdx);
                System.arraycopy(array, emptyIdx, newArray, emptyIdx + 1, array.length - emptyIdx);
            }
        }

        return newArray;
    }

    public static void shiftItemsFromIndex(final int[] array, final int idx, final int size) {
        if (size >= array.length) {
            throw new RuntimeException("Not enough space");
        }

        if (idx == size) {
            return;
        } else if (idx == 0) {
            System.arraycopy(array, 0, array, 1, size);
        } else {
            System.arraycopy(array, 0, array, 0, idx);
            System.arraycopy(array, idx, array, idx + 1, array.length - idx - 1);
        }
    }

    public static int[] removeItemAndDecreaseCapacity(final int[] array, final int idx, final int size, final int decreaseCapacityBy) {
        final int newSize = size > decreaseCapacityBy ? size - decreaseCapacityBy : size - 1;
        if (newSize <= 0) {
            return new int[0];
        }

        final int[] newArray = new int[newSize];

        if (idx == 0) {
            System.arraycopy(array, 1, newArray, 0, newSize);
        } else if (idx == newSize) {
            System.arraycopy(array, 0, newArray, 0, idx);
        } else {
            System.arraycopy(array, 0, newArray, 0, idx);
            System.arraycopy(array, idx + 1, newArray, idx, newSize - idx);
        }

        return newArray;
    }

    public static void removeItem(final int[] array, final int idx, final int size) {
        if (idx == size - 1) {
            array[idx] = 0;
        } else {
            System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
            array[size-1] = 0;
        }
    }

    public static long randomLong(final long min, final long max) {
        final long d = max - min + 1;
        final long r = (long)Math.floor(Math.random() * d) + min;
        return r;
    }

    public static int randomInt(final long min, final long max) {
        return (int)randomLong(min, max);
    }

    public static void swap(final int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static int indexOfMax(int[] array) {
        int maxIdx = 0;

        for (int i = 1; i < array.length; i += 1) {
            if (array[i] > array[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    public static void shuffleArray(int[] array, double mix) {
        final int sign = (int)Math.signum(mix);
        final int cnt = (int)(array.length * mix * sign);

        if (sign == 0 || cnt == 0) {
            return;
        }
        for (int i = 0; i < cnt-1; i += 1) {
            int j = randomInt(i + 1, cnt-1);
            int k = i;
            if (sign < 0) {
                k = array.length - i -1;
                j = array.length - j - 1;
            }
            swap(array, j, k);
        }
    }

    public static void fillArrayWithRandomNumbers(final int[] array, int min, int max) {
        for (int i = 0; i < array.length; i += 1) {
            array[i] = randomInt(min, max);
        }
    }

    public static String arrayToString(final int[] array) {
        return "[" + array.length + "]: " + Arrays.toString(array);
    }

}
