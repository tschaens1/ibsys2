package de.hska.waitingqueuemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name="waitingqueue")
public class WaitingQueue {

    @Id
    @GeneratedValue
    private Long Id;


}
