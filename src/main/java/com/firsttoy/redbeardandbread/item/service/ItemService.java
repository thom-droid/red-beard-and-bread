package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.item.entity.Item;

public interface ItemService {

    Item createItem(Item item);

    Item updateItem(Item item);

    Item findById(Long id);

}
