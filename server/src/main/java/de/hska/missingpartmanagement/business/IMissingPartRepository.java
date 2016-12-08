package de.hska.missingpartmanagement.business;

import de.hska.missingpartmanagement.domain.MissingPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMissingPartRepository extends JpaRepository<MissingPart, Long> {
}
