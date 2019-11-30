package task4_chess.impl.tests;

import java.lang.reflect.Constructor;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskRunner {

    public static ITask createTask(Class cls, String taskName, String inputFile, String outputFile) {
        try {
            Class[] argTypes = new Class[] { String.class, String.class, String.class };
            Constructor ctr = cls.getConstructor(argTypes);
            return (ITask)ctr.newInstance(new Object[] { taskName, inputFile, outputFile });
        } catch(Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    public static void runtTests(Class cls, String taskName, String dir) {
        String[][] files = getFiles(dir);

        String[] inputFiles = files[0];
        String[] outputFiles = files[1];

        for (int i = 0; i < inputFiles.length; i += 1) {
            ITask task = createTask(cls, taskName, inputFiles[i], outputFiles[i]);
            task.execute();
        }

        System.out.println("All tests passed");
    }

    private static int parseIndex(String fileName) {
        String[] parts = fileName.split("\\.");
        return Integer.parseInt(parts[parts.length-2]);
    }

    private static String[][] getFiles(String dir) {
        final String[][] results = new String[2][];

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))){
            final List<String> inFiles = new ArrayList<>();
            final List<String> outFiles = new ArrayList<>();

            for(Path file : stream) {
                final String path = file.toString();
                if (path.endsWith(".in")) {
                    inFiles.add(path);
                } else if (path.endsWith(".out")) {
                    outFiles.add(path);
                }
            }

            String[] inFilesArray = new String[inFiles.size()];
            String[] outFilesArray = new String[outFiles.size()];

            for (String file: inFiles) {
                int i = parseIndex(file);
                inFilesArray[i] = file;
            }

            for (String file: outFiles) {
                int i = parseIndex(file);
                outFilesArray[i] = file;
            }

            results[0] = inFilesArray;
            results[1] = outFilesArray;

            return results;
        } catch(Throwable t) {
            t.printStackTrace();
        }

        throw new RuntimeException("Check out console");
    }
}
