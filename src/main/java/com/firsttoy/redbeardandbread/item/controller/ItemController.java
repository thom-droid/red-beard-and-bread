package com.firsttoy.redbeardandbread.item.controller;

import com.firsttoy.redbeardandbread.dto.SingleResponseDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RequestMapping("/item")
@RestController
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ResponseEntity<SingleResponseDto<ItemPostResponseDto>> postItem(@RequestBody @Valid ItemPostDto itemPostDto) {
        Item item = itemMapper.itemFrom(itemPostDto);
        itemMapper.updateItemFromItemPostDto(item, itemPostDto);
        Item createdItem = itemService.createItem(item);
        ItemPostResponseDto response = itemMapper.itemPostResponseDtoFrom(createdItem);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

}
