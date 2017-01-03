package de.hska.partsmanagement.domain;

import java.util.ArrayList;
import java.util.Objects;

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

		for (ManufacturingPartNode part : this.parts) {
			if (Objects.equals(part.partNumber, partNumber)) {
				return true;
			}
		}

		return false;
	}

	public Integer getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(Integer partNumber) {
		this.partNumber = partNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ArrayList<ManufacturingPartNode> getParts() {
		return parts;
	}

	public void setParts(ArrayList<ManufacturingPartNode> parts) {
		this.parts = parts;
	}

}
