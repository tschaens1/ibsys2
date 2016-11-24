package de.hska.qualitycontrolmanagement.business;

import de.hska.qualitycontrolmanagement.domain.QualityControl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQualityControlRepository extends JpaRepository<QualityControl, Long> {
}
