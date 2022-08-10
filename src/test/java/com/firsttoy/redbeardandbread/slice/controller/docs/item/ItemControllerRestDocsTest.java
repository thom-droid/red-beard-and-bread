package com.firsttoy.redbeardandbread.slice.controller.docs.item;

import com.firsttoy.redbeardandbread.item.controller.ItemController;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.item.service.ItemServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ItemController.class)
public class ItemControllerRestDocsTest {

    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private ItemServiceImpl itemService;

    @MockBean
    private ItemRepository itemRepository;

    @DisplayName("postItem")
    @Test
    public void givenItemAndItemOption_whenPostRequested_thenPersistedItemReturned() {
        //given
        
    }

}
