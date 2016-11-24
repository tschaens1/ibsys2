package de.hska.sellwishmanagement.business;

import de.hska.sellwishmanagement.domain.Sellwish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellwishRepository extends JpaRepository<Sellwish, Long> {
}