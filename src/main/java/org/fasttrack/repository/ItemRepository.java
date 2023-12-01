package org.fasttrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.fasttrack.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
