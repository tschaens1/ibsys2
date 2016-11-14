package de.hska.purchasemanagement.business;

import de.hska.purchasemanagement.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
}
