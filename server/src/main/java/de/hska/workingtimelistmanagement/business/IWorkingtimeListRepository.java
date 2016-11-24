package de.hska.workingtimelistmanagement.business;

import de.hska.workingtimelistmanagement.domain.WorkingtimeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkingtimeListRepository extends JpaRepository<WorkingtimeList, Long> {
}
