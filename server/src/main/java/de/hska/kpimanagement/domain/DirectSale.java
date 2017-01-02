package de.hska.kpimanagement.domain;

public class DirectSale {
    private KpiContainer profit;
    private KpiContainer contractPenalty;

    public DirectSale() {
        this.profit = new KpiContainer();
        this.contractPenalty = new KpiContainer();
    }

    public KpiContainer getProfit() {
        return profit;
    }

    public void setProfit(KpiContainer profit) {
        this.profit = profit;
    }

    public KpiContainer getContractPenalty() {
        return contractPenalty;
    }

    public void setContractPenalty(KpiContainer contractPenalty) {
        this.contractPenalty = contractPenalty;
    }
}
