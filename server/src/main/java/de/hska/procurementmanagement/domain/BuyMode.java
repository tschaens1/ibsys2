package de.hska.procurementmanagement.domain;

public enum BuyMode {
    Normal {
        Integer AsInteger() {
            return 4;
        }
    },
    Fast {
        Integer AsInteger() {
            return 5;
        }
    }
}
