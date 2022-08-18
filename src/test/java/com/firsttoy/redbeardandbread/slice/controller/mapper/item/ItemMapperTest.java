package com.firsttoy.redbeardandbread.slice.controller.mapper.item;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.utils.CustomBeanUtils;
import com.firsttoy.redbeardandbread.utils.StubbingUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Stubbing;
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

    }
}
