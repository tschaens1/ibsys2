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

    @OneToMany(mappedBy = "workplaces")
    private List<Workplace> workplaces;



    public Idletimecost(){

    }

}
