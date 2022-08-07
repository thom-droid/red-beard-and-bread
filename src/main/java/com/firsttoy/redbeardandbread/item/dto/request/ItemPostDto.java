package com.firsttoy.redbeardandbread.item.dto.request;

import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @Positive
    private int point;

    //Todo : custom validator
    @NotEmpty(message = "4글자 영어 대문자여야 합니다")
    @Length(min = 4, max = 4)
    private String code;

    @NotEmpty
    private Item.Category category;

    private List<ItemOptionPostDto> itemOptions;
}
