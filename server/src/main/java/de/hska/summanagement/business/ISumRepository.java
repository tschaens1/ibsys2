package de.hska.summanagement.business;

import de.hska.summanagement.domain.Sum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISumRepository extends JpaRepository<Sum, Long> {
}
