package de.hska.periodmanagement.business;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hska.periodmanagement.domain.Period;

public interface IPeriodRepository extends JpaRepository<Period, Long> {

	public Period findByCounter(Long counter);
}
