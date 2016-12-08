package de.hska.waitinglistmanagement.domain;

import de.hska.articlemanagement.domain.Article;
import de.hska.batchmanagement.domain.Batch;
import de.hska.ordermanagement.domain.Order;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "missingpart")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WaitingList {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Integer period;

    @Column(name = "\"order\"")
    @XmlAttribute(name = "\"order\"")
    private Order order;

    @XmlAttribute(name = "firstbatch")
    private Batch firstbatch;

    @XmlAttribute(name = "lastbatch")
    private Batch lastbatch;

    @XmlAttribute(name = "item")
    private Article item;

    @XmlAttribute
    private Integer amount;

    public WaitingList() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Batch getFirstbatch() {
        return firstbatch;
    }

    public void setFirstbatch(Batch firstbatch) {
        this.firstbatch = firstbatch;
    }

    public Batch getLastbatch() {
        return lastbatch;
    }

    public void setLastbatch(Batch lastbatch) {
        this.lastbatch = lastbatch;
    }

    public Article getItem() {
        return item;
    }

    public void setItem(Article item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
