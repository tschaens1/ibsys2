package de.hska.workplacemanagement.domain;

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
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    }
}
