package de.hska.workplacemanagement.business;

import de.hska.workplacemanagement.domain.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkplaceRepository extends JpaRepository<Workplace, Long> {
}
