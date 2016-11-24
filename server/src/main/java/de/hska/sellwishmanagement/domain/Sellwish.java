package de.hska.sellwishmanagement.domain;

import de.hska.itemmanagement.domain.Item;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sellwish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "sellwish")
    @XmlElementWrapper
    @XmlElement(name="item")
    private List<Item> item;

    public Sellwish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

}
