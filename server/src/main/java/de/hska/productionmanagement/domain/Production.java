package de.hska.productionmanagement.domain;

import de.hska.articlemanagement.domain.Article;
import de.hska.productionlistmanagement.domain.ProductionList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productionlist_fk")
    private ProductionList productionList;

    @OneToOne
    private Article article;

    private Integer quantity;

    public Production() {
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

    public ProductionList getProductionList() {
        return productionList;
    }

    public void setProductionList(ProductionList productionList) {
        this.productionList = productionList;
    }
}
