package de.hska.periodmanagement.domain;

import javax.persistence.*;

@Entity
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "periodnumber", nullable = false)
    private Integer periodNumber;
}
