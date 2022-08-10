package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        verifyExists(item.getCode());
        return itemRepository.save(item);
    }

    public void verifyExists(String itemCode) {

        itemRepository.findByCode(itemCode).ifPresent(
            item -> {
                throw new RuntimeException("item already exists");}
        );
    }
}
