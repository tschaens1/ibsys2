package de.hska.selldirectmanagement.domain;

import de.hska.itemmanagement.domain.Item;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Selldirect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "selldirect")
    @XmlElementWrapper(name = "selldirect")
    @XmlElement(name="item")
    private List<Item> item;

    public Selldirect() {
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

    public void setItems(List<Item> item) {
        this.item = item;
    }
}
