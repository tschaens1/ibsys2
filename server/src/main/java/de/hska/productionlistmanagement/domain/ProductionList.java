package de.hska.productionlistmanagement.domain;

import de.hska.productionmanagement.domain.Production;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductionList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="productionList")
    private List<Production> productions;

    public ProductionList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

}
