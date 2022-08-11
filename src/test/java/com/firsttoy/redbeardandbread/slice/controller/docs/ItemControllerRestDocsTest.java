package com.firsttoy.redbeardandbread.slice.controller.docs;

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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.*;

@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ItemController.class)
public class ItemControllerRestDocsTest {

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
    public void givenItemAndItemOption_whenPostRequested_thenPersistedItemReturned() {

        //TODO: 1. validation 은 테스트 단에서도 동작하는가?

        //given
        ItemOptionPostDto itemOptionPostDto = ItemOptionPostDto.builder()
                .name("size up")
                .price(1000)
                .build();

        ItemOptionPostDto itemOptionPostDto1 = ItemOptionPostDto.builder()
                .name("blueberry jam")
                .price(1500)
                .build();

        List<ItemOptionPostDto> itemOptionPostDtoList = List.of(itemOptionPostDto, itemOptionPostDto1);

        ItemPostDto itemPostDto = ItemPostDto.builder()
                .name("Plain Croissant")
                .title("쫀딕쫀딕 버터향이 살아있는 ")
                .thumbnail("croissant.jpg")
                .descriptionImage("croissant-desc.jpg")
                .price(3000)
                .stock(100)
                .point(10)
                .code("PCRI")
                .category(Item.Category.BREAD)
                .itemOptions(itemOptionPostDtoList)
                .build();

        Item targetItem = Item.builder()
                .name(itemPostDto.getName())
                .title(itemPostDto.getTitle())
                .thumbnail(itemPostDto.getThumbnail())
                .descriptionImage(itemPostDto.getDescriptionImage())
                .price(itemPostDto.getPrice())
                .stock(itemPostDto.getStock())
                .point(itemPostDto.getPoint())
                .code(itemPostDto.getCode())
                .category(itemPostDto.getCategory())
                .build();

        given(itemMapper.itemFrom(Mockito.mock(ItemPostDto.class))).willReturn(new Item());
        given(itemService.createItem(Mockito.mock(Item.class))).willReturn(new Item());
        given(itemMapper)

    }

}
