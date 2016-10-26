package de.hska.purchasemanagement;

import de.hska.articlemanagement.domain.Article;
import de.hska.periodmanagement.domain.Period;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by andre on 26.10.2016.
 */
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "purchaseType", nullable = false)
    private PurchaseType purchaseType;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "article_fk", nullable = false)
    private Article article;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @OneToOne()
    @JoinColumn(name = "period_fk", nullable = false)
    private Period period;

    @NotNull
    @Column(name = "cumulativeCosts", nullable = false)
    private Double cumulativeCosts;

    @NotNull
    @Column(name = "materialCosts", nullable = false)
    private Double materialCosts;

    @NotNull
    @Column(name = "purchasingCosts", nullable = false)
    private Double purchasingCosts;

    @NotNull
    @Column(name = "deliveryCosts", nullable = false)
    private Double deliveryCosts;

    @NotNull
    @Column(name = "unitCosts", nullable = false)
    private Double unitCosts;

    public Purchase(){

    }

    public Long getId() {
        return Id;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Double getCumulativeCosts() {
        return cumulativeCosts;
    }

    public void setCumulativeCosts(Double cumulativeCosts) {
        this.cumulativeCosts = cumulativeCosts;
    }

    public Double getMaterialCosts() {
        return materialCosts;
    }

    public void setMaterialCosts(Double materialCosts) {
        this.materialCosts = materialCosts;
    }

    public Double getPurchasingCosts() {
        return purchasingCosts;
    }

    public void setPurchasingCosts(Double purchasingCosts) {
        this.purchasingCosts = purchasingCosts;
    }

    public Double getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(Double deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public Double getUnitCosts() {
        return unitCosts;
    }

    public void setUnitCosts(Double unitCosts) {
        this.unitCosts = unitCosts;
    }
}
