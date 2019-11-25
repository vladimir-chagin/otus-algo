package task4_chess;

import task4_chess.impl.tests.StringLengthTask;
import task4_chess.impl.tests.TaskRunner;

public class Program {

    public static void main(String[] args) {
        TaskRunner.runtTests(StringLengthTask.class, "TestInput", "java/src/task4_chess/data/!.TESTS");
    }
}
