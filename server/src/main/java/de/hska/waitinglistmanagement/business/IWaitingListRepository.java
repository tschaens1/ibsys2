package de.hska.waitinglistmanagement.business;

import de.hska.waitinglistmanagement.domain.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWaitingListRepository extends JpaRepository<WaitingList, Long> {
}
