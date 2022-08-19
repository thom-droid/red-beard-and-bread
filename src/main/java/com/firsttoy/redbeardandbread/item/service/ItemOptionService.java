package com.firsttoy.redbeardandbread.item.service;

import com.firsttoy.redbeardandbread.exception.CustomRuntimeException;
import com.firsttoy.redbeardandbread.exception.Exceptions;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.item.entity.ItemOption;
import com.firsttoy.redbeardandbread.item.repository.ItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemOptionService {

    private final ItemOptionRepository itemOptionRepository;

    public void updateItemOptions(Item source) {

    }

    //Todo : verifying code could be refactored using generics?
    private void verifyExistence(Long id) {

    }

    public ItemOption findById(Long id) {
        return itemOptionRepository.findById(id).orElseThrow(
                () -> {throw new CustomRuntimeException(Exceptions.NO_SUCH_ELEMENT);}
        );
    }
}
