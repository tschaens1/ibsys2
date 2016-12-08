package de.hska.waitinglistmanagement.business;

import de.hska.waitinglistmanagement.domain.Waitinglist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaitinglistRepository extends JpaRepository<Waitinglist, Long> {
}
