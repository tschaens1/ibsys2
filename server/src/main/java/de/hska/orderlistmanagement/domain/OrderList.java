package de.hska.orderlistmanagement.domain;

import de.hska.ordermanagement.domain.Order;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name="orderlist")
@XmlRootElement
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "orderlist")
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
