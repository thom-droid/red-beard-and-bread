package com.firsttoy.redbeardandbread.utils;

import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;

import java.util.Optional;

public class StubbingUtils {

    public static Item getSimpleItemEntity() {
        return Item.builder().build();
    }

    public static ItemPostDto getSimpleItemPostDto(String name) {
        return ItemPostDto.builder().build();
    }
}
