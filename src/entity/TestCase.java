package entity;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
    private int id;
    private List<Integer> inputValues;
    private String expectedOutput;

    public TestCase(int id, Integer... inputValues) {
        this.id = id;

        this.inputValues = new ArrayList<>();
        this.inputValues.addAll(List.of(inputValues));
    }

    public int getId() {
        return id;
    }

    public List<Integer> getInputValues() {
        return this.inputValues;
    }

    public Integer getInputValue(int n) {
        return isInBound(n) ? this.inputValues.get(n) : null;
    }

    public boolean setInputValue(int n, Integer val) {
        if (this.isInBound(n)) {
            this.inputValues.set(n, val);
            return true;
        }

        return false;
    }

    private boolean isInBound(int n) {
        return n > -1 && n < this.inputValues.size();
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    @Override
    public String toString() {
        final String[] string = {""};

        this.inputValues.forEach(inputValue -> {
            string[0] += inputValue.toString() + ",";
        });

        return string[0] + this.expectedOutput;
    }
}
