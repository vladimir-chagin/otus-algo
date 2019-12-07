package task7_timsort.impl;

import task2.impl.FactorArray;
import task2.impl.IArray;
import task2.impl.VectorArray;
import util.U;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

public class BufferedFileSort {

    private static final String TASK_FILENAME_PREFIX = "java/src/task7_timsort/data/";
    private static final String ORIGINAL_FILENAME = TASK_FILENAME_PREFIX + "numbers.bin";
    private static final String TMP_FILENAME_PREFIX = TASK_FILENAME_PREFIX + "tmp/";
    private static final String SORTED_FILENAME_PREFIX = TASK_FILENAME_PREFIX + "sorted/";

    private static final int BUFFER_SIZE = 1024 * 1024;
    private static final byte[] BYTE_BUFFER = new byte[BUFFER_SIZE];
    private static final int[] INT_BUFFER = new int[BUFFER_SIZE >> 1];
    private static final int MAX_NUMBERS = Integer.MAX_VALUE >> 2;

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 65535;

    public static void generateFileWithRandomNumbers() {
        final Path filePath = Paths.get(ORIGINAL_FILENAME);
        try {
            if (Files.exists(filePath)) {
                return;
            }

            Files.createFile(filePath);
            OutputStream out = Files.newOutputStream(filePath, StandardOpenOption.APPEND);
            int numbersCount = 0;
            while(numbersCount < MAX_NUMBERS) {
                final int intCount = Math.min(MAX_NUMBERS - numbersCount, INT_BUFFER.length);
                U.fillArrayWithRandomInts(INT_BUFFER, 0, intCount, MIN_VALUE, MAX_VALUE);
                final int byteLength = intToByte(INT_BUFFER, 0, intCount, BYTE_BUFFER);
                out.write(BYTE_BUFFER, 0, byteLength);
                numbersCount += intCount;
            }
            out.flush();
            out.close();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }


    public static void sortFileWithMerge() {
        doSort(MergeSort::sort, "simple_merge.bin");
    }

    public static void sortFileWithMixedMerge(final int minPart) {
        doSort((final int[] array, final int l, final int h) -> {
            MergeSort.sortMixed(array, l, h, minPart);
        }, "mixed_merge_" + minPart + ".bin");
    }

    private static void doSort(Sorter fn, String sortedFilename) {
        final IArray<String> sortedParts = new VectorArray<>(1024);

        try {
            InputStream in = new FileInputStream(ORIGINAL_FILENAME);
            int readCount;
            int iterCount = 0;
            while((readCount = in.read(BYTE_BUFFER)) > 0) {
                int intCount = byteToInt(BYTE_BUFFER, readCount, INT_BUFFER);

                fn.sort(INT_BUFFER, 0, intCount - 1);

                int byteCount = intToByte(INT_BUFFER, 0, intCount, BYTE_BUFFER);

                final String fileName = TMP_FILENAME_PREFIX + iterCount + ".bin";
                saveArrayToFile(BYTE_BUFFER, byteCount, fileName);
                sortedParts.add(fileName);
                iterCount += 1;
            }

            final IArray<String> filesToRemove = new FactorArray<>(50, 2048);

            final String sortedFile = mergeFiles(sortedParts, filesToRemove, 0);

            System.out.println("Copying result to '" + sortedFilename + "'");

            Files.copy(Paths.get(sortedFile), Paths.get(SORTED_FILENAME_PREFIX + sortedFilename), StandardCopyOption.REPLACE_EXISTING);

            filesToRemove.add(sortedFile);

            removeFiles(filesToRemove);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    private static String mergeFiles(IArray<String> fileNames, IArray<String> filesToRemove, int mergeRound) {
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

                final String mergedFileName = TMP_FILENAME_PREFIX + mergeRound + "_" + cnt + ".bin";
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
                return mergeFiles(mergedFiles, filesToRemove,mergeRound + 1);
            } else {
                return mergedFiles.get(0);
            }
        } catch(Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    private static void merge(final InputStream in1, final InputStream in2, final OutputStream out) {
        try {
            final byte[] bytes1 = in1.readAllBytes();
            final byte[] bytes2 = in2.readAllBytes();

            final int[] nums1 = byteToInt(bytes1, bytes1.length);
            final int[] nums2 = byteToInt(bytes2, bytes2.length);

            int i1 = 0;
            int i2 = 0;
            int mergeCnt = 0;
            final int[] mergeBuffer = INT_BUFFER;

            while(i1 < nums1.length || i2 < nums2.length) {
                if (i1 < nums1.length && i2 < nums2.length) {
                    if (nums1[i1] <= nums2[i2]) {
                        mergeBuffer[mergeCnt++] = nums1[i1++];
                    } else {
                        mergeBuffer[mergeCnt++] = nums2[i2++];
                    }
                } else if (i1 < nums1.length) {
                    mergeBuffer[mergeCnt++] = nums1[i1++];
                } else if (i2 < nums2.length) {
                    mergeBuffer[mergeCnt++] = nums2[i2++];
                }

                if (mergeCnt >= mergeBuffer.length) {
                    writeInts(mergeBuffer, mergeCnt, out);
                    mergeCnt = 0;
                }
            }

            if (mergeCnt > 0) {
                writeInts(mergeBuffer, mergeCnt, out);
            }

            out.flush();
            out.close();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    private static void writeInts(final int[] intBuffer, final int size, final OutputStream out) throws Throwable {
        int numsLeft = size;
        int offset = 0;
        while(numsLeft > 0) {
            int bytesLength = Math.min(numsLeft << 1, BYTE_BUFFER.length);

            intToByte(intBuffer, offset, size, BYTE_BUFFER);

            out.write(BYTE_BUFFER, 0, bytesLength);

            offset += bytesLength << 1;
            numsLeft -= bytesLength >> 1;
        }
    }

    private static int readInts(final int[] buffer, final InputStream in) throws Throwable {
        int readBytesCnt = in.read(BYTE_BUFFER);
        if (readBytesCnt > 0) {
            return byteToInt(BYTE_BUFFER, buffer);
        }

        return -1;
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

    private static void removeFiles(IArray<String> files) {
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



    ///buffer utility methods
    private static int byteToInt(byte [] src, int[] dst) {
        return byteToInt(src, src.length, dst);
    }

    private static int byteToInt(byte [] src, int byteSize, int[] dst) {
        if (byteSize / 2 > dst.length) {
            throw new RuntimeException("Not enough space in dst");
        }

        int intCount = 0;
        int byteCount = 0;

        while(byteCount < byteSize) {
            final int low = src[byteCount++];
            final int high = src[byteCount++] << 8;
            dst[intCount++] = 0xffff & (high | low);
        }

        return intCount;
    }

    private static int intToByte(int[] src, byte[] dst) {
        return intToByte(src, 0, src.length, dst);
    }

    private static int intToByte(int[] src, int offset, int length, byte[] dst) {
        if (src.length <= offset) {
            throw new RuntimeException("invalid offset: " + offset + "; length: " + src.length);
        }

        if (length > dst.length / 2) {
            throw new RuntimeException("Not enough space in destination");
        }

        int intsToCopy = Math.min(src.length - offset, length);
        int byteCount = 0;
        int intCount = 0;

        while(intCount < intsToCopy) {
            byte high = (byte)(0xff & (src[offset + intCount] >> 8));
            byte low = (byte)(0xff & src[offset + intCount]);
            intCount += 1;

            dst[byteCount++] = high;
            dst[byteCount++] = low;
        }

        return byteCount;
    }

    private static int[] byteToInt(final byte[] bytes, final int size) {
        if (bytes.length > size) {
            throw new RuntimeException("Invalid size");
        }

        int[] nums = new int[size >> 1];

        byteToInt(bytes, size, nums);

        return nums;
    }
}
