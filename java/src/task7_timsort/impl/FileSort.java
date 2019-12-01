package task7_timsort.impl;

import task2.impl.FactorArray;
import task2.impl.IArray;
import task2.impl.VectorArray;
import util.U;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSort {
    private static final String FILENAME = "java/src/task7_timsort/data/numbers.bin";
    private static final String TMP_FILENAME_PREFIX = "java/src/task7_timsort/data/tmp";

    private static final Path FILEPATH = Paths.get(FILENAME);

    private static final int BUFFER_SIZE = 1024 * 1024;
    private static final byte[] BYTE_BUFFER = new byte[BUFFER_SIZE];
    private static final int[] INT_BUFFER = new int[BUFFER_SIZE >> 1];
    private static final int MAX_NUMBERS = Integer.MAX_VALUE >> 2;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 65535;

    public static void generateFileWithRandomNumbers() {
        final Path filePath = Paths.get(FILENAME);
        try {
            if (Files.exists(filePath)) {
                return;
            }

            Files.createFile(filePath);
            OutputStream out = Files.newOutputStream(filePath, StandardOpenOption.APPEND);
            int numbersCount = 0;
            while(numbersCount < MAX_NUMBERS) {
                int intCount = MAX_NUMBERS - numbersCount > INT_BUFFER.length ? INT_BUFFER.length : MAX_NUMBERS - numbersCount;
                fillArrayWithRandomNumbers(INT_BUFFER, intCount);
                final int byteLength = intToByte(INT_BUFFER, BYTE_BUFFER);
                out.write(BYTE_BUFFER, 0, byteLength);
                numbersCount += byteLength / 2;
            }
            out.flush();
            out.close();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public static int fillArrayWithRandomNumbers(final int[] array, int length) {
        length = Math.min(array.length, length);

        for (int i = 0; i < length; i += 1) {
            array[i] = 0xffff & U.randomInt(MIN_VALUE, MAX_VALUE);
        }

        return length;
    }
    //dst.length >= src.length * 2
    //returns bytes count copied to dest
    private static int byteToInt(byte [] src, int[] dst) {
        if (src.length / 2 > dst.length) {
            throw new RuntimeException("Not enough space in dst");
        }

        int intCount = 0;
        int byteCount = 0;

        while(byteCount < src.length) {
            final int low = src[byteCount++];
            final int high = src[byteCount++] << 8;
            dst[intCount++] = 0xffff & (high | low);
        }

        return intCount;
    }

    private static int intToByte(int[] src, byte[] dst) {
        if (src.length > dst.length / 2) {
            throw new RuntimeException("Not enough space in destination");
        }

        int byteCount = 0;
        int intCount = 0;

        while(intCount < src.length) {
            byte high = (byte)(0xff & (src[intCount] >> 8));
            byte low = (byte)(0xff & src[intCount++]);
            dst[byteCount++] = high;
            dst[byteCount++] = low;
        }

        return intCount;
    }

    public static void sortFileWithMerge() {
        doSort(MergeSort::sort);
    }

    public static void sortFileWithMixedMerge(final int minPart) {
        doSort((final int[] array, final int l, final int h) -> {
            MergeSort.sortMixed(array, l, h, minPart);
        });
    }

    private static void doSort(Sorter fn) {
        final IArray<String> sortedParts = new VectorArray<>(1024);

        try {
            InputStream in = Files.newInputStream(FILEPATH);
            int readCount = -1;
            int iterCount = 0;
            while((readCount = in.read(BYTE_BUFFER)) > 0) {
                byteToInt(BYTE_BUFFER, INT_BUFFER);

                fn.sort(INT_BUFFER, 0, (readCount + 1) / 2 - 1);

                int byteCount = intToByte(INT_BUFFER, BYTE_BUFFER);
                final String fileName = TMP_FILENAME_PREFIX + "/" + iterCount + ".bin";
                saveArrayToFile(BYTE_BUFFER, byteCount, fileName);
                sortedParts.add(fileName);
                iterCount += 1;
            }

            final IArray<String> filesToRemove = new FactorArray<>(50, 2048);
            mergeFiles(sortedParts, filesToRemove, 0);
            removeFiles(filesToRemove);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    private static IArray<String> mergeFiles(IArray<String> fileNames, IArray<String> filesToRemove, int mergeRound) {
        IArray<String> mergedFiles = new FactorArray<>();
        try {
            int cnt = 0;
            while(fileNames.size() > 1) {
                String fn1 = fileNames.removeLast();
                String fn2 = fileNames.removeLast();

                Path p1 = Paths.get(fn1);
                Path p2 = Paths.get(fn2);

                final InputStream in1 = Files.newInputStream(p1);
                final InputStream in2 = Files.newInputStream(p2);
                final String mergedFileName = TMP_FILENAME_PREFIX + "/" + mergeRound + "_" + cnt + ".bin";
                final Path mergedFilePath = Paths.get(mergedFileName);
                final OutputStream out = Files.newOutputStream(mergedFilePath);
                try {
                    merge(in1, in2, out);
                } finally {
                    out.close();
                    in1.close();
                    in2.close();
                }

                mergedFiles.add(mergedFileName);
                cnt += 1;

                filesToRemove.add(fn1);
                filesToRemove.add(fn2);
            }

            if (fileNames.size() > 0) {
                mergedFiles.add(fileNames.removeLast());
            }

            if (mergedFiles.size() > 1) {
                mergeFiles(mergedFiles, filesToRemove,mergeRound + 1);
            } else {
                System.out.println("Sorted file: " + mergedFiles.get(0));
            }
        } catch(Throwable t) {
            t.printStackTrace();
        }
        return mergedFiles;
    }

    private static void merge(final InputStream in1, final InputStream in2, final OutputStream out) {
        try {
            final byte[] b1 = new byte[2];
            final byte[] b2 = new byte[2];
            final byte[] nBuff = new byte[2];

            int c1 = -2;
            int c2 = -2;

            while(true) {
                if (c1 < -1) {
                    c1 = in1.read(b1);
                }

                if (c2 < -1) {
                    c2 = in2.read(b2);
                }

                int n;
                int nl;

                if (c1 > 0 && c2 > 0) {
                    int n1 = b1[0];
                    int nl1 = 1;
                    if (c1 > 1) {
                        n1 = 0xffff & ((n1 << 8) | b1[1]);
                        nl1 = 2;
                    }

                    int n2 = b2[0];
                    int nl2 = 1;
                    if (c2 > 1) {
                        n2 = 0xffff & ((n2 << 8) | b2[1]);
                        nl2 = 2;
                    }

                    if (n1 <= n2) {
                        n = n1;
                        nl = nl1;
                        c1 = -2;
                    } else {
                        n = n2;
                        nl = nl2;
                        c2 = -2;
                    }
                } else if (c1 > 0) {
                    n = b1[0];
                    nl = 1;
                    if (c1 > 1) {
                        n = 0xffff & ((n << 8) | b1[1]);
                        nl = 2;
                    }
                    c1 = -2;
                } else if (c2 > 0) {
                    n = b2[0];
                    nl = 1;
                    if (c2 > 1) {
                        n = 0xffff & ((n << 8) | b2[1]);
                        nl = 2;
                    }
                    c2 = -2;
                } else {
                    break;
                }

                if (nl > 1) {
                    nBuff[1] = (byte)(n & 0xff);
                    nBuff[0] = (byte)((n >> 8) & 0xff);
                    out.write(nBuff);
                } else {
                    nBuff[0] = (byte)(n & 0xff);
                    out.write(nBuff, 0, 1);
                }
            }

            out.flush();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    private static void saveArrayToFile(byte[] buffer, int length, String fileName) {
        try {
            final Path path = Paths.get(fileName);
            final OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE);
            out.write(buffer, 0, length);
            out.flush();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public static void removeFiles(IArray<String> files) {
        for (int i = 0; i < files.size(); i += 1) {
            removeFile(files.get(i));
        }
    }

    private static void removeFile(final String filename) {
        try {
            final Path p = Paths.get(filename);
            Files.deleteIfExists(p);
        } catch(Throwable t) {
            t.printStackTrace();
        }

    }

}
