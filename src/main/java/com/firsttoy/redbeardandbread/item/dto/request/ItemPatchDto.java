package com.firsttoy.redbeardandbread.item.dto.request;

import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.*;

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

}
