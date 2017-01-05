package de.hska.kpimanagement.domain;

public class KpiContainer {
    private double current;
    private double average;
    private double sum;

    public KpiContainer() {
        this.sum = -1;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
