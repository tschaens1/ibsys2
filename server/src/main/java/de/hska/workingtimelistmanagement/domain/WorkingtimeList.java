package de.hska.workingtimelistmanagement.domain;

import de.hska.workingtimemanagement.domain.Workingtime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkingtimeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "workingtimelist", cascade = CascadeType.ALL)
    @XmlElement(name = "workingtime")
    private List<Workingtime> workingtimes;

    public WorkingtimeList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Workingtime> getWorkingtimes() {
        return workingtimes;
    }

    public void setWorkingtimes(List<Workingtime> workingtimes) {
        this.workingtimes = workingtimes;
    }
}
