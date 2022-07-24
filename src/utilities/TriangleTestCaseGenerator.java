package utilities;

import entity.TestCase;

import java.util.*;

public class TriangleTestCaseGenerator {
    private List<TestCase> testCases = new ArrayList<>();

    protected int minSide;
    protected int maxSide;

    public TriangleTestCaseGenerator(int minSide, int maxSide) {
        this.minSide = minSide;
        this.maxSide = maxSide;

        initInputValues();
        initOutputValues();
    }

    public int[] getExtremeValues() {
        int[] extremeValues = {minSide, minSide + 1, maxSide - 1, maxSide};
        return extremeValues;
    }

    private void initInputValues() {
        int[] extremeValues = getExtremeValues();

        int nom = (maxSide - minSide) / 2;

        int id = 1;
        for (int extremeValue : extremeValues) {
            testCases.add(new TestCase(id++, nom, nom, extremeValue));
            testCases.add(new TestCase(id++, nom, extremeValue, nom));
            testCases.add(new TestCase(id++, extremeValue, nom, nom));
        }
        testCases.add(new TestCase(id, nom, nom, nom));
    }

    private void initOutputValues() {
        testCases.forEach(testcase -> {
            String output = inspectTriangle(testcase.getFirstInputVariable(), testcase.getSecondInputVariable(), testcase.getThirdInputVariable());
            testcase.setExpectedOutput(output);
        });
    }

    private String inspectTriangle(int side1, int side2, int side3) {
        if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
            return "Not a triangle";
        }

        if (side1 == side2 && side1 == side3) {
            return "Equilateral";
        } else if (side1 == side2 || side1 == side3 || side2 == side3) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    public List<TestCase> getTestCases() {
        return this.testCases;
    }

    public int getMinSide() {
        return minSide;
    }

    public void setMinSide(int minSide) {
        this.minSide = minSide;
        initInputValues();
        initOutputValues();
    }

    public int getMaxSide() {
        return maxSide;
    }

    public void setMaxSide(int maxSide) {
        this.maxSide = maxSide;
        initInputValues();
        initOutputValues();
    }
}
