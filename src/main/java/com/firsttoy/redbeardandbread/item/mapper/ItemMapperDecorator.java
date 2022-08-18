package com.firsttoy.redbeardandbread.item.mapper;

import com.firsttoy.redbeardandbread.item.dto.request.ItemPatchDto;
import com.firsttoy.redbeardandbread.item.dto.request.ItemPostDto;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
public abstract class ItemMapperDecorator implements ItemMapper {

    @Autowired
    @Qualifier("delegate")
    private ItemMapper delegate;


    @Override
    public void updateItemFromItemPostDto(Item item, ItemPostDto itemPostDto) {
        if (item == null) {
            return ;
        }
        log.info("update mapper invoked");

        itemPostDto.getItemOptions().forEach(itemOptionDto -> {
            ItemOption itemOption = itemOptionFrom(itemOptionDto);
            item.addItemOptions(itemOption);
        });
    }

    @Override
    public void updateItemFromSourceItem(Item item, Item sourceItem) {
        if (item == null) {
            return ;
        }

        item.setDescriptionImage(sourceItem.getDescriptionImage());
        item.setPoint(sourceItem.getPoint());
        item.setStock(sourceItem.getStock());

        for (int i = 0; i < item.getItemOptions().size(); i++) {
            ItemOption itemOption = item.getItemOptions().get(i);
            ItemOption source = sourceItem.getItemOptions().get(i);

            itemOption.setName(source.getName());
            itemOption.setPrice(source.getPrice());

        }

    }
}
