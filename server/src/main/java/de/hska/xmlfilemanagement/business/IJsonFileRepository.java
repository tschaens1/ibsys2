package de.hska.xmlfilemanagement.business;

import de.hska.xmlfilemanagement.domain.JsonFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJsonFileRepository extends JpaRepository<JsonFile, Long> {
}
