package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.config.DefaultMapperConfig;
import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DefaultMapperConfig.class)
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    //todo: some common methods may be abstracted?
    @Mapping(target = "itemOptions", ignore = true)
    Item itemFrom(ItemPostDto itemPostDto);

    Item itemFrom(ItemPatchDto itemPatchDto);

    void updateItemFromItemPostDto(@MappingTarget Item item, ItemPostDto itemPostDto);

    //todo: if some values are null or not present,
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "itemOptions", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromSource(@MappingTarget Item item, Item source);

    @Mapping(target = "item", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemOptionFromSource(@MappingTarget ItemOption itemOption, ItemOption source);

    ItemOption itemOptionFrom(ItemOptionDto itemOptionDto);

    ItemResponseDto itemResponseDtoFrom(Item item);

    ItemOptionDto itemOptionPostDtoFrom(ItemOption itemOption);


}
