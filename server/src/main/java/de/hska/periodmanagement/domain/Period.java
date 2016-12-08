package de.hska.periodmanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "period")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"group\"")
    private Long group;

    private Long game;

    private XmlFile xmlFile;

    public Period() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getGame() {
        return game;
    }

    public void setGame(Long game) {
        this.game = game;
    }

    public XmlFile getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(XmlFile xmlFile) {
        this.xmlFile = xmlFile;
    }
}
