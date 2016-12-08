package de.hska.idletimecostsmanagement.domain;

import de.hska.workplacemanagement.domain.Workplace;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "idletimecost")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Idletimecost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "idletimecost")
    private List<Workplace> workplaces;

    public Idletimecost(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }
}
