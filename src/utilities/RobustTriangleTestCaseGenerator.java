package utilities;

public class RobustTriangleTestCaseGenerator extends TriangleTestCaseGenerator {

    public RobustTriangleTestCaseGenerator(int minSide, int maxSide) {
        super(minSide, maxSide);
    }

    @Override
    public int[] getExtremeValues() {
        int[] extremeValues = {minSide - 1, minSide, minSide + 1, maxSide - 1, maxSide, maxSide + 1};
        return extremeValues;
    }
}
