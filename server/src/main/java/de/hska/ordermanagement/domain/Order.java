package de.hska.ordermanagement.domain;

import de.hska.periodmanagement.domain.Period;
import de.hska.workplacemanagement.domain.Workplace;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"order\"")
public class Order {

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @OneToOne
    @JoinColumn(name = "period_fk", nullable = false)
    private Period period;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "workplace_fk", nullable = false)
    private Workplace workplace;

    @NotNull
    @Column(name = "inwork", nullable = false)
    private Boolean isInWork;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private Priority priority;

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Boolean getInWork() {
        return isInWork;
    }

    public void setInWork(Boolean inWork) {
        isInWork = inWork;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        if (period != null ? !period.equals(order.period) : order.period != null) return false;
        if (workplace != null ? !workplace.equals(order.workplace) : order.workplace != null) return false;
        if (isInWork != null ? !isInWork.equals(order.isInWork) : order.isInWork != null) return false;
        return priority == order.priority;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (workplace != null ? workplace.hashCode() : 0);
        result = 31 * result + (isInWork != null ? isInWork.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", period=" + period +
                ", workplace=" + workplace +
                ", isInWork=" + isInWork +
                ", priority=" + priority +
                '}';
    }
}
