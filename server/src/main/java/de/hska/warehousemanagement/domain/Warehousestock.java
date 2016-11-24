package de.hska.warehousemanagement.domain;

import de.hska.articlemanagement.domain.Article;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehousestock")
public class Warehousestock {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToMany(mappedBy = "warehousestock")
    private List<Article> articles;

    public Warehousestock() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
