package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item createItem(Item item) {
        verifyExistence(item.getCode());
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item source) {
        Item entity = findById(source.getItemId());

        itemMapper.updateItemFromSource(entity, source);

        for (int i = 0; i < entity.getItemOptions().size(); i++) {
            itemMapper.updateItemOptionFromSource(entity.getItemOptions().get(i), source.getItemOptions().get(i));
        }

        return itemRepository.save(entity);
    }

    public void verifyExistence(String itemCode) {

        itemRepository.findByCode(itemCode).ifPresent(
            item -> {
                throw new CustomRuntimeException(Exceptions.ITEM_ALREADY_EXISTS);}
        );
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> {throw new CustomRuntimeException(Exceptions.ITEM_NOT_FOUND);}
        );
    }
}
