package task7_timsort.impl;

import util.IntU;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSort {

    private static final String DATA_DIR = "java/src/task7_timsort/data/";
    private static final String ORIGINAL_FILE = DATA_DIR + "numbers.bin";
    private static final String DATA_DIR_TMP = DATA_DIR + "tmp/";
    private static final String DATA_DIR_SORTED = DATA_DIR + "sorted/";

    public static final long NUMBERS_IN_FILE = Integer.MAX_VALUE >> 4 - 1;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = Short.MAX_VALUE << 1 - 1;

    private static final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(2);
    private static final int[] MERGE_BUFFER = new int[1024];
    private static final int[] INT_BUFFER = new int[1];

    private static final byte[] FILE_BYTE_BUFFER = new byte[MERGE_BUFFER.length << 1];


    public static void sort() {
        try {
            final Path p = Paths.get(ORIGINAL_FILE);
            final FileChannel ch = FileChannel.open(p, StandardOpenOption.READ);
            final long numberInFile = (ch.size() + 1) >> 1;
            final String mergedFile = mergeSort(ch, 0, numberInFile - 1);
            System.out.println(mergedFile);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    private static String mergeSort(final SeekableByteChannel ch, final long l, final long h) throws IOException {
        if (l >= h) {
            return null;
        }

        final long m = l + (h - l) / 2;
        mergeSort(ch, l, m);
        mergeSort(ch, m + 1, h);
        return merge(ch, l, m, h);
    }

    private static String merge(final SeekableByteChannel ch, final long l, final long m, final long h) throws IOException {
        if (l >= h) {
            return null;
        }

        final String mergeFilename = DATA_DIR_TMP + "merged_" + l + "_" + m + "_" + h + ".bin";
        Path p = Paths.get(mergeFilename);
        Files.createFile(p);
        try(final OutputStream out = Files.newOutputStream(p)) {
            long li = l;
            long ri = m + 1;

            int mergeIdx = 0;
            while(li <= m || ri <= h) {
                if (li <= m && ri <= h) {
                    readInt(ch, li, INT_BUFFER);
                    final int n1 = INT_BUFFER[0];
                    readInt(ch, ri, INT_BUFFER);
                    final int n2 = INT_BUFFER[0];

                    if (n1 <= n2) {
                        MERGE_BUFFER[mergeIdx++] = n1;
                        li += 1;
                    } else {
                        MERGE_BUFFER[mergeIdx++] = n2;
                        ri += 1;
                    }
                } else if (li <= m) {
                    readInt(ch, li++, INT_BUFFER);
                    MERGE_BUFFER[mergeIdx++] = INT_BUFFER[0];
                } else if (ri <= h) {
                    readInt(ch, ri++, INT_BUFFER);
                    MERGE_BUFFER[mergeIdx++] = INT_BUFFER[0];
                }

                if (mergeIdx >= MERGE_BUFFER.length) {
                    bufferToFile(MERGE_BUFFER, mergeIdx, out);
                    mergeIdx = 0;
                }
            }

            if (mergeIdx > 0) {
                bufferToFile(MERGE_BUFFER, mergeIdx, out);
            }
            out.flush();
        }

        return mergeFilename;
    }

    private static void bufferToFile(final int[] buffer, final int size, final OutputStream out) throws IOException {
        int length = Math.min(buffer.length, size);
        int idx = 0;
        int byteIdx = 0;
        while(idx < length) {
            final byte high = (byte)(0xff & (buffer[idx] >> 8));
            final byte low = (byte)(0xff & buffer[idx] >> 8);
            FILE_BYTE_BUFFER[byteIdx++] = high;
            FILE_BYTE_BUFFER[byteIdx++] = low;
            idx += 1;
        }

        if (byteIdx > 0) {
            out.write(FILE_BYTE_BUFFER, 0, byteIdx);
        }
    }

    private static int readInt(final SeekableByteChannel ch, final long i, final int[] res) throws IOException {
        ch.position(i << 1);

        int bytes = ch.read(BYTE_BUFFER);
        if (bytes <= 0) {
            throw new RuntimeException("Invalid read position: " + i + "; size: " + ch.size());
        }

        byte high = BYTE_BUFFER.get(0);
        res[0] = high;
        if (bytes > 1) {
            res[0] = 0xffff & ((high << 8) | BYTE_BUFFER.get(1));
        }
        BYTE_BUFFER.clear();
        return bytes;
    }

    public static final void generateFileWithRandomNumbers(final String filename, final long numbersInFile, final int min, final int max) {
        final Path p = Paths.get(DATA_DIR + filename);
        if (Files.exists(p)) {
            return;
        }

        try {
            Files.createFile(p);
        } catch(Throwable t) {
            t.printStackTrace();
        }


        try(final OutputStream out = Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND);) {
            final byte[] buffer = FILE_BYTE_BUFFER;
            int idx = 0;
            long numbersLeft = numbersInFile;
            while(numbersLeft-- > 0) {
                final int number = IntU.randomInt(min, max);
                final byte high = (byte)(0xff & (number >> 8));
                final byte low = (byte)(0xff * number);
                buffer[idx++] = high;
                buffer[idx++] = low;
                if (idx >= buffer.length) {
                    out.write(buffer);
                    idx = 0;
                }
            }
            if (idx > 0) {
                out.write(buffer, 0, idx);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
