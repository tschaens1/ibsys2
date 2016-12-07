package de.hska.articlemanagement.domain;

import de.hska.warehousemanagement.domain.Warehousestock;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer articlenumber;

    @Enumerated(EnumType.STRING)
    private ArticleType articletype;

    @Enumerated(EnumType.STRING)
    private ArticleUsage articleusage;

    private Integer startamount;

    private Double price;

    private Integer amount;

    private Double stockvalue;

    @XmlAttribute(name = "pct")
    private Double percentage;

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

    public Integer getArticlenumber() {
        return articlenumber;
    }

    public void setArticlenumber(Integer articlenumber) {
        this.articlenumber = articlenumber;
    }

    public ArticleType getArticletype() {
        return articletype;
    }

    public void setArticletype(ArticleType articletype) {
        this.articletype = articletype;
    }

    public ArticleUsage getArticleusage() {
        return articleusage;
    }

    public void setArticleusage(ArticleUsage articleusage) {
        this.articleusage = articleusage;
    }

    public Integer getStartamount() {
        return startamount;
    }

    public void setStartamount(Integer startamount) {
        this.startamount = startamount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Warehousestock getWarehousestock() {
        return warehousestock;
    }

    public void setWarehousestock(Warehousestock warehousestock) {
        this.warehousestock = warehousestock;
    }
}
