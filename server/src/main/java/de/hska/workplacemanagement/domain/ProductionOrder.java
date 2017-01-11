package de.hska.workplacemanagement.domain;

public class ProductionOrder {
	private Integer productNumber;
	private Integer amount;
	private Integer period;
	private Integer orderId;
	private Integer timeNeed;
	private Integer workplaceId;
	private Boolean isInWork;
	private Priority priority;

	public ProductionOrder() {

	}

	public ProductionOrder(Integer productNumber, Integer amount, Integer period, Boolean isInWork,
			Integer workplaceId) {
		this.productNumber = productNumber;
		this.amount = amount;
		this.period = period;
		this.isInWork = isInWork;
		this.workplaceId = workplaceId;
	}

	public ProductionOrder(Integer productNumber, Integer amount, Integer period, Boolean isInWork, Integer workplaceId,
			Integer orderId, Integer timeNeed) {
		this.productNumber = productNumber;
		this.amount = amount;
		this.period = period;
		this.orderId = orderId;
		this.isInWork = isInWork;
		this.workplaceId = workplaceId;
		this.timeNeed = timeNeed;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Boolean getIsInWork() {
		return isInWork;
	}

	public void setIsInWork(Boolean isInWork) {
		this.isInWork = isInWork;
	}

	public Integer getTimeNeed() {
		return timeNeed;
	}

	public void setTimeNeed(Integer timeNeed) {
		this.timeNeed = timeNeed;
	}

	@Override
	public String toString() {
		return "ProductionOrder [productNumber=" + productNumber + ", amount=" + amount + ", period=" + period
				+ ", workplaceId=" + workplaceId + ", isInWork=" + isInWork + ", priority=" + priority + "]";
	}

}
