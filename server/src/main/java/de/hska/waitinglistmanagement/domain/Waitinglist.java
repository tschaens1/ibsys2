package de.hska.waitinglistmanagement.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"waitinglist\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Waitinglist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    private int period;

    @XmlAttribute
    private int order;

    @XmlAttribute
    private int firstbatch;

    @XmlAttribute
    private int lastbatch;

    @XmlAttribute
    private int item;

    @XmlAttribute
    private int amount;

    @XmlAttribute
    private int timeneed;

    public Waitinglist(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getFirstbatch() {
        return firstbatch;
    }

    public void setFirstbatch(int firstbatch) {
        this.firstbatch = firstbatch;
    }

    public int getLastbatch() {
        return lastbatch;
    }

    public void setLastbatch(int lastbatch) {
        this.lastbatch = lastbatch;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTimeneed() {
        return timeneed;
    }

    public void setTimeneed(int timeneed) {
        this.timeneed = timeneed;
    }
}
