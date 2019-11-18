package task2;

import task2.impl.*;
import util.Performance;
import util.U;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Program {

    public static void main(String[] args) {
        IArray<String> singleArray = new SingleArray<>();
        IArray<String> vectorArray = new FactorArray<>();
        IArray<String> factorArray = new FactorArray<>();
        IArray<String> matrixArray = new MatrixArray<>();
        IArray<String> arrLstArray = new ArrLstArray<>();
        IArray<String> sortedArray = new SortedArray<>();
        IArray<String> linkedList = new LinkedList<>();
        IArray<String> doubleLinkedList = new DoubleLinkedList<>();

        StringBuilder b = new StringBuilder();
        b.append("<table border=\"1\">");
        b.append("<tr>");
        b.append("<th>Name</th>");
        b.append("<th>Add</th>");
        b.append("<th>Add(i)</th>");
        b.append("<th>Add(0)</th>");
        b.append("<th>Get(i)</th>");
        b.append("<th>Remove(i)</th>");
        b.append("<th>Remove(0)</th>");
        b.append("<th>Remove(n)</th>");
        b.append("</tr>");
        for (int i = 0; i < counts.length; i += 1) {
            b.append("<tr><td>" + "Testing with " + counts[i] + " iterations" + "</td></tr>");

            b.append(testArray(singleArray, counts[i]));
            b.append(testArray(vectorArray, counts[i]));
            b.append(testArray(factorArray, counts[i]));
            b.append(testArray(arrLstArray, counts[i]));
            b.append(testArray(sortedArray, counts[i]));
            b.append(testArray(matrixArray, counts[i]));
            b.append(testArray(linkedList, counts[i]));
            b.append(testArray(doubleLinkedList, counts[i]));

            b.append("\r\n");
        }
        b.append("</table>\r\n");

        b.append("<table border=\"1\">");
        PriorityQueue<String> queue = new PriorityQueue<>();
        b.append("<tr><td>Enqueue</td><td>Dequeue</td></tr>");
        for (int i = 0; i < counts.length; i += 1) {
            b.append("<tr><td>" + "Testing with " + counts[i] + " iterations</td></tr>");
            b.append("<tr><td>");
            b.append(testEnqueue(queue, counts[i]));
            b.append("</td><td>");
            b.append(testDequeue(queue, counts[i]));
            b.append("</td></tr>");
        }
        b.append("</table>\r\n");

        final String output = b.toString();
        System.out.println(output);
        try {
            Files.write(Paths.get("report.html"), output.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static final int[] counts = new int[]{ 100, 1_000, 10_000, 100_000 };

    private static String testArray(IArray<String> array, int count) {
        String row = "<tr>" +
                "<td>" + array + "</td>" +
                "<td>" + testArrayAdd(array, count) + "</td>" +
                "<td>" + testArrayAddWithIndex(array, count) + "</td>" +
                "<td>" + testArrayAddFirst(array, count) + "</td>" +
                "<td>" + testArrayGet(array, count) + "</td>" +
                "<td>" + testArrayRemove(array, count) + "</td>" +
                "<td>" + testArrayRemoveLast(array, count) + "</td>" +
                "<td>" + testArrayRemoveFirst(array, count) + "</td></tr>";

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
                int idx = (int)U.randomNumber(0, array.size() - 1);
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
        int idx = (int)U.randomNumber(0, array.size());
        if (idx > array.size()) {
            idx = array.size();
        }
        return idx;
    }

    private static String testEnqueue(final PriorityQueue<String> queue, final int count) {
        return Performance.measure("", () -> {
            for (int i = 0; i < count; i += 1) {
                int p = (int)U.randomNumber(1, count);
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
