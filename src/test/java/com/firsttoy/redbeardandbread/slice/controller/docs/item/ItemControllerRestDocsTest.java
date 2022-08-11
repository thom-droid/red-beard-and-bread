package com.firsttoy.redbeardandbread.slice.controller.docs.item;

import com.firsttoy.redbeardandbread.item.controller.ItemController;
import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionPostDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemPostResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.item.service.ItemService;
import com.firsttoy.redbeardandbread.item.service.ItemServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ItemController.class)
public class ItemControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private ItemMapper itemMapper;

    @DisplayName("postItem")
    @Test
    public void givenItemAndItemOption_whenPostRequested_thenPersistedItemReturned() throws Exception {
        //given

        ItemOptionPostDto optionPostDto = ItemOptionPostDto.builder()
                .name("size up")
                .price(500)
                .build();

        ItemOptionPostDto optionPostDto1 = ItemOptionPostDto.builder()
                .name("stuffed with jam")
                .price(1500)
                .build();

        List<ItemOptionPostDto> optionPostDtos = List.of(optionPostDto, optionPostDto1);

        ItemPostResponseDto responseDto = ItemPostResponseDto.builder()
                                .itemId(1L)
                                .name("Plain Croissant")
                                .title("쫀딕쫀딕 버터향이 구수한 갓 구운 끄로아상")
                                .price(5000)
                                .stock(100)
                                .point(50)
                                .category(Item.Category.BREAD)
                                .status(Item.SaleStatus.ON_SALE)
                                .itemOptions(optionPostDtos)
                                .build();


        given(itemMapper.itemFrom(Mockito.any(ItemPostDto.class))).willReturn(Mockito.mock(Item.class));
        given(itemService.createItem(Mockito.any(Item.class))).willReturn(Mockito.mock(Item.class));
        given(itemMapper.itemPostResponseDtoFrom(Mockito.any(Item.class))).willReturn(responseDto);

        ItemPostDto requestDto =
                ItemPostDto.builder()
                        .name("some")
                        .title("hehehe")
                        .thumbnail("test.jpg")
                        .descriptionImage("test.jpg")
                        .price(5000)
                        .stock(1000)
                        .code("PCRI")
                        .category(Item.Category.BREAD)
                        .build();

        String request = gson.toJson(requestDto);

        ResultActions resultActions =
                mockMvc.perform(
                        post("/item")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                );

        verify(itemMapper).itemPostResponseDtoFrom(Mockito.any(Item.class));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.itemId").value(responseDto.getItemId()))
                .andExpect(jsonPath("$.response.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.response.status").value(responseDto.getStatus().name()))
                .andDo(print());
    }

}
