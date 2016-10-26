package de.hska.waitingqueuemanagenment.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Created by andre on 26.10.2016.
 */
@Entity
@Table(name="waitingqueue")
public class WaitingQueue {

    @Id
    @GeneratedValue
    private Long Id;


}
