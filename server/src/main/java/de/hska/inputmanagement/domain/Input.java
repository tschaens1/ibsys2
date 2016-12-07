package de.hska.inputmanagement.domain;

import de.hska.orderlistmanagement.domain.OrderList;
import de.hska.productionlistmanagement.domain.ProductionList;
import de.hska.qualitycontrolmanagement.domain.QualityControl;
import de.hska.selldirectmanagement.domain.Selldirect;
import de.hska.sellwishmanagement.domain.Sellwish;
import de.hska.workingtimelistmanagement.domain.WorkingtimeList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private QualityControl qualitycontrol;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private Sellwish sellwish;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private Selldirect selldirect;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private OrderList orderlist;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private ProductionList productionlist;

    @OneToOne(cascade = CascadeType.ALL)
    @XmlElement
    private WorkingtimeList workingtimelist;

    public Input() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QualityControl getQualitycontrol() {
        return qualitycontrol;
    }

    public void setQualitycontrol(QualityControl qualitycontrol) {
        this.qualitycontrol = qualitycontrol;
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

    public OrderList getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(OrderList orderlist) {
        this.orderlist = orderlist;
    }

    public ProductionList getProductionlist() {
        return productionlist;
    }

    public void setProductionlist(ProductionList productionlist) {
        this.productionlist = productionlist;
    }

    public WorkingtimeList getWorkingtimelist() {
        return workingtimelist;
    }

    public void setWorkingtimelist(WorkingtimeList workingtimelist) {
        this.workingtimelist = workingtimelist;
    }
}