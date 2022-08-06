package com.firsttoy.redbeardandbread.item.dto;

import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Builder
public class ItemPostDto {

    @NotBlank
    private String name;

    @NotBlank
    private String title;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private String descriptionImage;

    @Positive
    private int price;

    @Positive
    private int stock;

    @NotEmpty
    private Item.Category category;
}
