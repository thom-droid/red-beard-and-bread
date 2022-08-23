package com.firsttoy.redbeardandbread.item.repository;

import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {

    Optional<ItemOption> findById(Long id);
}
