package utilities.abstraction;

import entity.Variable;

@FunctionalInterface
public interface ExtremeBoundaryValues {

    public Integer[] getExtremeValues(Variable variable);

}
