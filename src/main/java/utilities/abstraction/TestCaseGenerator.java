package utilities.abstraction;

import entity.TestCase;
import entity.Variable;

import java.util.*;

public abstract class TestCaseGenerator {
    private ExtremeBoundaryValues extremeBoundaryValues;
    private List<Variable> variables;
    private List<TestCase> testCases;

    public TestCaseGenerator(ExtremeBoundaryValues extremeBoundaryValues, Variable... variables) {
        this.extremeBoundaryValues = extremeBoundaryValues;

        this.variables = new ArrayList<>();
        this.variables.addAll(List.of(variables));

        this.testCases = new ArrayList<>();

        this.initInputValues(extremeBoundaryValues);
        this.initOutputValues();
    }

    public void initInputValues(ExtremeBoundaryValues extremeBoundaryValues) {
        Integer[] inputValues = new Integer[this.variables.size()];
        for (int i = 0; i < inputValues.length; i++) {
            inputValues[i] = this.variables.get(i).getNom();
        }

        int id = 1;
        this.testCases.add(new TestCase(id++, inputValues));

        for (int i = inputValues.length - 1; i > -1; i--) {
            for (Integer val : extremeBoundaryValues.getExtremeValues(this.variables.get(i))) {
                inputValues[i] = val;
                this.testCases.add(new TestCase(id++, inputValues));
            }
            inputValues[i] = this.variables.get(i).getNom();
        }
    }

    public void initOutputValues() {
        this.testCases.forEach(testCase -> {
            if (!isInRange(testCase.getInputValues())) {
                testCase.setExpectedOutput("Out of range");
            } else {
                String output = this.calc(testCase.getInputValues());
                testCase.setExpectedOutput(output);
            }
        });
    };

    public abstract String calc(List<Integer> values);

    public boolean isInRange(List<Integer> values) {
        for (int i = 0; i < variables.size(); i++) {
            if (values.get(i) < variables.get(i).getMin() || values.get(i) > variables.get(i).getMax()) {
                return false;
            }
        }
        return true;
    }

    public List<TestCase> getTestCases() { return this.testCases; }

    public List<Variable> getVariables() { return this.variables; }

    public boolean setVariable(int n, Variable variable) {
        if (n > -1 && n < this.variables.size()) {
            this.variables.set(n, variable);

            this.testCases = new ArrayList<>();
            this.initInputValues(this.extremeBoundaryValues);
            this.initOutputValues();

            return true;
        }

        return false;
    }
}
