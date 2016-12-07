package de.hska.orderlistmanagement.domain;

import de.hska.ordermanagement.domain.Order;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "orderlist")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orderlist", cascade = CascadeType.ALL)
    @XmlElement(name = "order")
    @Column(name = "\"order\"")
    private List<Order> orders;

    public OrderList() {
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
