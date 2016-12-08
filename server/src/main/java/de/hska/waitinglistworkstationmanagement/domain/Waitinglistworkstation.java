package de.hska.waitinglistworkstationmanagement.domain;

import de.hska.workplacemanagement.domain.Workplace;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "waitinglistworkstation")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Waitinglistworkstation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "waitinglistworkstation")
    private List<Workplace> workplaces;

    public Waitinglistworkstation(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }
}
