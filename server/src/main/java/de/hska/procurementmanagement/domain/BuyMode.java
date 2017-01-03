package de.hska.procurementmanagement.domain;

public enum BuyMode {
    Normal(4), Fast(5);

    private final int value;

    BuyMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
