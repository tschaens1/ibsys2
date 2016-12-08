package de.hska.completedordersmanagement.domain;

import de.hska.ordermanagement.domain.Order;
import de.hska.resultsmanagement.domain.Results;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "completedorders")
public class Completedorders {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany
    private List<Order> orderlist;
}
