package utilities;

public class RobustCommissionTestCaseGenerator extends CommissionTestCaseGenerator {

    public RobustCommissionTestCaseGenerator(int lockMin, int lockMax, int stockMin, int stockMax, int barrelMin, int barrelMax) {
        super(lockMin, lockMax, stockMin, stockMax, barrelMin, barrelMax);
    }

    @Override
    public int[] getExtremeValues(int min, int max) {
        return new int[]{min - 1, min, min + 1, max - 1, max, max + 1};
    }
}
