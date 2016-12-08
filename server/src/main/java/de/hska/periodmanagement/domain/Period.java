package de.hska.periodmanagement.domain;

import de.hska.xmlfilemanagement.domain.JsonFile;

import javax.persistence.*;

@Entity
@Table(name = "period")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long counter;

    @Column(name = "\"group\"")
    private Long group;

    private Long game;

    @OneToOne(cascade = CascadeType.ALL)
    private JsonFile jsonFile;

    public Period() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
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

    public JsonFile getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(JsonFile jsonFile) {
        this.jsonFile = jsonFile;
    }
}
