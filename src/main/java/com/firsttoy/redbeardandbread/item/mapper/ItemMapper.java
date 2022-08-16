package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    @Mapping(target = "itemOptions", ignore = true)
    Item itemFrom(ItemPostDto itemPostDto);

    void updateItemFromItemPostDto(@MappingTarget Item item, ItemPostDto itemPostDto);

    ItemOption itemOptionFrom(ItemOptionDto itemOptionDto);

    ItemPostResponseDto itemPostResponseDtoFrom(Item item);

    ItemOptionDto itemOptionPostDtoFrom(ItemOption itemOption);

    Item updateItemFromItemPatchDto(@MappingTarget Item item, ItemPatchDto itemPatchDto);

}
