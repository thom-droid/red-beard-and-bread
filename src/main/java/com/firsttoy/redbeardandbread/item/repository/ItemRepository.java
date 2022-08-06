package com.firsttoy.redbeardandbread.item.repository;

import com.firsttoy.redbeardandbread.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
