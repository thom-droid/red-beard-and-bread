package com.firsttoy.redbeardandbread.item.dto.response;

import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPostResponseDto {

    private long itemId;
    private String name;
    private String title;
    private int price;
    private int stock;
    private int point;
    private Item.Category category;
    private Item.SaleStatus status;
    private List<ItemOptionPostDto> itemOptions;
}
