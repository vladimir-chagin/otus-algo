package task2;

import task2.model.*;


public class Program {

    public static void main(String[] args) {

        IArray<String> singleArray = new SingleArray<>();
        IArray<String> vectorArray = new FactorArray<>();
        IArray<String> factorArray = new FactorArray<>();
        IArray<String> matrixArray = new MatrixArray<>();
        IArray<String> arrLstArray = new ArrLstArray<>();
        IArray<String> sortedArray = new SortedArray();
//        IArray<String> spacedArray = new SpaceArray<>();

        for (int i = 0; i < counts.length; i += 1) {
            testArray(singleArray, counts[i]);
            testArray(vectorArray, counts[i]);
            testArray(factorArray, counts[i]);
            testArray(matrixArray, counts[i]);
            testArray(arrLstArray, counts[i]);
            testArray(sortedArray, counts[i]);
        }

    }

    private static final int[] counts = new int[]{ 100, 1_000, /*1_0_000, 100_000, 1_000_000 */};

    private static void testArray(IArray<String> array, int count) {
        String row = array.toString() + " | " +
                testArrayAdd(array, count) + " | " +
                testArrayAddWithIndex(array, count) + " | " +
                testArrayAddFirst(array, count) + " | " +
                testArrayRemove(array, count) + " | " +
                testArrayRemoveFirst(array, count) + "\r\n" +
                "-------------------------------------------------------------------------------------------------------";

        System.out.println(row);
    }

    private static String testArrayAdd(IArray<String> array, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i += 1) {
            array.add(String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        final long duration = end - start;

        return duration + "ms";
    }

    private static String testArrayAddFirst(IArray<String> array, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i += 1) {
            array.addFirst(String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        final long duration = end - start;

        return duration + "ms";
    }

    private static String testArrayAddWithIndex(IArray<String> array, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i += 1) {
            array.add(String.valueOf(i), randomIndex(array));
        }
        long end = System.currentTimeMillis();
        final long duration = end - start;

        return duration + "ms";
    }

    private static String testArrayRemove(IArray<String> array, int count) {
        fillArray(array, count);

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i += 1) {
            array.remove(randomIndex(array));
        }
        long end = System.currentTimeMillis();
        final long duration = end - start;

        return duration + "ms";
    }

    private static String testArrayRemoveFirst(IArray<String> array, int count) {
        fillArray(array, count);

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i += 1) {
            array.removeFirst();
        }
        long end = System.currentTimeMillis();
        final long duration = end - start;

        return duration + "ms";
    }

    private static final void fillArray(IArray<String> array, int count) {
        for (int i = 0; i < count; i += 1) {
            array.add(String.valueOf(i));
        }
    }

    private static final int randomIndex(IArray<String> array) {
        return (int)Math.floor(Math.random() * array.size());
    }
}
