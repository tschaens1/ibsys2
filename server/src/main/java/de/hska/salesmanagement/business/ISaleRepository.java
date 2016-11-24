package de.hska.salesmanagement.business;

import de.hska.salesmanagement.domain.DirectSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<DirectSale, Long> {
}
