package de.hska.summanagement.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"sum\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sum {

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

    public Sum(){

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
}
