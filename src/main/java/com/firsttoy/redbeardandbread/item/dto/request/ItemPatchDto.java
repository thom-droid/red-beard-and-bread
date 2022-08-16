package com.firsttoy.redbeardandbread.item.dto.request;

import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPatchDto {

    @Setter
    private Long itemId;
    private int stock;
    private int point;
    private String descriptionImage;

    @Builder.Default
    private List<ItemOptionDto> itemOptions = new ArrayList<>();

}
