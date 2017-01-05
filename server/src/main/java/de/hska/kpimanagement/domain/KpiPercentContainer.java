package de.hska.kpimanagement.domain;


public class KpiPercentContainer {
    private double current;
    private double average;

    public KpiPercentContainer() {
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

}
