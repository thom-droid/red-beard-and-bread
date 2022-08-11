package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ItemMapperDecorator implements ItemMapper {

    @Autowired
    @Qualifier("delegate")
    private ItemMapper delegate;

    @Override
    public Item itemFrom(ItemPostDto itemPostDto) {

        return null;
    }
}
