package com.firsttoy.redbeardandbread.item.repository;

import com.firsttoy.redbeardandbread.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
