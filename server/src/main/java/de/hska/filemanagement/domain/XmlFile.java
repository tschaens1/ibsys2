package de.hska.filemanagement.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "xmlfile")
public class XmlFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "text")
    private String content;

    public XmlFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
