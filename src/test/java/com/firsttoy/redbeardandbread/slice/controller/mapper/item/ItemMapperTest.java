package com.firsttoy.redbeardandbread.slice.controller.mapper.item;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.utils.CustomBeanUtils;
import com.firsttoy.redbeardandbread.utils.StubbingUtils;
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
        ItemPostDto itemPostDto = StubbingUtils.getSimpleItemPostDto("Plain Croissant", "PCRI", Item.Category.BREAD);

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
                .name(itemPostDto.getItemOptions().get(0).getName())
                .price(itemPostDto.getItemOptions().get(0).getPrice())
                .build();

        ItemOption optionEntity2 = ItemOption.builder()
                .name(itemPostDto.getItemOptions().get(1).getName())
                .price(itemPostDto.getItemOptions().get(1).getPrice())
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
        // given

        // dto
        // descriptionImage is set as null to test if null value strategy is applied in mapper
        ItemOptionDto itemOptionDto =
                ItemOptionDto.builder()
                        .itemOptionId(1L)
                        .price(1500)
                        .build();

        ItemOptionDto itemOptionDto1 =
                ItemOptionDto.builder()
                        .itemOptionId(2L)
                        .name("additional butter")
                        .build();

        ItemPatchDto itemPatchDto =
                ItemPatchDto.builder()
                        .itemId(1L)
                        .point(1000)
                        .stock(100)
                        .itemOptions(List.of(itemOptionDto, itemOptionDto1))
                        .build();

        // entity
        ItemOption itemOption =
                ItemOption.builder()
                        .itemOptionId(1L)
                        .name("extra size : before update")
                        .price(1200)
                        .build();

        ItemOption itemOption1 =
                ItemOption.builder()
                        .itemOptionId(2L)
                        .name("extra yogurt : before update")
                        .price(900)
                        .build();

        Item entity = Item.builder()
                .name("Croissant")
                .itemId(1L)
                .title("title")
                .thumbnail("thumbnail")
                .descriptionImage("before updated")
                .price(500)
                .stock(50)
                .point(500)
                .code("PCRI")
                .category(Item.Category.BREAD)
                .itemOptions(List.of(itemOption,itemOption1))
                .build();

        // when
        Item source = itemMapper.itemFrom(itemPatchDto);
        itemMapper.updateItemFromSource(entity, source);
        for (int i = 0; i < entity.getItemOptions().size(); i++) {
            itemMapper.updateItemOptionFromSource(entity.getItemOptions().get(i), source.getItemOptions().get(i));
        }

        // then
//        assertThat(entity.getDescriptionImage()).isNotEqualTo(source.getDescriptionImage());
        assertThat(entity.getDescriptionImage()).isEqualTo("before updated");
        assertThat(entity.getStock()).isEqualTo(itemPatchDto.getStock());
        assertThat(entity.getPoint()).isEqualTo(itemPatchDto.getPoint());

        assertThat(entity.getItemOptions().get(0).getItemOptionId()).isEqualTo(1L);
        assertThat(entity.getItemOptions().get(0).getName()).isEqualTo("extra size : before update");
        assertThat(entity.getItemOptions().get(0).getPrice()).isEqualTo(itemPatchDto.getItemOptions().get(0).getPrice());
        assertThat(entity.getItemOptions().get(1).getName()).isEqualTo(itemPatchDto.getItemOptions().get(1).getName());
        assertThat(entity.getItemOptions().get(1).getPrice()).isEqualTo(900);

    }
}
