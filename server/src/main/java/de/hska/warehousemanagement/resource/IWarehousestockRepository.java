package de.hska.warehousemanagement.resource;

import de.hska.warehousemanagement.domain.Warehousestock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWarehousestockRepository extends JpaRepository<Warehousestock, Long> {
}