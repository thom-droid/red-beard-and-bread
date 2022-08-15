package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.utils.CustomBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CustomBeanUtils<Item> beanUtils;

    @Override
    public Item createItem(Item item) {
        verifyExists(item.getCode());
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item source) {
        Item target = findById(source.getItemId());
        Item updatedItem = beanUtils.copyProperties(target, source);

        return itemRepository.save(updatedItem);
    }

    public void verifyExists(String itemCode) {

        itemRepository.findByCode(itemCode).ifPresent(
            item -> {
                throw new CustomRuntimeException(Exceptions.ITEM_ALREADY_EXISTS);}
        );
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> {throw new CustomRuntimeException(Exceptions.ITEM_NOT_FOUND);}
        );
    }
}
