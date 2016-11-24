package de.hska.articlemanagement.domain;

import de.hska.warehousemanagement.domain.Warehousestock;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

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
    @Column(name = "stockvalue", nullable = false)
    private Double stockvalue;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "warehousestock_fk")
    private Warehousestock warehousestock;

    public Article() {
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

    public Double getStockvalue() {
        return stockvalue;
    }

    public void setStockvalue(Double stockvalue) {
        this.stockvalue = stockvalue;
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

    public Warehousestock getWarehousestock() {
        return warehousestock;
    }

    public void setWarehousestock(Warehousestock warehousestock) {
        this.warehousestock = warehousestock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (id != null ? !id.equals(article.id) : article.id != null) return false;
        if (amount != null ? !amount.equals(article.amount) : article.amount != null) return false;
        if (startamount != null ? !startamount.equals(article.startamount) : article.startamount != null) return false;
        if (stockvalue != null ? !stockvalue.equals(article.stockvalue) : article.stockvalue != null) return false;
        if (percentage != null ? !percentage.equals(article.percentage) : article.percentage != null) return false;
        return price != null ? price.equals(article.price) : article.price == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (startamount != null ? startamount.hashCode() : 0);
        result = 31 * result + (stockvalue != null ? stockvalue.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", amount=" + amount +
                ", startamount=" + startamount +
                ", stockvalue=" + stockvalue +
                ", percentage=" + percentage +
                ", price=" + price +
                '}';
    }
}
