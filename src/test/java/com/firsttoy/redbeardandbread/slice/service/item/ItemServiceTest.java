package com.firsttoy.redbeardandbread.slice.service.item;

import com.firsttoy.redbeardandbread.config.MockTestConfig;
import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.item.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ItemServiceTest extends MockTestConfig {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void throwsCustomRuntimeExceptionWithCode600WhenCreateItem() {

        Item item = Item.builder().code("CROI").build();
        given(itemRepository.findByCode(item.getCode())).willReturn(Optional.of(item));

        CustomRuntimeException result = assertThrows(CustomRuntimeException.class, () -> itemService.createItem(item));

        assertThat(result.getMessage()).isEqualTo(Exceptions.ITEM_ALREADY_EXISTS.getDescription());
        assertThat(result.getCode()).isEqualTo(Exceptions.ITEM_ALREADY_EXISTS.getCode());

    }

}
