package de.hska.futureinwardstockmovementmanagement.domain;

import de.hska.ordermanagement.domain.Order;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "futureinwardstockmovement")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Futureinwardstockmovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "futureinwardstockmovement")
    private List<Order> orders;

    public Futureinwardstockmovement (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
