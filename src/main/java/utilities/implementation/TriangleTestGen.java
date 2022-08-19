package utilities.implementation;

import entity.Variable;
import utilities.abstraction.ExtremeBoundaryValues;
import utilities.abstraction.TestCaseGenerator;

import java.util.List;

public class TriangleTestGen extends TestCaseGenerator {
    public TriangleTestGen(ExtremeBoundaryValues extremeBoundaryValues, Variable side1, Variable side2, Variable side3) {
        super(extremeBoundaryValues, side1, side2, side3);
    }

    @Override
    public String calc(List<Integer> values) {
        int side1 = values.get(0), side2 = values.get(1), side3 = values.get(2);

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
}
