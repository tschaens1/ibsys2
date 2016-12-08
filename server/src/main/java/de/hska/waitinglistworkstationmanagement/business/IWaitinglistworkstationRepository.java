package de.hska.waitinglistworkstationmanagement.business;

import de.hska.waitinglistworkstationmanagement.domain.Waitinglistworkstation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaitinglistworkstationRepository extends JpaRepository<Waitinglistworkstation, Long>{
}
