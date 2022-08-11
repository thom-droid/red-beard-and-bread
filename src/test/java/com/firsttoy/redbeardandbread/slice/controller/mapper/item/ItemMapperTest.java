package com.firsttoy.redbeardandbread.slice.controller.mapper.item;

import com.firsttoy.redbeardandbread.config.MockTestConfig;
import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ItemMapperTest extends MockTestConfig {

    private final ItemMapper itemMapper = new ItemMapperImpl();

    @DisplayName("ItemPostDto -> Item")
    @Test
    public void itemPostDtoToItemEntity() {

        //given
        ItemOptionPostDto itemOptionPostDto = ItemOptionPostDto.builder()
                .name("size up")
                .price(1000)
                .build();

        ItemOptionPostDto itemOptionPostDto1 = ItemOptionPostDto.builder()
                .name("blueberry jam")
                .price(1500)
                .build();

        List<ItemOptionPostDto> itemOptionPostDtoList = List.of(itemOptionPostDto, itemOptionPostDto1);

        ItemPostDto itemPostDto = ItemPostDto.builder()
                .name("Plain Croissant")
                .title("쫀딕쫀딕 버터향이 살아있는 ")
                .thumbnail("croissant.jpg")
                .descriptionImage("croissant-desc.jpg")
                .price(3000)
                .stock(100)
                .point(10)
                .code("PCRI")
                .category(Item.Category.BREAD)
                .itemOptions(itemOptionPostDtoList)
                .build();

        Item targetItem = Item.builder()
                .name(itemPostDto.getName())
                .title(itemPostDto.getTitle())
                .thumbnail(itemPostDto.getThumbnail())
                .descriptionImage(itemPostDto.getDescriptionImage())
                .price(itemPostDto.getPrice())
                .stock(itemPostDto.getStock())
                .point(itemPostDto.getPoint())
                .code(itemPostDto.getCode())
                .category(itemPostDto.getCategory())
                .build();

        ItemOption optionEntity = ItemOption.builder()
                .name(itemOptionPostDto.getName())
                .price(itemOptionPostDto.getPrice())
                .build();

        ItemOption optionEntity2 = ItemOption.builder()
                .name(itemOptionPostDto1.getName())
                .price(itemOptionPostDto1.getPrice())
                .build();

        targetItem.addItemOptions(optionEntity);
        targetItem.addItemOptions(optionEntity2);

        //when
        Item destItem = itemMapper.itemFrom(itemPostDto);

        //then

        assertThat(destItem.getName()).isEqualTo(targetItem.getName());
        assertThat(destItem.getItemOptions().get(0).getName()).isEqualTo(targetItem.getItemOptions().get(0).getName());
    }
}
