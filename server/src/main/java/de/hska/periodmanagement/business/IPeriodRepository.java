package de.hska.periodmanagement.business;

import de.hska.periodmanagement.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeriodRepository extends JpaRepository<Period, Long> {
}
