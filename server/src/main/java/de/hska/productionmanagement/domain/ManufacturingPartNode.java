package de.hska.productionmanagement.domain;

import java.util.ArrayList;

public class ManufacturingPartNode {

    private Integer partNumber;
    private Integer amount;
    private ArrayList<ManufacturingPartNode> parts;

    public ManufacturingPartNode(Integer partNumber, Integer amount, ArrayList<ManufacturingPartNode> parts) {
        this.partNumber = partNumber;
        this.amount = amount;
        this.parts = parts;
    }

    public Boolean hasParts() {
        return this.parts != null;
    }

    public Boolean hasCertainPart(Integer partNumber) {
        if (!this.hasParts()) {
            return false;
        }

        for (int i = 0; i < this.parts.size(); i++) {
            if (this.parts.get(i).partNumber == partNumber) {
                return true;
            }
        }

        return false;
    }
}
