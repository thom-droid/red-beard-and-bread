package com.firsttoy.redbeardandbread.slice.controller.mapper.item;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.utils.CustomBeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CustomBeanUtils customBeanUtils;

    @DisplayName("ItemPostDto -> Item 😊🤣❤️😍")
    @Test
    public void itemPostDtoToItemEntity() {

        //given
        ItemOptionDto itemOptionDto = ItemOptionDto.builder()
                .name("size up")
                .price(1000)
                .build();

        ItemOptionDto itemOptionDto1 = ItemOptionDto.builder()
                .name("blueberry jam")
                .price(1500)
                .build();

        List<ItemOptionDto> itemOptionDtoList = List.of(itemOptionDto, itemOptionDto1);

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
                .itemOptions(itemOptionDtoList)
                .build();

        Item source = Item.builder()
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
                .name(itemOptionDto.getName())
                .price(itemOptionDto.getPrice())
                .build();

        ItemOption optionEntity2 = ItemOption.builder()
                .name(itemOptionDto1.getName())
                .price(itemOptionDto1.getPrice())
                .build();

        source.addItemOptions(optionEntity);
        source.addItemOptions(optionEntity2);

        //when
        Item target = itemMapper.itemFrom(itemPostDto);
        itemMapper.updateItemFromItemPostDto(target, itemPostDto);

        //then
        assertThat(target.getName()).isEqualTo(source.getName());
        assertThat(target.getItemOptions().get(0).getName()).isEqualTo(source.getItemOptions().get(0).getName());
        assertThat(target.getItemOptions().get(0).getItem().getName()).isEqualTo(target.getName());
    }

    @DisplayName("ItemPatchDto -> UpdatedItem")
    @Test
    public void givenItemPatchDto_whenMapperInvoked_thenUpdatedItemReturn() {

    }
}
