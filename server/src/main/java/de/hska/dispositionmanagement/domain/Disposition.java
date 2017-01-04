package de.hska.dispositionmanagement.domain;

import java.util.ArrayList;

import de.hska.partsmanagement.domain.ManufacturingPart;
import de.hska.planningmangement.domain.PlanningPosition;

public class Disposition {

	private int partNumber;
	private int safetyStockvalue;
	private int amount;
	private PlanningPosition production;
	private PlanningPosition selldirect;
	private Disposition parent;
	private ArrayList<ManufacturingPart> manufacturing;

	public Disposition() {

	}

	public Disposition(int partNumber, int safetyStockvalue, int amount, PlanningPosition production,
			PlanningPosition selldirect, Disposition parent, ArrayList<ManufacturingPart> manufacturing) {
		this.partNumber = partNumber;
		this.safetyStockvalue = safetyStockvalue;
		this.amount = amount;
		this.production = production;
		this.selldirect = selldirect;
		this.parent = parent;
		this.manufacturing = manufacturing;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public int getSafetyStockvalue() {
		return safetyStockvalue;
	}

	public void setSafetyStockvalue(int safetyStockvalue) {
		this.safetyStockvalue = safetyStockvalue;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public PlanningPosition getProduction() {
		return production;
	}

	public void setProduction(PlanningPosition production) {
		this.production = production;
	}

	public PlanningPosition getSelldirect() {
		return selldirect;
	}

	public void setSelldirect(PlanningPosition selldirect) {
		this.selldirect = selldirect;
	}

	public Disposition getParent() {
		return parent;
	}

	public void setParent(Disposition parent) {
		this.parent = parent;
	}

	public ArrayList<ManufacturingPart> getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(ArrayList<ManufacturingPart> manufacturing) {
		this.manufacturing = manufacturing;
	}

	public ArrayList<PlanningPosition> getOrders() {
		ArrayList<PlanningPosition> orders = new ArrayList<PlanningPosition>();
		orders.add(this.production);
		orders.add(this.selldirect);
		return orders;
	}

}
