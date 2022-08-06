package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item itemFrom(ItemPostDto itemPostDto);

    ItemOption itemOptionFrom(ItemOptionPostDto itemOptionPostDto);

    ItemPostResponseDto itemPostResponseDtoFrom(Item item);

    ItemOptionPostDto itemOptionPostDtoFrom(ItemOption itemOption);

}
