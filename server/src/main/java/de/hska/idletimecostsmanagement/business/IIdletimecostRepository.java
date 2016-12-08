package de.hska.idletimecostsmanagement.business;

import de.hska.idletimecostsmanagement.domain.Idletimecost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIdletimecostRepository  extends JpaRepository<Idletimecost, Long> {
}
