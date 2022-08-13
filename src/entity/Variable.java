package entity;

public class Variable {
    private int min;
    private int max;

    public Variable(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getNom() { return (max - min) / 2; }

    public int getMin() { return min; }

    public int getMinLower() { return min - 1; }

    public int getMinUpper() { return min + 1; }

    public void setMin(int min) { this.min = min; }

    public int getMax() { return max; }

    public int getMaxLower() { return max - 1; }

    public int getMaxUpper() { return max + 1; }

    public void setMax(int max) { this.max = max; }
}
