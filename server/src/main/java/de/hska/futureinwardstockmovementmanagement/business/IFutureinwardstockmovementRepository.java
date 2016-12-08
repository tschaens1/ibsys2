package de.hska.futureinwardstockmovementmanagement.business;

import de.hska.futureinwardstockmovementmanagement.domain.Futureinwardstockmovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFutureinwardstockmovementRepository extends JpaRepository<Futureinwardstockmovement, Long> {
}
