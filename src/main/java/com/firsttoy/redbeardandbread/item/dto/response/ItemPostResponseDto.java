package com.firsttoy.redbeardandbread.item.dto.response;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPostResponseDto {

    private long itemId;
    private String name;
    private String title;
    private int price;
    private int stock;
    private int point;
    private String thumbnail;
    private String descriptionImage;
    private String code;
    private Item.Category category;
    private Item.SaleStatus status;

    @Builder.Default
    private List<ItemOptionDto> itemOptions = new ArrayList<>();

}
