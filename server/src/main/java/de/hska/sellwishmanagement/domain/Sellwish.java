package de.hska.sellwishmanagement.domain;

import de.hska.itemmanagement.domain.Item;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sellwish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sellwish", cascade = CascadeType.ALL)
    @XmlElement(name = "item")
    private List<Item> sellwishes;

    public Sellwish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getSellwishes() {
        return sellwishes;
    }

    public void setSellwishes(List<Item> sellwishes) {
        this.sellwishes = sellwishes;
    }
}
