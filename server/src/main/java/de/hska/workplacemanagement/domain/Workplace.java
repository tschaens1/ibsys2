package de.hska.workplacemanagement.domain;

import de.hska.ordermanagement.domain.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "setupTime", nullable = false)
    private Double setupTime;

    @NotNull
    @Column(name = "processingTime", nullable = false)
    private Double processingTime;

    @NotNull
    @Column(name = "workingTime", nullable = false)
    private Double workingTime;

    @NotNull
    @OneToMany(mappedBy = "workplace")
    private List <Order> orders;

    // TODO - erzeugnis_id:number;

    public Workplace(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Double setupTime) {
        this.setupTime = setupTime;
    }

    public Double getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Double processingTime) {
        this.processingTime = processingTime;
    }

    public Double getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Double workingTime) {
        this.workingTime = workingTime;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
