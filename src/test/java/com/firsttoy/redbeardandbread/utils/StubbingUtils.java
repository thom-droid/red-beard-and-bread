package com.firsttoy.redbeardandbread.utils;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;

import java.util.List;
import java.util.Optional;

public class StubbingUtils {

    public static Item getSimpleItemEntity() {
        return Item.builder().build();
    }

    /** Provides a simple dto that is populated with given parameters.
     * Some parameters that are likely to be used to assert in test cases can be given. If not precisely present, "croissant" is given.
     * Be careful that parameters should be the same if dto and entity stubs are used for assertion.
    * */
    public static ItemPostDto getSimpleItemPostDto(String name, String code, Item.Category category) {

        Optional<String> stubName = Optional.of(name);
        Optional<String> stubCode = Optional.of(code);
        Optional<Item.Category> stubCategory = Optional.of(category);

        ItemOptionDto itemOptionDto = ItemOptionDto.builder()
                .name("size up")
                .price(1000)
                .build();

        ItemOptionDto itemOptionDto1 = ItemOptionDto.builder()
                .name("blueberry jam")
                .price(1500)
                .build();

        List<ItemOptionDto> itemOptionDtoList = List.of(itemOptionDto, itemOptionDto1);

        return ItemPostDto.builder()
                .name(stubName.orElse("Plain Croissant"))
                .title("쫀딕쫀딕 버터향이 살아있는 ")
                .thumbnail("croissant.jpg")
                .descriptionImage("croissant-desc.jpg")
                .price(3000)
                .stock(100)
                .point(10)
                .code(stubCode.orElse("PCRI"))
                .category(stubCategory.orElse(Item.Category.BREAD))
                .itemOptions(itemOptionDtoList)
                .build();

    }

}
