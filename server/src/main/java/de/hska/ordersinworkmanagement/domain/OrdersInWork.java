package de.hska.ordersinworkmanagement.domain;


import de.hska.workplacemanagement.domain.Workplace;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ordersinwork")
public class OrdersInWork {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "ordersinwork")
    private List<Workplace> workplacelist;

    public OrdersInWork() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Workplace> getWorkplacelist() {
        return workplacelist;
    }

    public void setWorkplacelist(List<Workplace> workplacelist) {
        this.workplacelist = workplacelist;
    }
}
