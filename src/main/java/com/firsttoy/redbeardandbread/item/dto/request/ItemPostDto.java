package com.firsttoy.redbeardandbread.item.dto.request;

import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
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

    @Range(min = 0L, max = 1000)
    private int point;

    //Todo : custom validator
    @NotEmpty(message = "4글자 영어 대문자여야 합니다")
    @Length(min = 4, max = 4)
    private String code;

    @NotNull
    private Item.Category category;

    @Builder.Default
    private List<ItemOptionDto> itemOptions = new ArrayList<>();
}
