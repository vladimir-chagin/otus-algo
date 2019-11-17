package task2;

import task2.model.*;
import util.Performance;
import util.U;


public class Program {

    public static void main(String[] args) {

//        IArray<String> singleArray = new SingleArray<>();
//        IArray<String> vectorArray = new FactorArray<>();
//        IArray<String> factorArray = new FactorArray<>();
//        IArray<String> matrixArray = new MatrixArray<>();
//        IArray<String> arrLstArray = new ArrLstArray<>();
//
//        AbstractArray<Integer> sortedArray = new SortedArray();
//        for (int i = 0; i < 10; i += 1) {
//            int idx = (int)U.randomNumber(0, 10);
//            sortedArray.add(Integer.valueOf(idx));
//            sortedArray.print();
//        }
//        for (int i = 0; i < 10; i += 1) {
//            int idx = (int)U.randomNumber(0, sortedArray.size()-1);
//            sortedArray.remove(idx);
//            sortedArray.print();
//        }

        PriorityQueue<String> queue = new PriorityQueue<>();
        for (int i = 0; i < counts.length; i += 1) {
            testEnqueue(queue, counts[i]);
            testDequeue(queue, counts[i]);
        }

//        for (int i = 0; i < counts.length; i += 1) {
//            System.out.println("Testing with " + counts[i] + " iterations");
//            testArray(singleArray, counts[i]);
//            testArray(vectorArray, counts[i]);
//            testArray(factorArray, counts[i]);
//            testArray(arrLstArray, counts[i]);
//            testArray(sortedArray, counts[i]);
//            testArray(matrixArray, counts[i]);
//        }
    }

    private static final int[] counts = new int[]{ 100, 1_000/*, 10_000, 100_000 */};

    private static void testArray(IArray<String> array, int count) {
        String row = array + " | " +
                testArrayAdd(array, count) + " | " +
                testArrayAddWithIndex(array, count) + " | " +
                testArrayAddFirst(array, count) + " | " +
                testArrayRemove(array, count) + " | " +
                testArrayGet(array, count) + " | " +
                testArrayRemoveFirst(array, count) + " |\r\n" +
                "-------------------------------------------------------------------------------------------------------";

        System.out.println(row);
    }

    private static String testArrayAdd(final IArray<String> array, final int count) {
        return Performance.measure("Add ", () -> {
            for (int i = 0; i < count; i += 1) {
                array.add(String.valueOf(i));
            }
        });
    }

    private static String testArrayAddFirst(final IArray<String> array, final int count) {
        return Performance.measure("Add(0)", () -> {
            for (int i = 0; i < count; i += 1) {
                array.addFirst(String.valueOf(i));
            }
        });
    }

    private static String testArrayAddWithIndex(final IArray<String> array, final int count) {
        return Performance.measure("Add(i) ", () -> {
            for (int i = 0; i < count; i += 1) {
                array.add(String.valueOf(i), randomIndex(array));
            }
        });
    }

    private static String testArrayRemove(final IArray<String> array, final int count) {
        fillArray(array, count);

        return Performance.measure("Remove(i) ", () -> {
            for (int i = 0; i < count; i += 1) {
                int idx = (int)U.randomNumber(0, array.size() - 1);
                array.remove(idx);
            }
        });
    }

    private static String testArrayRemoveFirst(final IArray<String> array, final int count) {
        fillArray(array, count);

        return Performance.measure("Remove(0) ", () -> {
            for (int i = 0; i < count; i += 1) {
                array.removeFirst();
            }
        });
    }

    private static String testArrayGet(final IArray<String> array, final int count) {
        fillArray(array, count);

        return Performance.measure("Get(i) ", () -> {
            for (int i = 0; i < count; i += 1) {
                array.get(i);
            }
        });
    }

    private static void fillArray(final IArray<String> array, int count) {
        for (int i = 0; i < count; i += 1) {
            array.add(String.valueOf(i));
        }
    }

    private static int randomIndex(final IArray<String> array) {
        int idx = (int)U.randomNumber(0, array.size());
        if (idx > array.size()) {
            idx = array.size();
        }
        return idx;
    }

    private static void testEnqueue(final PriorityQueue<String> queue, final int count) {
        for (int i = 0; i < count; i += 1) {
            int p = (int)U.randomNumber(1, count);
            queue.enqueue(p, String.valueOf(i));
        }
    }

    private static void testDequeue(final PriorityQueue<String> queue, final int count) {
        while (!queue.isEmpty()) {
            queue.dequeue();
        }
    }
}
