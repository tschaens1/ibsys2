package de.hska.ordersinworkmanagement.business;

import de.hska.ordersinworkmanagement.domain.OrdersInWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersInWorkRepository extends JpaRepository<OrdersInWork, Long> {
}
