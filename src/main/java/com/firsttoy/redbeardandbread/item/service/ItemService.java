package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.helper.RepositoryHelper;
import com.firsttoy.redbeardandbread.item.entity.Item;

public interface ItemService extends RepositoryHelper<Item> {

    Item createItem(Item item);

    Item updateItem(Item item);

}
