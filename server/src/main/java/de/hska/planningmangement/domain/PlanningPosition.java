package de.hska.planningmangement.domain;

public class PlanningPosition {

	private int article;
	private int quantity;
	private int price;
	private int penalty;

	public PlanningPosition(int article, int quantity, int price, int penalty) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "PlanningPosition [article=" + article + ", quantity=" + quantity + ", price=" + price + ", penalty="
				+ penalty + "]";
	}

}
