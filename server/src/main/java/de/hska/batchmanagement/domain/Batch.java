package de.hska.batchmanagement.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name =  "\"batch\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    private int amount;

    @XmlAttribute
    private int cycletime;

    @XmlAttribute
    private int cost;

    public Batch(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCycletime() {
        return cycletime;
    }

    public void setCycletime(int cycletime) {
        this.cycletime = cycletime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
