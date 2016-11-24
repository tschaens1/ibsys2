package de.hska.itemmanagement.business;

import de.hska.itemmanagement.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}