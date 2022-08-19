package main;

import com.formdev.flatlaf.*;
import entity.*;
import utilities.implementation.*;
import utilities.abstraction.*;
import views.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        new MainFrame();
    }

    static void printToConsole() {
        ExtremeBoundaryValues normalExtremeBoundaryValues = variable -> new Integer[]{variable.getMin(), variable.getMinUpper(), variable.getMaxLower(), variable.getMax()};
        ExtremeBoundaryValues robustExtremeBoundaryValues = variable -> new Integer[]{variable.getMinLower(), variable.getMin(), variable.getMinUpper(), variable.getMaxLower(), variable.getMax(), variable.getMaxUpper()};

        String[] triangleHeaders = {"#", "Side 1", "Side 2", "Side 3", "Expected Output"};
        String[] commissionHeaders = {"#", "Stocks", "Locks", "Barrels", "Expected Output"};

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Triangle: normal extreme input values");

        Variable side = new Variable(5, 205);

        TriangleTestGen triangle = new TriangleTestGen(normalExtremeBoundaryValues, side, side, side);
        printTestCases(triangle.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Triangle: Robust extreme input values");

        TriangleTestGen robustTriangle = new TriangleTestGen(robustExtremeBoundaryValues, side, side, side);
        printTestCases(robustTriangle.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Commission: normal extreme input values");

        Variable lock = new Variable(1, 80);
        Variable stock = new Variable(1, 90);
        Variable barrel = new Variable(1, 100);

        CommissionTestGen commission = new CommissionTestGen(robustExtremeBoundaryValues, lock, stock, barrel);
        printTestCases(commission.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Commission: Robust extreme input values");

        CommissionTestGen robustCommission = new CommissionTestGen(robustExtremeBoundaryValues, lock, stock, barrel);
        printTestCases(robustCommission.getTestCases());
    }

    static void printTestCases(List<TestCase> testCases) {
        testCases.forEach(testCase -> {
            System.out.print(testCase.getId() + " ");
            System.out.println(testCase);
        });
    }

}
