package com.firsttoy.redbeardandbread.slice.repository;

import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenItem_whenSaved_thenItemOptionsCascade() {
        //given

        Item data = Item.builder()
                .name("PLAIN CROISSANT")
                .title("basic croissant")
                .thumbnail("croissant.jpg")
                .descriptionImage("croissant-description.jpg")
                .price(5000)
                .stock(100)
                .point(50)
                .category(Item.Category.BREAD)
                .code("PCRI")
                .build();

        ItemOption itemOption = ItemOption.builder()
                .name("size up")
                .price(500)
                .build();

        data.addItemOptions(itemOption);

        //when
        Item savedItem = itemRepository.save(data);

        //then
        assertThat(savedItem).isEqualTo(data);
        assertThat(savedItem.getItemId()).isEqualTo(1L);
        assertThat(savedItem.getCode()).isEqualTo(data.getCode());
        assertThat(savedItem.getItemOptions()).isNotEmpty();
        assertThat(savedItem.getItemOptions().get(0).getName()).isEqualTo("size up");
        assertThat(savedItem.getItemOptions().get(0).getItem().getItemId()).isEqualTo(1L);
    }
}
