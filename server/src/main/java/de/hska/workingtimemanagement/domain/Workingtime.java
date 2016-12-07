package de.hska.workingtimemanagement.domain;

import de.hska.workingtimelistmanagement.domain.WorkingtimeList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Workingtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer station;
    private Integer shift;
    private Integer overtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workingtimelist_fk")
    private WorkingtimeList workingtimelist;

    public Workingtime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public WorkingtimeList getWorkingtimelist() {
        return workingtimelist;
    }

    public void setWorkingtimelist(WorkingtimeList workingtimelist) {
        this.workingtimelist = workingtimelist;
    }
}
