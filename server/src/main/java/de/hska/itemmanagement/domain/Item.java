package de.hska.itemmanagement.domain;

import de.hska.articlemanagement.domain.Article;
import de.hska.selldirectmanagement.domain.Selldirect;
import de.hska.sellwishmanagement.domain.Sellwish;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Article article;

    @XmlAttribute(name ="quantity")
    private Integer quantity;

    @XmlAttribute(name ="price")
    private Double price;

    @XmlAttribute(name ="penalty")
    private Double penalty;

    @ManyToOne
    @JoinColumn(name = "sellwish_id")
    private Sellwish sellwish;

    @ManyToOne
    @JoinColumn(name = "selldirect_id")
    private Selldirect selldirect;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Sellwish getSellwish() {
        return sellwish;
    }

    public void setSellwish(Sellwish sellwish) {
        this.sellwish = sellwish;
    }

    public Selldirect getSelldirect() {
        return selldirect;
    }

    public void setSelldirect(Selldirect selldirect) {
        this.selldirect = selldirect;
    }
}
