package utilities;

import entity.TestCase;

import java.util.*;

public class CommissionTestCaseGenerator {
    private static final int LOCK_COST = 45;
    private static final int STOCK_COST = 30;
    private static final int BARREL_COST = 25;

    private List<TestCase> testCases = new ArrayList<>();

    protected int lockMin;
    protected int lockMax;
    protected int stockMin;
    protected int stockMax;
    protected int barrelMin;
    protected int barrelMax;

    public CommissionTestCaseGenerator(int lockMin, int lockMax, int stockMin, int stockMax, int barrelMin, int barrelMax) {
        this.lockMin = lockMin;
        this.lockMax = lockMax;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.barrelMin = barrelMin;
        this.barrelMax = barrelMax;

        this.initInputValues();
        this.initOutputValues();
    }

    public int[] getExtremeValues(int min, int max) {
        return new int[]{min, min + 1, max - 1, max};
    }

    private void initInputValues() {
        int[] lockInputValues = getExtremeValues(lockMin, lockMax);
        int[] stockInputValues = getExtremeValues(stockMin, stockMax);
        int[] barrelInputValues = getExtremeValues(barrelMin, barrelMax);

        int lockNom = (lockMax - lockMin) / 2;
        int stockNom = (stockMax - stockMin) / 2;
        int barrelNom = (barrelMax - barrelMin) / 2;

        int i = 1;
        for (int barrelInputValue : barrelInputValues) {
            testCases.add(new TestCase(i++, lockNom, stockNom, barrelInputValue));
        }

        for (int stockInputValue : stockInputValues) {
            testCases.add(new TestCase(i++, lockNom, stockInputValue, barrelNom));
        }

        for (int lockInputValue : lockInputValues) {
            testCases.add(new TestCase(i++, lockInputValue, stockNom, barrelNom));
        }

        testCases.add(new TestCase(i, lockNom, stockNom, barrelNom));
    }

    private void initOutputValues() {
        testCases.forEach(testCase -> {
            double output = calcCommission(testCase.getFirstInputVariable(), testCase.getSecondInputVariable(), testCase.getThirdInputVariable());
            testCase.setExpectedOutput(Double.toString(output));
        });
    }

    private double calcCommission(int locks, int stocks, int barrels) {
        double commission = 0d;
        int sales = locks * LOCK_COST + stocks * STOCK_COST + barrels * BARREL_COST;

        int[] milestones = {0, 1000, 1800};
        double[] rates = {.1, .15, .2};

        for (int i = 2; i > -1; i--) {
            if (sales > milestones[i]) {
                for (int j = 0; j <= i; j++) {
                    commission += (i != j ? (milestones[j + 1] - milestones[j]) * rates[j] : (sales - milestones[j]) * rates[j]);
                }
                break;
            }
        }

        return commission;
    }

    public List<TestCase> getTestCases() {
        return this.testCases;
    }

    public int getLockMin() {
        return lockMin;
    }

    public void setLockMin(int lockMin) {
        this.lockMin = lockMin;

        this.initInputValues();
        this.initOutputValues();
    }

    public int getLockMax() {
        return lockMax;
    }

    public void setLockMax(int lockMax) {
        this.lockMax = lockMax;

        this.initInputValues();
        this.initOutputValues();
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;

        this.initInputValues();
        this.initOutputValues();
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;

        this.initInputValues();
        this.initOutputValues();
    }

    public int getBarrelMin() {
        return barrelMin;
    }

    public void setBarrelMin(int barrelMin) {
        this.barrelMin = barrelMin;

        this.initInputValues();
        this.initOutputValues();
    }

    public int getBarrelMax() {
        return barrelMax;
    }

    public void setBarrelMax(int barrelMax) {
        this.barrelMax = barrelMax;

        this.initInputValues();
        this.initOutputValues();
    }
}
