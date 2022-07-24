package main;

import entity.TestCase;
import utilities.CommissionTestCaseGenerator;
import utilities.RobustCommissionTestCaseGenerator;
import utilities.RobustTriangleTestCaseGenerator;
import utilities.TriangleTestCaseGenerator;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Triangle: normal extreme input values");

        TriangleTestCaseGenerator gen = new TriangleTestCaseGenerator(5, 205);
        printTestCases(gen.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Triangle: Robust extreme input values");

        TriangleTestCaseGenerator gen2 = new RobustTriangleTestCaseGenerator(5, 205);
        printTestCases(gen2.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Commission: normal extreme input values");

        CommissionTestCaseGenerator gen3 = new CommissionTestCaseGenerator(1, 80, 1, 90, 1, 100);
        printTestCases(gen3.getTestCases());

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Commission: Robust extreme input values");

        CommissionTestCaseGenerator gen4 = new RobustCommissionTestCaseGenerator(1, 80, 1, 90, 1, 100);
        printTestCases(gen4.getTestCases());
    }

    static void printTestCases(List<TestCase> testCases) {
        testCases.forEach(testCase -> {
            System.out.print(testCase.getId() + " ");
            System.out.println(testCase);
        });
    }

}
