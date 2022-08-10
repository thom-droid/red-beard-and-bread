package com.firsttoy.redbeardandbread.slice.service.item;

import com.firsttoy.redbeardandbread.item.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest extends ServiceTestConfig {

    @Test
    public void throwsRuntimeExceptionWhenCreateItem() {

        Item item = Item.builder().code("CROI").build();
        given(itemRepository.findByCode(item.getCode())).willReturn(Optional.of(item));

        assertThrows(RuntimeException.class, () -> itemService.createItem(item));

    }

}
