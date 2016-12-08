package de.hska.workplacemanagement.domain;

import de.hska.articlemanagement.domain.Article;
import de.hska.batchmanagement.domain.Batch;
import de.hska.idletimecostsmanagement.domain.Idletimecost;
import de.hska.ordermanagement.domain.Order;
import de.hska.ordersinworkmanagement.domain.OrdersInWork;
import de.hska.waitinglistworkstationmanagement.domain.Waitinglistworkstation;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "workplace\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordersinwork_fk")
    private OrdersInWork ordersinwork;

    @ManyToOne
    @JoinColumn(name = "waitinglistworkstation_fk")
    private Waitinglistworkstation waitinglistworkstation;

    @ManyToOne
    @JoinColumn(name = "workplace_fk")
    private Idletimecost idletimecost;

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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Idletimecost getIdletimecost() {
        return idletimecost;
    }

    public void setIdletimecost(Idletimecost idletimecost) {
        this.idletimecost = idletimecost;
    }
}
