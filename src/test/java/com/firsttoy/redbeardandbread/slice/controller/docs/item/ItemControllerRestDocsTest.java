package com.firsttoy.redbeardandbread.slice.controller.docs.item;

import com.firsttoy.redbeardandbread.item.controller.ItemController;
import com.firsttoy.redbeardandbread.item.dto.request.ItemOptionDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.dto.response.ItemResponseDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.mapper.ItemMapper;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import com.firsttoy.redbeardandbread.item.service.ItemService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

//@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(ItemController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
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

    @DisplayName("RestDocs -> postItem")
    @Test
    public void givenItemAndItemOption_whenPostRequested_thenPersistedItemReturned() throws Exception {
        //given

        ItemOptionDto optionPostDto = ItemOptionDto.builder()
                .itemOptionId(1L)
                .name("size up")
                .price(500)
                .build();

        ItemOptionDto optionPostDto1 = ItemOptionDto.builder()
                .itemOptionId(2L)
                .name("stuffed with jam")
                .price(1500)
                .build();

        List<ItemOptionDto> optionPostDtos = List.of(optionPostDto, optionPostDto1);

        ItemResponseDto responseDto = ItemResponseDto.builder()
                .itemId(1L)
                .name("Plain Croissant")
                .title("쫀딕쫀딕 버터향이 구수한 갓 구운 끄로아상")
                .thumbnail("thumbnail.png")
                .descriptionImage("description.png")
                .code("PCRI")
                .price(5000)
                .stock(100)
                .point(50)
                .category(Item.Category.BREAD)
                .status(Item.SaleStatus.ON_SALE)
                .itemOptions(optionPostDtos)
                .build();

        given(itemMapper.itemFrom(Mockito.any(ItemPostDto.class))).willReturn(Mockito.mock(Item.class));
        given(itemService.createItem(Mockito.any(Item.class))).willReturn(Mockito.mock(Item.class));
        given(itemMapper.itemResponseDtoFrom(Mockito.any(Item.class))).willReturn(responseDto);

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
                        RestDocumentationRequestBuilders.post("/api/v1/items")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                );

        verify(itemMapper).itemResponseDtoFrom(Mockito.any(Item.class));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.itemId").value(responseDto.getItemId()))
                .andExpect(jsonPath("$.response.name").value(responseDto.getName()))
                .andExpect(jsonPath("$.response.status").value(responseDto.getStatus().name()))
                .andDo(document(
                        "post-item",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        //Todo : documentation 코드를 추상화할 수 없을까?
                        //Todo : validation 정보 추가
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("상품명"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("상세정보글 제목"),
                                        fieldWithPath("thumbnail").type(JsonFieldType.STRING).description("썸네일 사진 이름"),
                                        fieldWithPath("descriptionImage").type(JsonFieldType.STRING).description("상세정보 이미지"),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격"),
                                        fieldWithPath("stock").type(JsonFieldType.NUMBER).description("재고"),
                                        fieldWithPath("point").type(JsonFieldType.NUMBER).description("구매시 적립 포인트"),
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("상품식별코드"),
                                        fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                                        fieldWithPath("itemOptions[]").type(JsonFieldType.ARRAY).description("옵션정보"),
                                        fieldWithPath("itemOptions[].name").type(JsonFieldType.STRING).description("옵션이름").optional(),
                                        fieldWithPath("itemOptions[].price").type(JsonFieldType.NUMBER).description("옵션가격").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("response").type(JsonFieldType.OBJECT).description("응답"),
                                        fieldWithPath("response.itemId").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("response.name").type(JsonFieldType.STRING).description("상품명"),
                                        fieldWithPath("response.title").type(JsonFieldType.STRING).description("상세정보글 제목"),
                                        fieldWithPath("response.thumbnail").type(JsonFieldType.STRING).description("썸네일 사진 이름"),
                                        fieldWithPath("response.descriptionImage").type(JsonFieldType.STRING).description("상세정보 이미지"),
                                        fieldWithPath("response.price").type(JsonFieldType.NUMBER).description("가격"),
                                        fieldWithPath("response.stock").type(JsonFieldType.NUMBER).description("재고"),
                                        fieldWithPath("response.point").type(JsonFieldType.NUMBER).description("구매시 적립 포인트"),
                                        fieldWithPath("response.code").type(JsonFieldType.STRING).description("상품식별코드"),
                                        fieldWithPath("response.category").type(JsonFieldType.STRING).description("카테고리"),
                                        fieldWithPath("response.status").type(JsonFieldType.STRING).description("판매상태"),
                                        fieldWithPath("response.itemOptions[]").type(JsonFieldType.ARRAY).description("옵션정보"),
                                        fieldWithPath("response.itemOptions[].itemOptionId").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("response.itemOptions[].name").type(JsonFieldType.STRING).description("옵션이름").optional(),
                                        fieldWithPath("response.itemOptions[].price").type(JsonFieldType.NUMBER).description("옵션가격").optional()
                                )

                        )
                ));
    }

    @DisplayName("RestDocs -> Update item")
    @Test
    public void givenItemPatchDto_whenPatchRequested_thenUpdatedItemReturn() throws Exception {
        //given

        ItemOptionDto requestOptionDto =
                ItemOptionDto.builder()
                        .itemOptionId(1L)
                        .price(1500)
                        .build();

        ItemOptionDto requestOptionDto1 =
                ItemOptionDto.builder()
                        .itemOptionId(2L)
                        .name("update request 2")
                        .build();

        ItemPatchDto requestDto =
                ItemPatchDto.builder()
                        .itemId(1L)
                        .stock(20)
                        .point(10)
                        .descriptionImage("updated image")
                        .itemOptions(List.of(requestOptionDto, requestOptionDto1))
                        .build();


        ItemOptionDto optionDto = ItemOptionDto.builder()
                .itemOptionId(1L)
                .name("size up")
                .price(requestOptionDto.getPrice())
                .build();

        ItemOptionDto optionDto1 = ItemOptionDto.builder()
                .itemOptionId(2L)
                .name(requestOptionDto1.getName())
                .price(1500)
                .build();

        List<ItemOptionDto> optionDtos = List.of(optionDto, optionDto1);

        ItemResponseDto responseDto = ItemResponseDto.builder()
                .itemId(1L)
                .name("Plain Croissant")
                .title("쫀딕쫀딕 버터향이 구수한 갓 구운 끄로아상")
                .thumbnail("thumbnail.png")
                .descriptionImage(requestDto.getDescriptionImage())
                .code("PCRI")
                .price(5000)
                .stock(requestDto.getStock())
                .point(requestDto.getPoint())
                .category(Item.Category.BREAD)
                .status(Item.SaleStatus.ON_SALE)
                .itemOptions(optionDtos)
                .build();

        given(itemMapper.itemFrom(Mockito.any(ItemPatchDto.class))).willReturn(Mockito.mock(Item.class));
        given(itemService.updateItem(Mockito.any(Item.class))).willReturn(Mockito.mock(Item.class));
        given(itemMapper.itemResponseDtoFrom(Mockito.any(Item.class))).willReturn(responseDto);

        Long itemId = 1L;

        String request = gson.toJson(requestDto);

        //when

        ResultActions resultActions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/api/v1/items/{itemId}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.descriptionImage").value(requestDto.getDescriptionImage()))
                .andExpect(jsonPath("$.response.stock").value(requestDto.getStock()))
                .andExpect(jsonPath("$.response.point").value(requestDto.getPoint()))
                .andExpect(jsonPath("$.response.itemOptions[0].price").value(requestDto.getItemOptions().get(0).getPrice()))
                .andExpect(jsonPath("$.response.itemOptions[0].name").value(responseDto.getItemOptions().get(0).getName()))
                .andExpect(jsonPath("$.response.itemOptions[1].name").value(requestDto.getItemOptions().get(1).getName()))
                .andExpect(jsonPath("$.response.itemOptions[1].price").value(responseDto.getItemOptions().get(1).getPrice()))
                .andDo(
                        document(
                        "patch-item",
                            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                            pathParameters(
                                    parameterWithName("itemId").description("상품 식별자")
                            ),
                            requestFields(
                                    List.of(
                                            fieldWithPath("itemId").type(JsonFieldType.NUMBER).description("상품 식별자").ignored(),
                                            fieldWithPath("stock").type(JsonFieldType.NUMBER).description("재고수량").optional(),
                                            fieldWithPath("point").type(JsonFieldType.NUMBER).description("적립 포인트").optional(),
                                            fieldWithPath("descriptionImage").type(JsonFieldType.STRING).description("설명 이미지").optional(),
                                            fieldWithPath("itemOptions").type(JsonFieldType.ARRAY).description("상품 옵션").optional(),
                                            fieldWithPath("itemOptions[].itemOptionId").type(JsonFieldType.NUMBER).description("옵션 식별자").optional(),
                                            fieldWithPath("itemOptions[].name").type(JsonFieldType.STRING).description("옵션 이름").optional(),
                                            fieldWithPath("itemOptions[].price").type(JsonFieldType.NUMBER).description("옵션 가격").optional()
                                    )
                            ),
                            responseFields(
                                    List.of(
                                            fieldWithPath("response").type(JsonFieldType.OBJECT).description("응답"),
                                            fieldWithPath("response.itemId").type(JsonFieldType.NUMBER).description("식별자"),
                                            fieldWithPath("response.name").type(JsonFieldType.STRING).description("상품명"),
                                            fieldWithPath("response.title").type(JsonFieldType.STRING).description("상세정보글 제목"),
                                            fieldWithPath("response.thumbnail").type(JsonFieldType.STRING).description("썸네일 사진 이름"),
                                            fieldWithPath("response.descriptionImage").type(JsonFieldType.STRING).description("상세정보 이미지"),
                                            fieldWithPath("response.price").type(JsonFieldType.NUMBER).description("가격"),
                                            fieldWithPath("response.stock").type(JsonFieldType.NUMBER).description("재고"),
                                            fieldWithPath("response.point").type(JsonFieldType.NUMBER).description("구매시 적립 포인트"),
                                            fieldWithPath("response.code").type(JsonFieldType.STRING).description("상품식별코드"),
                                            fieldWithPath("response.category").type(JsonFieldType.STRING).description("카테고리"),
                                            fieldWithPath("response.status").type(JsonFieldType.STRING).description("판매상태"),
                                            fieldWithPath("response.itemOptions[]").type(JsonFieldType.ARRAY).description("옵션정보"),
                                            fieldWithPath("response.itemOptions[].itemOptionId").type(JsonFieldType.NUMBER).description("식별자"),
                                            fieldWithPath("response.itemOptions[].name").type(JsonFieldType.STRING).description("옵션이름").optional(),
                                            fieldWithPath("response.itemOptions[].price").type(JsonFieldType.NUMBER).description("옵션가격").optional()
                                    )
                            )
                        )
                );
    }
}
