package de.hska.ordermanagement.domain;

import de.hska.batchmanagement.domain.Batch;
import de.hska.futureinwardstockmovementmanagement.domain.Futureinwardstockmovement;
import de.hska.orderlistmanagement.domain.OrderList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderlist_fk")
    private OrderList orderlist;

    @ManyToOne
    @JoinColumn(name = "futureinwardstockmovement_fk")
    private Futureinwardstockmovement futureinwardstockmovement;

    @XmlAttribute
    private Integer article;

    @XmlAttribute
    private Integer quantity;

    @XmlAttribute
    private Integer modus;

    @XmlAttribute
    private Double averageunitcosts;

    @OneToMany
    List<Batch> batchlist;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderList getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(OrderList orderlist) {
        this.orderlist = orderlist;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getModus() {
        return modus;
    }

    public void setModus(Integer modus) {
        this.modus = modus;
    }

    public Double getAverageunitcosts() {
        return averageunitcosts;
    }

    public void setAverageunitcosts(Double averageunitcosts) {
        this.averageunitcosts = averageunitcosts;
    }

    public List<Batch> getBatchlist() {
        return batchlist;
    }

    public void setBatchlist(List<Batch> batchlist) {
        this.batchlist = batchlist;
    }
}
