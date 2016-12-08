package de.hska.batchmanagement.business;

import de.hska.batchmanagement.domain.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBatchRepository extends JpaRepository<Batch, Long>{
}
