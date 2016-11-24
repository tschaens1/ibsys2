package de.hska.waitingqueuemanagement.business;

import de.hska.waitingqueuemanagement.domain.WaitingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaitingQueueRepository extends JpaRepository<WaitingQueue, Long> {
}
