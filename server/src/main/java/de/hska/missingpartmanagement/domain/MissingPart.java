package de.hska.missingpartmanagement.domain;

import de.hska.waitinglistmanagement.domain.WaitingList;
import de.hska.waitingliststockmanagement.domain.WaitinglistStock;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "missingpart")
public class MissingPart {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id")
    private Long Id;

    @OneToOne
    private WaitingList waitinglist;

    @ManyToOne
    @JoinColumn(name = "waitingliststock_fk")
    private WaitinglistStock waitingliststock;

    public MissingPart() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public WaitingList getWaitinglist() {
        return waitinglist;
    }

    public void setWaitinglist(WaitingList waitinglist) {
        this.waitinglist = waitinglist;
    }
}
