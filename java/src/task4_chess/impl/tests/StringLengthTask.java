package task4_chess.impl.tests;

public class StringLengthTask extends AbstractTask {

    public StringLengthTask(String name, String inputFilePath, String outputFilePath) {
        super(name, inputFilePath, outputFilePath);
    }

    @Override
    protected String[] executeInner() {
        int strLength = this.inputData != null && this.inputData.length > 0 ? this.inputData[0].length() : 0;
        return new String[] { String.valueOf(strLength) };
    }
}
