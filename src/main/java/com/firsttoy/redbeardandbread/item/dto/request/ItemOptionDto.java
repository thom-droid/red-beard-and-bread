package com.firsttoy.redbeardandbread.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOptionDto {

    private Long itemOptionId;

    @NotEmpty
    private String name;

    @NotEmpty
    private Integer price;
}
