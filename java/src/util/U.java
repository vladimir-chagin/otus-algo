package util;

import java.util.Arrays;

public final class U {
    public static final <T> T[] increaseArray(final T[] array, final int increaseCapacityBy) {
        final T[] newArr = (T[]) new Object[array.length + increaseCapacityBy];

        if (array.length > 0) {
            System.arraycopy(array, 0, newArr, 0, array.length);
        }

        return newArr;
    }

    public static final <T> T[] increaseArray(final T[] array, final int increaseCapacityBy, final int emptyIdx) {
        final T[] newArray = newArray(array.length + increaseCapacityBy);

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

    public static <T> void shiftItemsFromIndex(final T[] array, final int idx, final int size) {
        if (size >= array.length) {
            throw new RuntimeException("Not enough space");
        }

        if (idx > size) {
            throw new RuntimeException("Index: " + idx + " greater than size: " + size);
        } else if (idx < size) {
            System.arraycopy(array, idx, array, idx + 1, size - idx - 1);
        }
    }

    public static <T> T[] removeItemAndDecreaseCapacity(final T[] array, final int idx, final int size) {
        final int newSize = size-1;
        if (newSize <= 0) {
            return newArray(0);
        }

        final T[] newArray = newArray(newSize);

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

    public static <T> void removeItem(final T[] array, final int idx, final int size) {
        if (idx == array.length - 1) {
            array[idx] = null;
        } else {
            System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
            array[size-1] = null;
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

    public static <T> T[] newArray(final int n) {
        return (T[]) new Object[n];
    }

    //mix - if mix is zero array will be sorted, if mix is 0.5 - array will be half sorted
    //if mix < 0 sorted items will be at the end
    public static void fillArrayWithRandomIntegers(Integer[] array) {
        for (int i = 0; i < array.length; i += 1) {
            array[i] = Integer.valueOf(i);
        }
    }

    public static void fillArrayWithRandomLongs(final long[] array, final int offset, final int length, final long min, final long max) {
        if (offset < 0 || offset >= array.length) {
            throw new RuntimeException("Invalid offset: " + offset + "; [" + array.length + "]");
        }

        final int lastIdx = Math.min(array.length - offset, length) + offset - 1;

        for (int i = offset; i <= lastIdx; i += 1) {
            array[i] = U.randomLong(min, max);
        }
    }

    public static void fillArrayWithRandomInts(final int[] array, final int offset, final int length, final int min, final int max) {
        if (offset < 0 || offset >= array.length) {
            throw new RuntimeException("Invalid offset: " + offset + "; [" + array.length + "]");
        }

        final int lastIdx = Math.min(array.length - offset, length) + offset - 1;

        for (int i = offset; i <= lastIdx; i += 1) {
            array[i] = U.randomInt(min, max);
        }
    }

    public static<T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static<T> void shuffleArray(T[] array, double mix) {
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

    public static<T> String arrayToString(T[] array) {
        return "[" + array.length + "]: " + Arrays.toString(array);
    }

    public static boolean compareStrings(String s1, String s2) {
        if (s1 == s2) {
            return true;
        }

        if (s1 == null || s2 == null) {
            return false;
        }

        return s1.equals(s2);
    }

    public static void assertEquals(String[] data1, String[] data2) {
        if (data2 == data1) {
            return;
        }

        if (data1 == null || data2 == null) {
            throw new RuntimeException("Null output data");
        }

        if (data1.length != data2.length) {
            throw new RuntimeException("Different lengths");
        }

        for (int i = 0; i < data2.length; i += 1) {
            if (!U.compareStrings(data1[i], data2[i])) {
                throw new RuntimeException("line[" + i + "]" + data1[i] + " != " + data2[i]);
            }
        }
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printArray(Object[] array) {
        System.out.println(Arrays.toString(array));
    }

    int max(int a, int b) {
        return a >= b ? a : b;
    }
}
