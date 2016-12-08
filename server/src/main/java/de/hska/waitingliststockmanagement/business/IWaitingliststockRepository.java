package de.hska.waitingliststockmanagement.business;

import de.hska.waitingliststockmanagement.domain.WaitinglistStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaitingliststockRepository extends JpaRepository<WaitinglistStock, Long> {
}
