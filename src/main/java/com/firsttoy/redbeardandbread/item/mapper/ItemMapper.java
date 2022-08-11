package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    //Todo: config setting to use Item.addItemOption() method.
    @Mapping(target = "itemOptions", ignore = true)
    Item itemFrom(ItemPostDto itemPostDto);

    ItemOption itemOptionFrom(ItemOptionPostDto itemOptionPostDto);

    ItemPostResponseDto itemPostResponseDtoFrom(Item item);

    ItemOptionPostDto itemOptionPostDtoFrom(ItemOption itemOption);


    @AfterMapping
    default void addItemOptions(@MappingTarget Item item, ItemPostDto itemPostDto) {
        itemPostDto.getItemOptions().forEach(itemOptionDto -> {
            ItemOption itemOption = itemOptionFrom(itemOptionDto);
            item.addItemOptions(itemOption);
        });
    }

//    void updateItem(@MappingTarget Item item, ItemPostDto itemPostDto);
}
