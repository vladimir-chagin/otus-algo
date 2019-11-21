package util;

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
    public static void fillArrayWithRandomNumbers(Integer[] array) {
        for (int i = 0; i < array.length; i += 1) {
            array[i] = Integer.valueOf(i);
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
}
