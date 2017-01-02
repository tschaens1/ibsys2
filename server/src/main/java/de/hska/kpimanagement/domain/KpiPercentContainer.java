package de.hska.kpimanagement.domain;


public class KpiPercentContainer {
    private String current;
    private String average;

    public KpiPercentContainer() {
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
