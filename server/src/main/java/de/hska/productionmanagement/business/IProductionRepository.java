package de.hska.productionmanagement.business;

import de.hska.productionmanagement.domain.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductionRepository extends JpaRepository<Production, Long> {
}
