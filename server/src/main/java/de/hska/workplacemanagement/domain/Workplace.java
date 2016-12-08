package de.hska.workplacemanagement.domain;

import de.hska.articlemanagement.domain.Article;
import de.hska.ordermanagement.domain.Order;
import de.hska.ordersinworkmanagement.domain.OrdersInWork;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "workplace")
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "ordersinwork_fk")
    private OrdersInWork ordersinwork;

    @XmlAttribute
    private Integer period;

    @OneToOne
    @XmlAttribute
    private Order order;

    @OneToOne
    @XmlAttribute
    private Batch batch;

    @XmlAttribute
    private Article item;

    @XmlAttribute
    private Integer amount;

    @XmlAttribute
    private Integer timeneeded;

    public Workplace() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public OrdersInWork getOrdersinwork() {
        return ordersinwork;
    }

    public void setOrdersinwork(OrdersInWork ordersinwork) {
        this.ordersinwork = ordersinwork;
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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
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

    public Integer getTimeneeded() {
        return timeneeded;
    }

    public void setTimeneeded(Integer timeneeded) {
        this.timeneeded = timeneeded;
    }
}
