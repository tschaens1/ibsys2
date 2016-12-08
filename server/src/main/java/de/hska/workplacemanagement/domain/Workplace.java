package de.hska.workplacemanagement.domain;

<<<<<<< HEAD
import de.hska.idletimecostsmanagement.domain.Idletimecost;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"workplace\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
=======
import de.hska.articlemanagement.domain.Article;
import de.hska.ordermanagement.domain.Order;
import de.hska.ordersinworkmanagement.domain.OrdersInWork;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "workplace")
>>>>>>> ae28e457e255bd470a4b922758dcf961984e7819
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id;

    @XmlAttribute
    private int setupevents;

    @XmlAttribute
    private int idletime;

    @XmlAttribute
    private double wageidletimecosts;

    @XmlAttribute
    private double wagecosts;

    @XmlAttribute
    private double machineidletimecosts;

    @ManyToOne
    @JoinColumn(name = "idletimecost_fk")
    private Idletimecost idletimecost;

    public Workplace(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSetupevents() {
        return setupevents;
    }

    public void setSetupevents(int setupevents) {
        this.setupevents = setupevents;
    }

    public int getIdletime() {
        return idletime;
    }

    public void setIdletime(int idletime) {
        this.idletime = idletime;
    }

    public double getWageidletimecosts() {
        return wageidletimecosts;
    }

    public void setWageidletimecosts(double wageidletimecosts) {
        this.wageidletimecosts = wageidletimecosts;
    }

    public double getWagecosts() {
        return wagecosts;
    }

    public void setWagecosts(double wagecosts) {
        this.wagecosts = wagecosts;
    }

    public double getMachineidletimecosts() {
        return machineidletimecosts;
    }

    public void setMachineidletimecosts(double machineidletimecosts) {
        this.machineidletimecosts = machineidletimecosts;
    }

    public Idletimecost getIdletimecost() {
        return idletimecost;
    }

    public void setIdletimecost(Idletimecost idletimecost) {
        this.idletimecost = idletimecost;
=======
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
>>>>>>> ae28e457e255bd470a4b922758dcf961984e7819
    }
}
