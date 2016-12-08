package de.hska.waitingliststockmanagement.domain;

import de.hska.missingpartmanagement.domain.MissingPart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "waitingliststock")
public class WaitinglistStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "waitingliststock")
    private List<MissingPart> missingparts;

    public WaitinglistStock() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<MissingPart> getMissingparts() {
        return missingparts;
    }

    public void setMissingparts(List<MissingPart> missingparts) {
        this.missingparts = missingparts;
    }
}
