package com.firsttoy.redbeardandbread.item.controller;

import com.firsttoy.redbeardandbread.dto.SingleResponseDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{itemId}")
    public ResponseEntity<SingleResponseDto<Item>> patchItem(
            @RequestBody ItemPatchDto itemPatchDto,
            @PathVariable Long itemId) {

        itemPatchDto.setItemId(itemId);
        Item foundItem = itemService.findById(itemId);

//        Item updatedItem = itemService.updateItem(itemMapper.itemFrom(itemPatchDto));
        return new ResponseEntity<>(new SingleResponseDto<>(foundItem), HttpStatus.OK);
    }

}
