package de.hska.periodmanagement.business;

import de.hska.periodmanagement.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPeriodRepository extends JpaRepository<Period, Long> {

    public List<Period> findByGameAndGroupAndCounter(Long game, Long group, Long counter);
}
