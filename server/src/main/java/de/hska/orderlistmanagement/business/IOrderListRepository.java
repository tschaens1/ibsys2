package de.hska.orderlistmanagement.business;

import de.hska.orderlistmanagement.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderListRepository extends JpaRepository<OrderList, Long> {
}
