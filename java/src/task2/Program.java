package task2;

import task2.impl.*;
import util.Performance;
import util.U;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Program {
    private static final int[] counts = new int[]{ 100, 1_000, 10_000, 20_000, 50_000, 100_000 };

    public static void main(String[] args) {
        IArray<String> singleArray = new SingleArray<>();
        IArray<String> vectorArray = new VectorArray<>();
        IArray<String> factorArray = new FactorArray<>();
        IArray<String> matrixArray = new MatrixArray<>();
        IArray<String> arrLstArray = new ArrLstArray<>();
        IArray<String> sortedArray = new SortedArray<>();
        IArray<String> linkedList = new LinkedList<>();
        IArray<String> doubleLinkedList = new DoubleLinkedList<>();

        StringBuilder b = new StringBuilder();

        b.append("# Arrays");
        b.append("\r\n");

        b.append("Name|Add|Add(i)|Add(0)|Get(i)|Remove(i)|Remove(n)|Remove(0)\r\n");
        b.append("---|---|---|---|---|---|---|---\r\n");
        for (int i = 0; i < counts.length; i += 1) {
            b.append("**" + counts[i] + " iterations** | \r\n");
            System.out.println("Iterations: " + counts[i]);
            b.append(testArray(singleArray, counts[i])).append("\r\n");
            b.append(testArray(vectorArray, counts[i])).append("\r\n");
            b.append(testArray(factorArray, counts[i])).append("\r\n");
            b.append(testArray(arrLstArray, counts[i])).append("\r\n");
            b.append(testArray(sortedArray, counts[i])).append("\r\n");
            b.append(testArray(matrixArray, counts[i])).append("\r\n");
            b.append(testArray(linkedList, counts[i])).append("\r\n");
            b.append(testArray(doubleLinkedList, counts[i])).append("\r\n");
        }

        b.append("\r\n");

        b.append("# PriorityQueue\r\n");
        PriorityQueue<String> queue = new PriorityQueue<>();
        b.append("|---|Enqueue|Dequeue|\r\n");
        b.append("|---|---|---|\r\n");
        for (int i = 0; i < counts.length; i += 1) {
            b.append(counts[i] + " iterations|");
            b.append(testEnqueue(queue, counts[i])).append("|").append(testDequeue(queue, counts[i])).append("|\r\n");
        }
        b.append("\r\n");

        final String output = b.toString();
        System.out.println(output);
        try {
            Files.write(Paths.get("java", "src", "task2", "report_generated.md"), output.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static String testArray(IArray<String> array, int count) {
        String row =
                array + "|" +
                testArrayAdd(array, count) + "|" +
                testArrayAddWithIndex(array, count) + "|" +
                testArrayAddFirst(array, count) + "|" +
                testArrayGet(array, count) + "|" +
                testArrayRemove(array, count) + "|" +
                testArrayRemoveLast(array, count) + "|" +
                testArrayRemoveFirst(array, count);

//        System.out.println(row);

        return row;
    }

    private static String testArrayAdd(final IArray<String> array, final int count) {
        array.clear();
        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                array.add(String.valueOf(i));
            }
        });
    }

    private static String testArrayAddFirst(final IArray<String> array, final int count) {
        array.clear();
        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                array.addFirst(String.valueOf(i));
            }
        });
    }

    private static String testArrayAddWithIndex(final IArray<String> array, final int count) {
        array.clear();
        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                array.add(String.valueOf(i), randomIndex(array));
            }
        });
    }

    private static String testArrayRemove(final IArray<String> array, final int count) {
        array.clear();
        fillArray(array, count);

        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                int idx = (int)U.randomLong(0, array.size() - 1);
                array.remove(idx);
            }
        });
    }

    private static String testArrayRemoveLast(final IArray<String> array, final int count) {
        array.clear();
        fillArray(array, count);

        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                array.removeLast();
            }
        });
    }

    private static String testArrayRemoveFirst(final IArray<String> array, final int count) {
        array.clear();
        fillArray(array, count);

        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                array.removeFirst();
            }
        });
    }

    private static String testArrayGet(final IArray<String> array, final int count) {
        array.clear();
        fillArray(array, count);

        return Performance.measure("", () -> {
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
        int idx = (int)U.randomLong(0, array.size());
        if (idx > array.size()) {
            idx = array.size();
        }
        return idx;
    }

    private static String testEnqueue(final PriorityQueue<String> queue, final int count) {
        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                int p = (int)U.randomLong(1, count);
                queue.enqueue(p, String.valueOf(i));
            }
        });
    }

    private static String testDequeue(final PriorityQueue<String> queue, final int count) {
        return Performance.measure("", () -> {
            int idx = count;
            while (idx-- > 0) {
                queue.dequeue();
            }
        });
    }
}
