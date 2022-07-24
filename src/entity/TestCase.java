package entity;

public class TestCase {
    private int id;
    private int firstInputVariable;
    private int secondInputVariable;
    private int thirdInputVariable;
    private String expectedOutput;

    public TestCase(int id, int firstInputVariable, int secondInputVariable, int thirdInputVariable) {
        this.id = id;
        this.firstInputVariable = firstInputVariable;
        this.secondInputVariable = secondInputVariable;
        this.thirdInputVariable = thirdInputVariable;
    }

    public TestCase(int id, int firstInputVariable, int secondInputVariable, int thirdInputVariable, String expectedOutput) {
        this(id, firstInputVariable, secondInputVariable, thirdInputVariable);
        this.expectedOutput = expectedOutput;
    }

    public int getId() {
        return id;
    }

    public int getFirstInputVariable() {
        return firstInputVariable;
    }

    public int getSecondInputVariable() {
        return secondInputVariable;
    }

    public int getThirdInputVariable() {
        return thirdInputVariable;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    @Override
    public String toString() {
        return "" + this.firstInputVariable + "," + this.secondInputVariable + "," + this.thirdInputVariable + "," + this.expectedOutput;
    }
}
