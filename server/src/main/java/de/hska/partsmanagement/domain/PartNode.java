package de.hska.partsmanagement.domain;

import java.util.ArrayList;
import java.util.Objects;

public class PartNode {

	private Integer partNumber;
	private Integer amount;
	private ArrayList<PartNode> parts;

	public PartNode(Integer partNumber, Integer amount, ArrayList<PartNode> parts) {
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

		for (PartNode part : this.parts) {
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

	public ArrayList<PartNode> getParts() {
		return parts;
	}

	public void setParts(ArrayList<PartNode> parts) {
		this.parts = parts;
	}

}
