package de.hska.filemanagement.business;

import org.springframework.data.jpa.repository.JpaRepository;

import de.hska.filemanagement.domain.JsonFile;

public interface IJsonFileRepository extends JpaRepository<JsonFile, Long> {
}
