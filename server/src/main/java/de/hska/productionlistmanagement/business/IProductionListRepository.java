package de.hska.productionlistmanagement.business;

import de.hska.productionlistmanagement.domain.ProductionList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductionListRepository extends JpaRepository<ProductionList, Long> {
}

