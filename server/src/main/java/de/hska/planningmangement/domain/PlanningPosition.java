package de.hska.planningmangement.domain;

public class PlanningPosition {

	private int article;
	private int quantity;
	private double price;
	private double penalty;

	public PlanningPosition(int article, int quantity, double price, double penalty) {
		this.article = article;
		this.quantity = quantity;
		this.price = price;
		this.penalty = penalty;
	}

	public int getArticle() {
		return article;
	}

	public void setArticle(int article) {
		this.article = article;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public double getPenalty() {
		return penalty;
	}

	@Override
	public String toString() {
		return "PlanningPosition [article=" + article + ", quantity=" + quantity + ", price=" + price + ", penalty="
				+ penalty + "]";
	}

}
