package util;

import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IO {

    public static String[] readAllLines(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
            List<String> outLines = new ArrayList<>(lines.size());
            for (String line: lines) {
                if (line != null && line.length() > 0) {
                    outLines.add(line);
                }
            }

            return outLines.toArray(new String[outLines.size()]);
        } catch(Throwable t) {
            t.printStackTrace();
        }
        return new String[0];
    }

    public static void saveStringToFile(String path, String content) {
        if (content == null || content.length() <= 0) {
            return;
        }

        try {
            Files.write(Paths.get(path), content.getBytes());
            System.out.println("File '" + path + "' saved");
        } catch(Throwable t) {
            t.printStackTrace();
        }

    }
}
