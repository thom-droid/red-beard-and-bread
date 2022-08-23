package com.firsttoy.redbeardandbread.item.controller;

import com.firsttoy.redbeardandbread.dto.SingleResponseDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemResponseDto;
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
@RequestMapping("/api/v1/items")
@RestController
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @PostMapping
    public ResponseEntity<SingleResponseDto<ItemResponseDto>> postItem(@RequestBody @Valid ItemPostDto itemPostDto) {
        Item item = itemMapper.itemFrom(itemPostDto);
        itemMapper.updateItemFromItemPostDto(item, itemPostDto);
        Item createdItem = itemService.createItem(item);
        ItemResponseDto response = itemMapper.itemResponseDtoFrom(createdItem);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<SingleResponseDto<ItemResponseDto>> patchItem(
            @RequestBody ItemPatchDto itemPatchDto,
            @PathVariable Long itemId) {

        //todo: would have liked to do this logic in service layer but if then mapper has to be injected to service layer which i wanted to avoid..

        itemPatchDto.setItemId(itemId);
        Item source = itemMapper.itemFrom(itemPatchDto);

        Item updatedEntity = itemService.updateItem(source);

        return new ResponseEntity<>(new SingleResponseDto<>(itemMapper.itemResponseDtoFrom(updatedEntity)), HttpStatus.OK);
    }

}
