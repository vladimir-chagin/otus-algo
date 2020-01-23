package task11_seg_tree;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SegSum {
    private static final String IN_FILE = "sum.in";
    private static final String OUT_FILE = "sum.out";

    public static void process() {
        try (FileWriter out = new FileWriter(OUT_FILE, false)) {
            final List<String> lines = Files.readAllLines(Paths.get(IN_FILE));
            String firstLine = lines.remove(0);
            String[] firstParts = firstLine.split("\\s");
            int N = Integer.parseInt(firstParts[0]);
            int K = Integer.parseInt(firstParts[1]);
            int n = (1 << ((int)(Math.log(N - 1)/Math.log(2)) + 1));

            long[] arr = new long[n << 1];
            long[] res = new long[K];

            int count = 0;
            for (String line : lines) {
                String[] parts = line.split("\\s");
                if (count == K) {
                    break;
                }
                final String c = parts[0];
                if ("A".equals(c)) {
                    final int i = Integer.parseInt(parts[1]);
                    final long x = Long.parseLong(parts[2]);
                    update(arr, n+i, x);
                    continue;
                }
                if ("Q".equals(c)) {
//                    if (!built) {
//                        buildHeap(arr, n);
//                        built = true;
//                    }
                    final int l = Integer.parseInt(parts[1]);
                    final int r = Integer.parseInt(parts[2]);
                    final long sum = sum(arr, l+n, r+n);
                    res[count++] = sum;
                }
            }

            for (int i = 0; i < count; i += 1) {
                out.write(res[i]+"\r\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void buildHeap(final long[] arr, final int startIdx) {
        int l = startIdx;
        int r = arr.length - 1;

        while(l < r) {
            for (int i = l; i < r; i += 2) {
                int p = i >> 1;
                long s = arr[i] + arr[i+1];
                arr[p] = s;
            }
            l = l / 2;
            r = r / 2;
        }
    }

    private static long sum(final long[] arr, int l, int r) {
        long s = 0;
        while(l <= r) {
            if ((l & 1) != 0) {
                s = s + arr[l];
            }
            if ((r & 1) == 0) {
                s = s + arr[r];
            }
            l = (l+1) / 2;
            r = (r-1) / 2;
        }
        return s;
    }

    private static void update(final long[] arr, int i, final long x) {
        arr[i] = x;
        for (; i > 0; i /= 2) {
            int j = (i & 1) == 0 ? i+1 : i-1;
            arr[i/2] = arr[i] + arr[j];
        }
    }

    public static void main(String[] args) {
        SegSum.process();
    }
}
