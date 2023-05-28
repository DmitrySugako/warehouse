package com.sugako.converter;

import com.sugako.domain.Item;
import com.sugako.repository.ItemRepository;
import com.sugako.requests.ItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemUpdateConverter extends ItemBaseConverter<ItemUpdateRequest, Item> {

    private final ItemRepository itemRepository;

    @Override
    public Item convert(ItemUpdateRequest source) {

        Optional<Item> item = itemRepository.findById(source.getId());
        return doConvert(item.orElseThrow(EntityNotFoundException::new), source);
    }
}
