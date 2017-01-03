package de.hska.workplacemanagement.domain;

public class ProductionOrder {
	private Integer productNumber;
	private Integer amount;
	private Integer period;
	private Integer workplaceId;
	private Boolean isInWork;
	private Priority priority;

	public ProductionOrder(Integer productNumber, Integer amount, Integer period) {
		this.productNumber = productNumber;
		this.amount = amount;
		this.period = period;
	}

	public ProductionOrder(Integer productNumber, Integer amount, Integer period, Integer workplaceId) {
		this.productNumber = productNumber;
		this.amount = amount;
		this.period = period;
		this.workplaceId = workplaceId;
		this.isInWork = false;
		this.priority = Priority.Normal;
	}

	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getWorkplaceId() {
		return workplaceId;
	}

	public void setWorkplaceId(Integer workplaceId) {
		this.workplaceId = workplaceId;
	}

	public Boolean getInWork() {
		return isInWork;
	}

	public void setInWork(Boolean inWork) {
		isInWork = inWork;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}
