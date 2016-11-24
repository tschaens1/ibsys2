package de.hska.selldirectmanagement.business;

import de.hska.selldirectmanagement.domain.Selldirect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISelldirectRepository extends JpaRepository<Selldirect, Long> {
}
