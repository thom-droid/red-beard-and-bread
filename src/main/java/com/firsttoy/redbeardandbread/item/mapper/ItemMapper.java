package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.*;

@MapperConfig(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Mapper(componentModel = "spring")
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    //todo: some common methods may be abstracted?
    @Mapping(target = "itemOptions", ignore = true)
    Item itemFrom(ItemPostDto itemPostDto);

    @Mapping(target = "itemOptions", ignore = true)
    Item itemFrom(ItemPatchDto itemPatchDto);

    void updateItemFromItemPostDto(@MappingTarget Item item, ItemPostDto itemPostDto);

    //todo: if some values are null or not present,
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "itemOptions", ignore = true)
    void updateItemFromSourceItem(@MappingTarget Item item, Item sourceItem);

    ItemOption itemOptionFrom(ItemOptionDto itemOptionDto);

    ItemPostResponseDto itemPostResponseDtoFrom(Item item);

    ItemOptionDto itemOptionPostDtoFrom(ItemOption itemOption);


}
