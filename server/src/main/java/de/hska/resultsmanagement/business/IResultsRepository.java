package de.hska.resultsmanagement.business;

import de.hska.resultsmanagement.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultsRepository extends JpaRepository<Results, Long> {
}
