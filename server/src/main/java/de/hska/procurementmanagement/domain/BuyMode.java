package de.hska.procurementmanagement.domain;

public enum BuyMode {
    Normal(5), Fast(4);

    private final int value;

    BuyMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
