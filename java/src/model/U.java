package model;

public final class U {
    public static final <T> T[] increaseArray(final T[] array, final int increaseCapacityBy) {
        return increaseArray(array, increaseCapacityBy, -1);
    }

    public static final <T> T[] increaseArray(final T[] array, final int increaseCapacityBy, final int emptyIdx) {

        if (emptyIdx > array.length) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }

        if (array.length <= 0) {
            return (T[])new Object[1];
        }

        T[] newArray = increaseCapacityBy == 0 ? array
                : (T[]) new Object[array.length + increaseCapacityBy];

        if (emptyIdx < 0) {
            System.arraycopy(array, 0, newArray, 0, array.length);
        } else if (emptyIdx == 0) {
            System.arraycopy(array, 0, newArray, 1, array.length);
        } else if (emptyIdx == array.length) {
            System.arraycopy(array, 0, newArray, 0, array.length);
        } else {
            System.arraycopy(array, 0, newArray, 0, emptyIdx);
            System.arraycopy(array, emptyIdx, newArray, emptyIdx + 1, newArray.length - emptyIdx - increaseCapacityBy);
        }

        return newArray;
    }

    public static <T> T[] reduceArray(final T[] array, final int reduceBy, final int idx) {
        if (array.length <= 0 || idx >= array.length || idx < 0) {
            throw new IllegalStateException("Invalid params");
        }

        final int newLength = array.length >= reduceBy ? array.length - reduceBy : 0;

        final T[] newArray = (T[]) new Object[newLength];
        if (newLength == 0) {
            return newArray;
        }

        if (idx == 0) {
            System.arraycopy(array, 1, newArray, 0, newArray.length - 1);
        } else if (idx == array.length - 1) {
            System.arraycopy(array, 0, newArray, 0, newArray.length - 1);
        } else {
            System.arraycopy(array, 0, newArray, 0, idx);
            System.arraycopy(array, idx + 1, newArray, idx, newArray.length - idx - 1);
        }

        return newArray;
    }

    public static <T> void shiftItemsFromIndex(final T[] array, final int idx, final int size) {

    }
}
