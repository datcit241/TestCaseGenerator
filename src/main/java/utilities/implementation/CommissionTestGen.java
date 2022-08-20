package utilities.implementation;

import entity.Variable;
import utilities.abstraction.ExtremeBoundaryValues;
import utilities.abstraction.TestCaseGenerator;

import java.util.ArrayList;
import java.util.List;

public class CommissionTestGen extends TestCaseGenerator {
    private static int lockCost = 45;
    private static int stockCost = 30;
    private static int barrelCost = 25;

    public CommissionTestGen(ExtremeBoundaryValues extremeBoundaryValues, Variable lock, Variable stock, Variable barrel) {
        super(extremeBoundaryValues, lock, stock, barrel);
    }

    @Override
    public String calc(List<Integer> values) {
        double commission = 0d;
        int sales = values.get(0) * lockCost + values.get(1) * stockCost + values.get(2) * barrelCost;

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

        return Double.toString(commission);
    }

    public static int getLockCost() { return lockCost; }

    public static void setLockCost(int lockCost) { CommissionTestGen.lockCost = lockCost; }

    public static int getStockCost() { return stockCost; }

    public static void setStockCost(int stockCost) { CommissionTestGen.stockCost = stockCost; }

    public static int getBarrelCost() { return barrelCost; }

    public static void setBarrelCost(int barrelCost) { CommissionTestGen.barrelCost = barrelCost; }
}
