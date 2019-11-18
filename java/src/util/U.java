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

    public static long randomNumber(final long min, final long max) {
        final long d = max - min + 1;
        final long r = (long)Math.floor(Math.random() * d) + min;
        return r;
    }

    public static <T> T[] newArray(final int n) {
        return (T[]) new Object[n];
    }

}
