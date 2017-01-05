package de.hska.filemanagement.business;

import de.hska.filemanagement.domain.XmlFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IXmlFileRepository extends JpaRepository<XmlFile, Long> {
}
