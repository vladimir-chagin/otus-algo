package task4_chess.impl.tests;

import util.IO;
import util.U;

public abstract class AbstractTask implements ITask {
    private final String name;
    private final String inputFilePath;
    private final String outputFilePath;

    protected final String[] inputData;
    private final String[] outputData;

    public AbstractTask(String name, String inputFilePath, String outputFilePath) {
        this.name = name;
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;

        this.inputData = IO.readAllLines(inputFilePath);
        this.outputData = IO.readAllLines(outputFilePath);
    }

    public final void execute() {
        try {
            final String[] outputData = executeInner();
            U.assertEquals(this.outputData, outputData);
        } catch(Throwable t) {
            System.out.println(name);
            System.out.println("input: " + this.inputFilePath);
            System.out.println("out: " + this.outputFilePath);
            t.printStackTrace();
        }
    }

    protected abstract String[] executeInner();
}
