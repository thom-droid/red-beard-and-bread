package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //Todo: config setting to use Item.addItemOption() method.
//    @Mapping()
    Item itemFrom(ItemPostDto itemPostDto);

    ItemOption itemOptionFrom(ItemOptionPostDto itemOptionPostDto);

    ItemPostResponseDto itemPostResponseDtoFrom(Item item);

    ItemOptionPostDto itemOptionPostDtoFrom(ItemOption itemOption);

}
