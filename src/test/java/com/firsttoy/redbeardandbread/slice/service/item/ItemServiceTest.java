package com.firsttoy.redbeardandbread.slice.service.item;

import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import com.firsttoy.redbeardandbread.item.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ItemServiceTest extends ServiceTestConfig {

    @Test
    public void throwsCustomRuntimeExceptionWithCode600WhenCreateItem() {

        Item item = Item.builder().code("CROI").build();
        given(itemRepository.findByCode(item.getCode())).willReturn(Optional.of(item));

        CustomRuntimeException result = assertThrows(CustomRuntimeException.class, () -> itemService.createItem(item));

        assertThat(result.getMessage()).isEqualTo(Exceptions.ITEM_ALREADY_EXISTS.getDescription());
        assertThat(result.getCode()).isEqualTo(Exceptions.ITEM_ALREADY_EXISTS.getCode());

    }

}
