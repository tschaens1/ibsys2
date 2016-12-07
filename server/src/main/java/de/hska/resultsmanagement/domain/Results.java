package de.hska.resultsmanagement.domain;

import de.hska.warehousemanagement.domain.Warehousestock;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Warehousestock warehousestock;
    private Integer game;

    @Column(name = "\"group\"")
    private Integer group;

    private Integer period;

    public Results() {
    }

    public Warehousestock getWarehousestock() {
        return warehousestock;
    }

    public void setWarehousestock(Warehousestock warehousestock) {
        this.warehousestock = warehousestock;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
