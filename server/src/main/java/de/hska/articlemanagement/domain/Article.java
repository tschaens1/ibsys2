package de.hska.articlemanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @Column(name = "startamount", nullable = false)
    private Integer startamount;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    public Article(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStartamount() {
        return startamount;
    }

    public void setStartamount(Integer startamount) {
        this.startamount = startamount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (getId() != null ? !getId().equals(article.getId()) : article.getId() != null) return false;
        if (getAmount() != null ? !getAmount().equals(article.getAmount()) : article.getAmount() != null) return false;
        if (getStartamount() != null ? !getStartamount().equals(article.getStartamount()) : article.getStartamount() != null)
            return false;
        if (getPercentage() != null ? !getPercentage().equals(article.getPercentage()) : article.getPercentage() != null)
            return false;
        return getPrice() != null ? getPrice().equals(article.getPrice()) : article.getPrice() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getStartamount() != null ? getStartamount().hashCode() : 0);
        result = 31 * result + (getPercentage() != null ? getPercentage().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", amount=" + amount +
                ", startamount=" + startamount +
                ", percentage=" + percentage +
                ", price=" + price +
                '}';
    }
}
