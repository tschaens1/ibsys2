package de.hska.ordermanagement.business;

import de.hska.ordermanagement.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
