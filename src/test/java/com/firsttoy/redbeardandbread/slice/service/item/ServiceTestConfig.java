package com.firsttoy.redbeardandbread.slice.service.item;

import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.item.service.ItemService;
import com.firsttoy.redbeardandbread.item.service.ItemServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class ServiceTestConfig {

    @InjectMocks
    protected ItemServiceImpl itemService;

    @Mock
    protected ItemRepository itemRepository;


}
