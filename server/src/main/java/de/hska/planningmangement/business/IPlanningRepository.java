package de.hska.planningmangement.business;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hska.planningmangement.domain.Planning;

public interface IPlanningRepository extends JpaRepository<Planning, Long> {

}
