package de.hska.partsmanagement.domain;

public enum ProcurementRisk {
    LOW (0.25),
    NORMAL (0.5),
    HIGH (1.0);

    ProcurementRisk(Double factor) {
        this.factor = factor;
    }

    private Double factor;

    public Double getFactor() {
        return factor;
    }

}
